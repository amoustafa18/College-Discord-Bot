package camiscollegecorner.commandhandlers;

import camiscollegecorner.Constants;
import sx.blah.discord.handle.obj.IMessage;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

/** This class handles the help command. */
public class HelpHandler extends AbstractHandler {

	private static final String ERROR_LIST = "There was an error loading the commands list. Please try again later.";

	public HelpHandler(IMessage message) {
		super(message);
	}

	@Override
	public void run() {

		InputStream is = this.getClass().getClassLoader().getResourceAsStream(Constants.COMMAND_LIST);
		System.out.println(is);

		String line = "";
		String response = "";

		Properties config = new Properties();
		File file = new File(Constants.COMMAND_LIST);
		if(file.exists()) {
			try (Scanner s = new Scanner(file)) {
				while((line = s.nextLine()) != null) {
				response += line + "\n";
			}

			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("\nUnable to load commands");
			}
		}

		getMessage().getAuthor().getOrCreatePMChannel().sendMessage(response);
	}
}