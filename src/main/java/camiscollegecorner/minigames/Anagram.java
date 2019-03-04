package camiscollegecorner.minigames;

import camiscollegecorner.Constants;
import camiscollegecorner.commandhandlers.AbstractHandler;
import camiscollegecorner.commandhandlers.CmdHandler;
import camiscollegecorner.listeners.MessageListener;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/** A minigame that rearranges the letters of a random user's username. */
public class Anagram extends AbstractMinigame {

	/** The user whose name will be anagram'd. */
	private IUser randomlyChosenUser;

	/** The username of the randomly chosen user, anagrammed. */
	private String nicknameAnagrammed;

	/** The user who won the minigame. */
	private IUser winner;

	/** For randomly choosing a user. */
	private Random random = new Random();

	private AnagramCmdHandler anagramCmdHandler;

	/** The description to use for the embed objects sent by this minigame. */
	private static final String ANAGRAM_DESCRIPTION = "Guess the anagram";

	/** The color to use for the embed objects sent by this minigame. */
	private static final int ANAGRAM_EMBED_COLOR = 0xf44242;

	/** An EmbedFieldObject storing information about the anagram game. */
	private static final EmbedObject.EmbedFieldObject ANAGRAM_EMBED_FIELD = new EmbedObject.EmbedFieldObject();

	/** An EmbedFieldObject storing the anagram. */
	private static final EmbedObject.EmbedFieldObject ANAGRAM_EMBED_USERNAME = new EmbedObject.EmbedFieldObject();

	/**
	 * @param commands
	 * [0] -> Command for users to place their guess. PREFIXCMD [tag of guess]
	 */
	public Anagram(String[] commands, IChannel sourceChannel) {
		super(commands, sourceChannel);

		List<IUser> users = sourceChannel.getUsersHere();
		int rand = random.nextInt(users.size());

		randomlyChosenUser = users.get(rand);
		nicknameAnagrammed = anagram(randomlyChosenUser.getName());

		ANAGRAM_EMBED_FIELD.inline = false;
		ANAGRAM_EMBED_FIELD.name = "Anagrams!";
		ANAGRAM_EMBED_FIELD.value = "In this minigame, you must guess whose Discord username has been anagram'd. The " +
				"letters of someone's username have been rearranged. You must guess who was chosen using " + Constants.CMD_PREFIX + commands[0] + " " +
				"[username of user you are guessing]. Good luck!";

		ANAGRAM_EMBED_USERNAME.inline = false;
		ANAGRAM_EMBED_USERNAME.name = "Anagram'd Username:";
		ANAGRAM_EMBED_USERNAME.value = "**" + nicknameAnagrammed + "**";
	}

	@Override
	public void handleMessage(MessageReceivedEvent mre) {
		IMessage message = mre.getMessage();

		anagramCmdHandler = new AnagramCmdHandler(message);
		anagramCmdHandler.handle();
	}

	@Override
	public void startGame() {
		IChannel channel = getSourceChannel();

		EmbedObject embed = new EmbedObject();
		embed.title = AbstractMinigame.EMBED_OBJECT_TITLE;
		embed.description = ANAGRAM_DESCRIPTION;

		embed.color = ANAGRAM_EMBED_COLOR;

		EmbedObject.EmbedFieldObject[] fields = new EmbedObject.EmbedFieldObject[3];

		fields[0] = AbstractMinigame.MINIGAME_INSTRUCTIONS;
		fields[1] = ANAGRAM_EMBED_FIELD;
		fields[2] = ANAGRAM_EMBED_USERNAME;

		embed.fields = fields;

		channel.sendMessage(embed);
	}

	@Override
	public void endGame() {
		getSourceChannel().sendMessage(winner.getName() + " has won. Sorry the ending of this was anti-climactic it's" +
				" currently 12 AM and I'm tired. I'll make the endings more special in the future I swear -Santorno.");
		MessageListener.currentMinigame = null;
	}

	/**
	 * Rearranges the characters of a string.
	 * @param original The string to anagram
	 * @return The anagrammed string
	 */
	private String anagram(String original) {
		
		String[] splitStrings = original.split(" ");
		String anagram = "";
		
		for(int i = 0; i < splitStrings.length; i++){
			List<Character> chars = new ArrayList<Character>;

			char[] charArray = splitStrings[i].toCharArray();

			for(char c : charArray) {
				chars.add(c);
			}

			Collections.shuffle(chars);

			for(char c : chars) {
				anagram += c;
			}
			
			anagram += " ";
		}
		
		return anagram;
		
	}

	private class AnagramCmdHandler extends CmdHandler {
		public AnagramCmdHandler(IMessage message) {
			super(message);
		}

		@Override
		public void handle() {
			String content = getMessage().getContent().toLowerCase();

			if(content.startsWith(Constants.CMD_PREFIX)) {
				//remove the command prefix:
				content = content.substring(Constants.CMD_PREFIX.length());

				System.out.println(content);
					System.out.println(content);
					if(content.startsWith(getCommands()[0])) {
						//guess command PREFIXCMD [tag of guess]
						GuessHandler h = new GuessHandler(getMessage());
						h.start();
					}
			}
		}
	}

	private class GuessHandler extends AbstractHandler {
		public GuessHandler(IMessage message) {
			super(message);
		}

		@Override
		public void run() {
			String guess = getMessage().getContent().split(" ")[1];

			System.out.println(guess);
			System.out.println(randomlyChosenUser.getName());

			if(randomlyChosenUser.getName().equalsIgnoreCase(guess)) {
				getMessage().addReaction(Constants.WHITE_CHECK_EMOJI);
				winner = getMessage().getAuthor();
				endGame();
			} else {
				getMessage().addReaction(Constants.RED_X_EMOJI);
			}
		}
	}
}
