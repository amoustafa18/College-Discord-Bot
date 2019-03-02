package camiscollegecorner;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.handle.obj.ActivityType;
import sx.blah.discord.handle.obj.StatusType;
import sx.blah.discord.util.DiscordException;

import java.util.Random;

public class Main {

    public static IDiscordClient client;
    public static final String COMMAND_LIST = "src/main/resources/commands.txt";
    public static final String CMD_PREFIX = "!";
    public static final boolean DEBUG_MODE = false; //when true, the bot will ONLY be responsive in #bot-team channel

    private static final String PLAYING_TEXT = "Type " + CMD_PREFIX + "help for command list";
    private static final String TOKEN = System.getProperty("botToken");

    private static Random rand = new Random();

    public static void main(String[] args) throws Exception {
        client = buildClient(TOKEN);

        EventDispatcher dispatcher = client.getDispatcher();
        dispatcher.registerListener(new MessageListener());
        dispatcher.registerListener(new ReactionAddListener());

        //wait until client is ready, then change its presence
        while(!(client.isLoggedIn() && client.isReady())) {
            //do nothing
        }

        //change presence
        client.changePresence(StatusType.ONLINE, ActivityType.PLAYING, PLAYING_TEXT);
    }

    public static IDiscordClient buildClient(String token) {
        ClientBuilder builder = new ClientBuilder();
        builder.withToken(token);

        try {
            return builder.login(); // Creates the client instance and logs the client in
        } catch (DiscordException e) { // This is thrown if there was a problem building the client
            e.printStackTrace();
            return null;
        }
    }
}