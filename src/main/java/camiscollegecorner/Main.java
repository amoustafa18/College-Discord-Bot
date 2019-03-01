package camiscollegecorner;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.util.DiscordException;

public class Main {
    
    public static IDiscordClient client;
    public static final String COMMAND_LIST = "src/main/resources/commands.txt";
    public static final String CMD_PREFIX = "!";

    private static final String PLAYING_TEXT = "Type !help for command list";
    private static final String TOKEN = "NTUwODc0NjQxMjc5NzQ2MDQ4.D1pDBw.lfCdrpiXl75D8bTL_n4JXJBekNk";

    public static void main(String[] args) {
        client = buildClient(TOKEN);
        client.changePlayingText(PLAYING_TEXT);

        EventDispatcher dispatcher = client.getDispatcher();
        dispatcher.registerListener(new MessageListener());
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