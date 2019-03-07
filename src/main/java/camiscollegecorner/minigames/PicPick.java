package camiscollegecorner.minigames;

import camiscollegecorner.Constants;
import camiscollegecorner.commandhandlers.CmdHandler;
import camiscollegecorner.commandhandlers.SingleThreadedAbstractHandler;
import camiscollegecorner.listeners.MessageListener;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

import java.util.List;
import java.util.Random;

/** A minigame that choose's a random user's avatar. */
public class PicPick extends AbstractMinigame {

	/** The user whose avatar will be pulled. */
	private IUser randomlyChosenUser;

	/** The user who won the minigame. */
	private IUser winner;

	/** For randomly choosing a user. */
	private Random random = new Random();

	/** The CmdHandler bound to this minigame. */
	private PicPickCmdHandler picPickCmdHandler;

	/** The description to use for the embed objects sent by this minigame. */
	private static final String PICPICK_DESCRIPTION = "Guess whose pic";

	/** The color to use for the embed objects sent by this minigame. */
	private static final int PICPICK_EMBED_COLOR = 0x4286f4;

	/** An EmbedFieldObject storing information about the picpick game. */
	private static final EmbedObject.EmbedFieldObject PICPICK_EMBED_FIELD = new EmbedObject.EmbedFieldObject();

	/**
	 * Construct a PicPick minigame. In this minigame, a user is randomly chosen from the channel and has their
	 * avatar pulled. Users must guess whose avatar has been pulled.
	 * @param sourceChannel The channel this minigame was sourced from.
	 * @param sourceUser The user this minigame was started by. Null if this was automatically started.
	 * @param automaticallyStarted Whether or not this minigame was randomly started.
	 */
	public PicPick(IChannel sourceChannel, IUser sourceUser, boolean automaticallyStarted) {
		super(sourceChannel, sourceUser, automaticallyStarted);

		List<IUser> users = sourceChannel.getUsersHere();
		int rand = random.nextInt(users.size());

		randomlyChosenUser = users.get(rand);

		PICPICK_EMBED_FIELD.inline = false;
		PICPICK_EMBED_FIELD.name = "PicPick!";
		PICPICK_EMBED_FIELD.value = "In this minigame, you must guess whose Discord avatar is currently displayed. " +
				" Place your guess using " +	Constants.CMD_PREFIX + CmdHandler.CMD_GUESS +
				" [username of user you are guessing]. Good luck!";
	}

	@Override
	public void handleMessage(MessageReceivedEvent mre) {
		IMessage message = mre.getMessage();

		picPickCmdHandler = new PicPick.PicPickCmdHandler(message);
		picPickCmdHandler.handle();
	}

	/** Introduce the minigame. */
	@Override
	public void startGame() {
		IChannel channel = getSourceChannel();

		EmbedObject embed = new EmbedObject();
		embed.title = AbstractMinigame.EMBED_OBJECT_TITLE;
		embed.description = PICPICK_DESCRIPTION;

		embed.color = PICPICK_EMBED_COLOR;

		EmbedObject.EmbedFieldObject[] fields = new EmbedObject.EmbedFieldObject[1];

		fields[0] = PICPICK_EMBED_FIELD;

		embed.fields = fields;

		EmbedObject.ImageObject avatar = new EmbedObject.ImageObject();

		String url = randomlyChosenUser.getAvatarURL();

		if(url.endsWith("webp")) {
			//replace webp with png, as webp is not embedded properly on IOS
			url = url.substring(0, url.length() - 4) + "png";
		}

		avatar.url = url;

		embed.image = avatar;

		embed.footer = generateFooter();

		channel.sendMessage(embed);
	}

	@Override
	public void endGame() {
		getSourceChannel().sendMessage(winner.getName() + " has won. Sorry the ending of this was anti-climactic it's" +
				" currently 12 AM and I'm tired. I'll make the endings more special in the future I swear -Santorno.");
		MessageListener.currentMinigame = null;
	}

	private class PicPickCmdHandler extends CmdHandler {
		public PicPickCmdHandler(IMessage message) {
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
					PicPick.GuessHandler h = new PicPick.GuessHandler(getMessage());
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
			String guess = getMessage().getContent().split(" ", 2)[1];

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