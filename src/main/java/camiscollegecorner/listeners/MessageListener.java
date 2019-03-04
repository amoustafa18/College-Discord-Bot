package camiscollegecorner.listeners;

import camiscollegecorner.Constants;
import camiscollegecorner.commandhandlers.CmdHandler;
import camiscollegecorner.minigames.AbstractMinigame;
import camiscollegecorner.minigames.Anagram;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

import java.util.Random;

/** This class listens for all messages receieved by the bot client. */
public class MessageListener implements IListener<MessageReceivedEvent> {

    private Random random = new Random();

    public static AbstractMinigame currentMinigame = null;

    /** The chances of a minigame starting per message per 10 000. For example, if this is 5, there is likely to be 5
     *  minigames per 10 000 messages received. */
    private static final int MINIGAME_START_CHANCE = 5;

    /** Called every time a message is received. */
    public void handle(MessageReceivedEvent messageEvent) {
        String channelName = messageEvent.getChannel().getName();
        boolean shouldHandleMessage = !Constants.DEBUG_MODE || channelName.equals("bot-team");

        if(shouldHandleMessage && messageEvent.getMessage().getContent().startsWith(Constants.CMD_PREFIX)) {
            handleCommand(messageEvent.getMessage());
        }

        if(currentMinigame != null) {
            currentMinigame.handleMessage(messageEvent);
        } else {
            if(random.nextInt(10001) <= MINIGAME_START_CHANCE) {
                //randomly pick a minigame
                currentMinigame = new Anagram(new String[] {CmdHandler.CMD_GUESS}, messageEvent.getChannel());
                currentMinigame.startGame();
            }
        }

        if(messageEvent.getAuthor().hasRole(messageEvent.getGuild().getRoleByID(Constants.BOT_TEAM_ROLE_ID)) && messageEvent.getMessage().getContent().equals("c" +
                "!anagram")) {
            currentMinigame = new Anagram(new String[] {CmdHandler.CMD_GUESS}, messageEvent.getChannel());
            currentMinigame.startGame();
            System.out.println("STARTED");
        }
    }

    public void handleCommand(IMessage message) {
        CmdHandler handler = new CmdHandler(message);
        handler.handle();
    }
}