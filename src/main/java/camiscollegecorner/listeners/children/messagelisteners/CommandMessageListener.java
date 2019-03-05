package camiscollegecorner.listeners.children.messagelisteners;

import camiscollegecorner.Constants;
import camiscollegecorner.commandhandlers.CmdHandler;
import camiscollegecorner.listeners.MessageListener;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

/** This class handles users entering commands. */
public class CommandMessageListener extends MessageListener {

	/** The singleton instance of this class. */
	private static final CommandMessageListener INSTANCE = new CommandMessageListener();

	private CommandMessageListener() {

	}

	@Override
	public void handleEvent(Event event) {
		MessageReceivedEvent messageEvent = (MessageReceivedEvent) event;

		boolean shouldHandleMessage =
				!Constants.DEBUG_MODE || messageEvent.getChannel().getLongID() == Constants.BOT_TEAM_CHANNEL_ID;

		if(shouldHandleMessage && messageEvent.getMessage().getContent().startsWith(Constants.CMD_PREFIX)) {
			handleCommand(messageEvent.getMessage());
		}
	}

	public static CommandMessageListener getInstance() {
		return INSTANCE;
	}


	private void handleCommand(IMessage message) {
		CmdHandler handler = new CmdHandler(message);
		handler.handle();
	}
}