package camiscollegecorner.listeners;

import camiscollegecorner.Constants;
import camiscollegecorner.listeners.children.messagelisteners.CommandMessageListener;
import camiscollegecorner.listeners.children.messagelisteners.RandomMinigameStartListener;
import camiscollegecorner.minigames.AbstractMinigame;
import camiscollegecorner.minigames.Anagram;
import camiscollegecorner.minigames.Hangman;
import camiscollegecorner.minigames.PicPick;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/** This class listens for all messages receieved by the bot client. */
public class MessageListener extends AbstractListener implements IListener<MessageReceivedEvent> {

    /** The current, active minigame. */
    public static AbstractMinigame currentMinigame = null;

    public MessageListener() {
        getChildListeners().add(CommandMessageListener.getInstance());
        getChildListeners().add(RandomMinigameStartListener.getInstance());
    }

    /** Called every time a message is received. */
    public void handle(MessageReceivedEvent messageEvent) {
        super.handleEvent(messageEvent);

        //TODO this started out as simply debugging. but maybe make this an actual command
        if(messageEvent.getAuthor().hasRole(messageEvent.getGuild().getRoleByID(Constants.BOT_TEAM_ROLE_ID)) && messageEvent.getMessage().getContent().equals("c" +
                "!anagram")) {
            currentMinigame = new Anagram(messageEvent.getChannel(), messageEvent.getAuthor(), false);
            currentMinigame.startGame();
        }

        if(messageEvent.getAuthor().hasRole(messageEvent.getGuild().getRoleByID(Constants.BOT_TEAM_ROLE_ID)) && messageEvent.getMessage().getContent().equals("c" +
                "!picpick")) {
            currentMinigame = new PicPick(messageEvent.getChannel(), messageEvent.getAuthor(), false);
            currentMinigame.startGame();
        }

        if(messageEvent.getAuthor().hasRole(messageEvent.getGuild().getRoleByID(Constants.BOT_TEAM_ROLE_ID)) && messageEvent.getMessage().getContent().equals("c" +
                "!hangman")) {
            currentMinigame = new Hangman(messageEvent.getChannel(), messageEvent.getAuthor(), false);
            currentMinigame.startGame();
        }
    }
}