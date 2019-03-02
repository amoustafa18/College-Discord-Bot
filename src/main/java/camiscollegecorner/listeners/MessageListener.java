package camiscollegecorner.listeners;

import camiscollegecorner.Constants;
import camiscollegecorner.commandhandlers.CmdHandler;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

/** This class listens for all messages receieved by the bot client. */
public class MessageListener implements IListener<MessageReceivedEvent> {

    /** Called every time a message is received. */
    public void handle(MessageReceivedEvent messageEvent) {
        String channelName = messageEvent.getChannel().getName();
        boolean shouldHandleMessage = !Constants.DEBUG_MODE || channelName.equals("bot-team");

        if(shouldHandleMessage && messageEvent.getMessage().getContent().startsWith(Constants.CMD_PREFIX)) {
            handleCommand(messageEvent.getMessage());
        }
    }

    public void handleCommand(IMessage message) {
        CmdHandler handler = new CmdHandler(message);
        handler.handle();
    }
}