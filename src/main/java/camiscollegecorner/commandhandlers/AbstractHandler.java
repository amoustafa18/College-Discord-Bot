package camiscollegecorner.commandhandlers;

import camiscollegecorner.Constants;
import sx.blah.discord.handle.obj.IMessage;

import java.util.ArrayList;
import java.util.List;

//TODO add a common system between commands checking how to respond if a command is misused.
/** This is an abstract handler class that stores the message that dispatched the handler. */
public abstract class AbstractHandler extends Thread {

	/** The message that dispatched the handler. */
	private IMessage message;

	/** A List of long ids of channels that this command should be handled on. If the command should be handled on
	 * all channels, simply add {@code Constants.ALL_CHANNELS_FLAG_ID} to this list. */
	private List<Long> channelsActive = new ArrayList<>();

	/** Whether or not the command should be handled. */
	private boolean shouldRun = true;

	public AbstractHandler(IMessage message) {
		this.message = message;
	}

	/** At a minimum, all handlers should check if the command should be detected on this channel. Therefore, all
	 * subclasses of AbstractHandler should override run() and call super.run().
	 */
	@Override
	public void run() {
		//the id of the channel this command was issued on
		long sourceChannelLongId = message.getChannel().getLongID();

		//command should only run if this channel is in the active channels or if the channel is the bot team channel
		if(!(channelsActive.contains(sourceChannelLongId) ||
				channelsActive.contains(Constants.ALL_CHANNELS_FLAG_ID) ||
				sourceChannelLongId == Constants.BOT_TEAM_CHANNEL_ID)) {
			//command is not to be used in this channel
			onCommandUsedIncorrectly();
			shouldRun = false;
		}
	}

	/**
	 * Gets the channels this command works on.
	 * @return A List of channel long ids that this command works on.
	 */
	public List<Long> channelsActive() {
		return channelsActive;
	}

	public IMessage getMessage() {
		return message;
	}

	public boolean shouldRun() {
		return shouldRun;
	}

	public void onCommandUsedIncorrectly(){
		getMessage().getAuthor().getOrCreatePMChannel().sendMessage("That command has been used incorrectly" +
				", you do not have access to that command, or it can not be used in that channel.");
	}
}