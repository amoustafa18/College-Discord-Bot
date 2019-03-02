package camiscollegecorner;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.Image;

public class MessageListener implements IListener<MessageReceivedEvent> {

    public void handle(MessageReceivedEvent messageEvent) {
        String channelName = messageEvent.getChannel().getName();
        boolean shouldHandleMessage = !Main.DEBUG_MODE || channelName.equals("bot-team");

        if(shouldHandleMessage && messageEvent.getMessage().getContent().startsWith(Main.CMD_PREFIX)) {
            handleCommand(messageEvent.getMessage());
        }
    }

    public void handleCommand(IMessage message) {
        CmdHandler handler = new CmdHandler(message);
        handler.start();
    }
}