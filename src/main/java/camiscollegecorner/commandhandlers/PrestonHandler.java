package camiscollegecorner.commandhandlers;

import camiscollegecorner.Constants;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IMessage;

import java.io.File;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/** This class handles the preston command. */
public class PrestonHandler extends AbstractHandler {

    /** This constructs a PrestonHandler. This object should handle the preston command. The preston
     * command should respond with a random picture of Preston the cat embedded. The picture of the cat will be
     * pulled from a text file.
     * @param message The IMessage issuing this command.
     */
    public PrestonHandler(IMessage message) {
        super(message);

        List<Long> activeChannels = channelsActive();

        for(long l : Constants.PRESTON_CHANNELS) {
            activeChannels.add(l);
        }
    }

    /** When the number of elements in {@code links} dips below this number, restock the list on another thread. */
    private static final int RESTOCK_THRESHOLD = 5;

    /** A list of URLs to preston pictures. */
    private static List<String> links = new ArrayList<>();

    /** A SecureRandom object used for generating random colors. */
    private static SecureRandom secureRandom = new SecureRandom();

    @Override
    public void run() {
        super.run();

        if(!shouldRun()) {
            return;
        }

        Random random = new Random();

        EmbedObject.ImageObject image = new EmbedObject.ImageObject();

        int randomIndex = random.nextInt(links.size());
        image.url = links.get(randomIndex);
        links.remove(randomIndex);
        checkForRestock();

        EmbedObject embed = new EmbedObject();
        embed.image = image;

        //generate a random color for the embed
        int color = secureRandom.nextInt(1000000);
        embed.color = color;
        embed.type = "image";
        embed.description = "Preston!";

        getMessage().getChannel().sendMessage(embed);
    }

    /**
     * Restocks {@code links} on a different thread if the number of elements in the list drops below {@code
     * RESTOCK_THRESHOLD}
     */
    private void checkForRestock() {
        if(links.size() < RESTOCK_THRESHOLD) {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    cachePrestonImages();
                }
            };

            Thread t = new Thread(r);
            t.start();
        }
    }

    /**
     * Grabs all of the URLs from file {@code Constants.PRESTON_LIST} and adds it to {@code links}.
     */
    public static void cachePrestonImages() {
        File file = new File(Constants.PRESTON_LIST);
        if(file.exists()) {
            try (Scanner s = new Scanner(file)) {

                while (s.hasNextLine()) {
                    links.add(s.nextLine());
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("\nUnable to load preston");
            }
        }
    }
}