package camiscollegecorner;

import camiscollegecorner.listeners.MessageListener;
import camiscollegecorner.listeners.ReactionAddListener;
import camiscollegecorner.listeners.ReactionRemoveListener;
import camiscollegecorner.listeners.ReadyListener;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.util.DiscordException;

public class Main {

    private static IDiscordClient client;

    private static final String TOKEN = System.getProperty("botToken");

    public static void main(String[] args) throws Exception {
        client = buildClient(TOKEN);

        EventDispatcher dispatcher = client.getDispatcher();
        dispatcher.registerListener(new MessageListener());
        dispatcher.registerListener(new ReactionAddListener());
        dispatcher.registerListener(new ReactionRemoveListener());
        dispatcher.registerListener(new ReadyListener());
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