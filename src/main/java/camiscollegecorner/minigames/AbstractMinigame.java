package camiscollegecorner.minigames;

import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

/** An abstract version of a Discord minigame. */
public abstract class AbstractMinigame {

	/** The channel this minigame was started on. */
	private IChannel sourceChannel;

	/** The user that started this minigame, if one exists. Otherwise, this is null. */
	private IUser sourceUser;

	/** The title to use for embed objects when sending info about minigames to server. */
	public static final String EMBED_OBJECT_TITLE = "MINIGAME";

	/** The footer to use on the minigame embed objects when the game was started automatically. */
	public static final String EMBED_FOOTER_AUTOMATIC = "This minigame was triggered automatically.";

	/** Whether or not this minigame was triggered automatically. */
	private boolean automaticallyStarted = false;

	/**
	 * Construct an AbstractMinigame.
	 * @param sourceChannel The channel this minigame was sourced from.
	 * @param sourceUser The user this minigame was started by. Null if this was automatically started.
	 * @param automaticallyStarted Whether or not this minigame was randomly started.
	 */
	public AbstractMinigame(IChannel sourceChannel, IUser sourceUser, boolean automaticallyStarted) {
		this.sourceChannel = sourceChannel;
		this.sourceUser = sourceUser;
		this.automaticallyStarted = automaticallyStarted;
	}

	/**
	 * Handles an incoming message appropriately.
	 * @param mre The message received while this minigame was in progress.
	 */
	public abstract void handleMessage(MessageReceivedEvent mre);

	/**
	 * A method intended for setup purposes when a minigame is started.
	 */
	public abstract void startGame();
	/**
	 * A method intended for cleanup purposes when a minigame is completed.
	 */
	public abstract void endGame();

	public IChannel getSourceChannel() {
		return sourceChannel;
	}

	public boolean isAutomaticallyStarted() {
		return automaticallyStarted;
	}

	/**
	 * Generates a footer for all embed objects sent by this minigame. If the minigame was started automatically,
	 * return {@code EMBED_FOOTER_AUTOMATIC}. Otherwise, return a footer describing who started this minigame along
	 * with their profile picture.
	 * @return The generated footer.
	 */
	public EmbedObject.FooterObject generateFooter() {
		EmbedObject.FooterObject footer = new EmbedObject.FooterObject();

		if(isAutomaticallyStarted()) {
			footer.text = EMBED_FOOTER_AUTOMATIC;
		} else {
			footer.text = "This minigame was started by " + sourceUser.getName() + ". No currency can be earned by " +
					"user-started minigames.";
			footer.icon_url = sourceUser.getAvatarURL();
		}

		return footer;
	}
}