package camiscollegecorner.commandhandlers;

import camiscollegecorner.Constants;
import sx.blah.discord.handle.obj.IMessage;

import java.io.*;
import java.util.Scanner;

/** This class handles the help command. */
public class HelpHandler extends AbstractHandler {

	private static final String ERROR_LIST = "There was an error loading the commands list. Please try again later.";

	public HelpHandler(IMessage message) {
		super(message);
	}

	@Override
	public void run() {
		File commandList = null;
		BufferedReader br = null;
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("/resources/commands.txt");
		System.out.println(is);

		//Scanner s = new Scanner(is);

		String response = "";

			//commandList = new File(getClass().getResourceAsStream(Constants.COMMAND_LIST));

			//br = new BufferedReader(new FileReader(commandList));

			//br = new BufferedReader(is);

			String line = "";

//			while((line = s.nextLine()) != null) {
//				response += line + "\n";
//			}

		getMessage().getAuthor().getOrCreatePMChannel().sendMessage(response);
	}
}