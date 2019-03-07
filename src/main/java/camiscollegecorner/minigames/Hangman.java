package camiscollegecorner.minigames;

import camiscollegecorner.Constants;
import camiscollegecorner.commandhandlers.CmdHandler;
import camiscollegecorner.commandhandlers.SingleThreadedAbstractHandler;
import camiscollegecorner.hangman.HangmanClient;
import camiscollegecorner.hangman.HangmanClientImpl;
import camiscollegecorner.listeners.MessageListener;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.obj.Embed;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hangman extends AbstractMinigame {

	/** A List containing all users who placed a guess in the hangman game. */
	private Set<IUser> participants;

	/** The CmdHandler bound to this minigame. */
	private Hangman.HangmanCmdHandler hangmanCmdHandler;

	/** The backend client of this minigame. */
	private HangmanClient hangmanClient;

	/** The description to use for the embed objects sent by this minigame. */
	private static final String HANGMAN_DESCRIPTION = "Guess the word";

	/** The color to use for the embed objects sent by this minigame. */
	private static final int HANGMAN_EMBED_COLOR = 0xe88914;

	/** The thumbnail of the hanging man to be used for this minigame's embeds. */
	private static final EmbedObject.ThumbnailObject HANGMAN_THUMBNAIL = new EmbedObject.ThumbnailObject();

	/** An EmbedFieldObject storing information about the picpick game. */
	private static final EmbedObject.EmbedFieldObject HANGMAN_EMBED_FIELD = new EmbedObject.EmbedFieldObject();

	/** An EmbedFieldObject storing information about the secret phrase. */
	private static final EmbedObject.EmbedFieldObject HANGMAN_PHRASE_FIELD = new EmbedObject.EmbedFieldObject();

	/** An EmbedFieldObject storing the number of remaining incorrect guessed the client has. */
	private static final EmbedObject.EmbedFieldObject HANGMAN_GUESSES_REMAINING_FIELD =
			new EmbedObject.EmbedFieldObject();

	/** An EmbedFieldObject storing all of the previous guesses made by the client. */
	private static final EmbedObject.EmbedFieldObject HANGMAN_GUESS_LIST_FIELD = new EmbedObject.EmbedFieldObject();

	/** The embed containing all of the game's information. */
	private static EmbedObject GAME_EMBED = new EmbedObject();

	/**
	 * Construct a hangman minigame. In this minigame, a phrase is randomly chosen and hidden from the client. The
	 * client must guess all of the letters in the phrase before exhausting all of its guesses.
	 * @param sourceChannel The channel this minigame was sourced from.
	 * @param sourceUser The user this minigame was started by. Null if this was automatically started.
	 * @param automaticallyStarted Whether or not this minigame was randomly started.
	 */
	public Hangman(IChannel sourceChannel, IUser sourceUser, boolean automaticallyStarted) {
		super(sourceChannel, sourceUser, automaticallyStarted);

		participants = new HashSet<>();
		hangmanClient = new HangmanClientImpl();

		HANGMAN_THUMBNAIL.url = Constants.HANGMAN_IMAGE_0;

		HANGMAN_EMBED_FIELD.inline = false;
		HANGMAN_EMBED_FIELD.name = "Hangman!";
		HANGMAN_EMBED_FIELD.value = "In this minigame, you must guess the letters of a random phrase. " +
				" Place your guess using " +	Constants.CMD_PREFIX + CmdHandler.CMD_GUESS +
				" [character you are guessing]. Good luck!";

		HANGMAN_PHRASE_FIELD.inline = false;
		HANGMAN_PHRASE_FIELD.name = "Phrase";
		//TODO remove ternary operator when backend is complete
		HANGMAN_PHRASE_FIELD.value = hangmanClient.getSecretPhraseAsDisplayed() == null ? "unimplemented" :
				hangmanClient.getSecretPhraseAsDisplayed();

		HANGMAN_GUESSES_REMAINING_FIELD.inline = true;
		HANGMAN_GUESSES_REMAINING_FIELD.name = "Health";
		HANGMAN_GUESSES_REMAINING_FIELD.value =
				"The hanging man's health is " + (hangmanClient.getMaxHealth() - hangmanClient.getCurrentHealth());

		//TODO remove ternary operator when backend is complete
		HANGMAN_GUESS_LIST_FIELD.inline = true;
		HANGMAN_GUESS_LIST_FIELD.name = "Guesses";
		HANGMAN_GUESS_LIST_FIELD.value = hangmanClient.getAllGuesses() == null ? "unimplemented" :
				characterListToString(hangmanClient.getAllGuesses());
	}

	@Override
	public void handleMessage(MessageReceivedEvent mre) {
		IMessage message = mre.getMessage();

		hangmanCmdHandler = new Hangman.HangmanCmdHandler(message);
		hangmanCmdHandler.handle();
	}

	/** Introduce the minigame. */
	@Override
	public void startGame() {
		IChannel channel = getSourceChannel();

		EmbedObject embed = new EmbedObject();
		embed.title = AbstractMinigame.EMBED_OBJECT_TITLE;
		embed.description = HANGMAN_DESCRIPTION;
		embed.thumbnail = HANGMAN_THUMBNAIL;

		embed.color = HANGMAN_EMBED_COLOR;

		EmbedObject.EmbedFieldObject[] fields = new EmbedObject.EmbedFieldObject[4];

		fields[0] = HANGMAN_EMBED_FIELD;
		fields[1] = HANGMAN_GUESS_LIST_FIELD;
		fields[2] = HANGMAN_GUESSES_REMAINING_FIELD;
		fields[3] = HANGMAN_PHRASE_FIELD;

		embed.fields = fields;

		embed.footer = generateFooter();

		channel.sendMessage(embed);

		GAME_EMBED = embed;
	}

	private void updateGame() {
		getSourceChannel().sendMessage(GAME_EMBED);
	}

	private void updateThumbnail() {
		switch(HANGMAN_THUMBNAIL.url) {
			case Constants.HANGMAN_IMAGE_0:
				HANGMAN_THUMBNAIL.url = Constants.HANGMAN_IMAGE_1;
				break;
			case Constants.HANGMAN_IMAGE_1:
				HANGMAN_THUMBNAIL.url = Constants.HANGMAN_IMAGE_2;
				break;
			case Constants.HANGMAN_IMAGE_2:
				HANGMAN_THUMBNAIL.url = Constants.HANGMAN_IMAGE_3;
				break;
			case Constants.HANGMAN_IMAGE_3:
				HANGMAN_THUMBNAIL.url = Constants.HANGMAN_IMAGE_4;
				break;
			case Constants.HANGMAN_IMAGE_4:
				HANGMAN_THUMBNAIL.url = Constants.HANGMAN_IMAGE_5;
				break;
			case Constants.HANGMAN_IMAGE_5:
				HANGMAN_THUMBNAIL.url = Constants.HANGMAN_IMAGE_6;
				break;
			case Constants.HANGMAN_IMAGE_6:
				HANGMAN_THUMBNAIL.url = Constants.HANGMAN_IMAGE_7;
			case Constants.HANGMAN_IMAGE_7:
				HANGMAN_THUMBNAIL.url = Constants.HANGMAN_IMAGE_7;
		}
	}

	@Override
	public void endGame() {
		EmbedObject finalEmbed = GAME_EMBED;

		EmbedObject.EmbedFieldObject[] fields = new EmbedObject.EmbedFieldObject[3];
		EmbedObject.EmbedFieldObject winnersField = new EmbedObject.EmbedFieldObject();

		if(hangmanClient.getCurrentHealth() < hangmanClient.getMaxHealth()) {
			//the client has won the game
			winnersField.name = "Winners";
			winnersField.value = userSetToString(participants);
		} else {
			//the client lost the game
			winnersField.name = "You lose";
			winnersField.value = "You were unable to save the hanging man. :sad:";
		}

		fields[0] = finalEmbed.fields[0];
		fields[1] = finalEmbed.fields[1];
		fields[2] = winnersField;

		finalEmbed.fields = fields;

		getSourceChannel().sendMessage(finalEmbed);
		MessageListener.currentMinigame = null;
	}

	/**
	 * Converts {@code set} to a Dtring by separating each of its elements by a comma.
	 * */
	private String userSetToString(Set<IUser> set) {
		StringBuilder sb = new StringBuilder();
		IUser[] setAsArr = (IUser[]) set.toArray();

		for(int i = 0; i < set.size(); i++) {
			if(i == set.size() - 1) {
				sb.append(setAsArr[i].getName());
			} else {
				sb.append(setAsArr[i].getName() + ", ");
			}
		}

		return sb.toString();
	}

	/**
	 * Converts {@code list} to a String by separating each of its elements by a space.
	 */
	private String characterListToString(List<Character> list) {
		StringBuilder sb = new StringBuilder();
		Collections.sort(list);

		for(Character c : list) {
			sb.append(c + " ");
		}

		return sb.toString().trim();
	}

	private class HangmanCmdHandler extends CmdHandler {
		public HangmanCmdHandler(IMessage message) {
			super(message);
		}

		@Override
		public void handle() {
			String content = getMessage().getContent().toLowerCase();

			if(content.startsWith(Constants.CMD_PREFIX)) {
				//remove the command prefix:
				content = content.substring(Constants.CMD_PREFIX.length());

				if(content.startsWith(CmdHandler.CMD_GUESS)) {
					//guess command PREFIXCMD [username of guess]
					Hangman.GuessHandler h = new Hangman.GuessHandler(getMessage());
					h.run();
				}
			}
		}
	}

	private class GuessHandler extends SingleThreadedAbstractHandler {
		public GuessHandler(IMessage message) {
			super(message);
		}

		@Override
		public void run() {
			String[] cmdAsArray = getMessage().getContent().split(" ");

			//TODO override onCommandUsedIncorrectly()
			if(cmdAsArray.length != 2) {
				onCommandUsedIncorrectly();
				return;
			}

			String guess = cmdAsArray[1];

			if(guess.length() != 1) {
				onCommandUsedIncorrectly();
				return;
			}

			int currentHealth = hangmanClient.getCurrentHealth();

			hangmanClient.guess(guess.charAt(0));

			if(hangmanClient.getCurrentHealth() > currentHealth) {
				//incorrect guess
				getMessage().addReaction(Constants.RED_X_EMOJI);
				updateThumbnail();
				//TODO update guesses remaining field
				//TODO append guess to guesses made field

				if(hangmanClient.getCurrentHealth() == hangmanClient.getMaxHealth()) {
					//client is dead
					endGame();
					return;
				}

				updateGame();
			} else {
				//correct guess
				getMessage().addReaction(Constants.WHITE_CHECK_EMOJI);
				GAME_EMBED.fields[1].value = hangmanClient.getSecretPhraseAsDisplayed();

				if(!hangmanClient.getSecretPhraseAsDisplayed().contains("_")) {
					//game is over
					endGame();
					return;
				}

				updateGame();
			}
		}
	}
}