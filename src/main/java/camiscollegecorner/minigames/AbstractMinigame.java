package camiscollegecorner.minigames;

import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;

/** An abstract version of a Discord minigame. */
public abstract class AbstractMinigame {

	private String[] commands;
	private IChannel sourceChannel;

	/** The title to use for embed objects when sending info about minigames to server. */
	public static final String EMBED_OBJECT_TITLE = "MINIGAME";

	/** An EmbedFieldObject storing a general introduction to the minigame. */
	public static final EmbedObject.EmbedFieldObject MINIGAME_INSTRUCTIONS = new EmbedObject.EmbedFieldObject();

	/**
	 * Construct an AbstractMinigame.
	 * @param commands An array of commands used by this minigame. Each concrete minigame should handle this array
	 *                    accordingly.
	 * @param sourceChannel The channel in which the minigame was triggered on.
	 */
	public AbstractMinigame(String[] commands, IChannel sourceChannel) {
		this.commands = commands;
		this.sourceChannel = sourceChannel;

		MINIGAME_INSTRUCTIONS.inline = false;
		MINIGAME_INSTRUCTIONS.name = "Instructions";
		MINIGAME_INSTRUCTIONS.value = "A minigame has been randomly triggered on this channel!";
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

	public String[] getCommands() {
		return commands;
	}

	public IChannel getSourceChannel() {
		return sourceChannel;
	}
}
