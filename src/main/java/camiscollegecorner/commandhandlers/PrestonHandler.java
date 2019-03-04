package camiscollegecorner.commandhandlers;

import camiscollegecorner.Constants;
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

        String line = "";
        String response = "";


        File file = new File(Constants.PRESTON_LIST);
        if(file.exists()) {
            try (Scanner s = new Scanner(file)) {
                int count = 0;
                while (s.hasNextLine()) {
                    count++;
                    s.nextLine();
                }
                s.close();

               Scanner scanner = new Scanner(file);

                line = scanner.nextLine();
                Random random = new Random();
                int val = random.nextInt(count);
                for(int i = 0; i <= val; i++)
                    line = scanner.nextLine();

                response = line;

            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("\nUnable to load preston");
            }
        }

        getMessage().getChannel().sendMessage(response);
    }


}
