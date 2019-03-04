package camiscollegecorner.commandhandlers;

import camiscollegecorner.Constants;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IMessage;

import java.io.File;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class PrestonHandler extends AbstractHandler {

    public PrestonHandler(IMessage message) { super(message); }


    @Override
    public void run() {

        InputStream is = this.getClass().getClassLoader().getResourceAsStream(Constants.PRESTON_LIST);
        System.out.println(is);
        Random random = new Random();
        String line = "";
        String response = "";


        File file = new File(Constants.PRESTON_LIST);
        if(file.exists()) {
            try (Scanner s = new Scanner(file)) {
               //figuring out how many lines are in the file
                int count = 0;
                while (s.hasNextLine()) {
                    count++;
                    s.nextLine();
                }
                s.close();
                //couldnt figure out how to bring the scanner back to the top of the file so i just made a new scanner
               Scanner scanner = new Scanner(file);


               //ranndom preston picture

                int val = random.nextInt(count);
                for(int i = 0; i <= val; i++)
                    line = scanner.nextLine();

                response = line;



            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("\nUnable to load preston");
            }
        }
        EmbedObject.ImageObject image = new EmbedObject.ImageObject();
        image.url = response;

        EmbedObject embed = new EmbedObject();
        embed.image = image;
        int color = random.nextInt(1000000);
        embed.color = color;
        embed.type = "image";
        embed.description = "Preston!";

        getMessage().getChannel().sendMessage(embed);
    }


}
