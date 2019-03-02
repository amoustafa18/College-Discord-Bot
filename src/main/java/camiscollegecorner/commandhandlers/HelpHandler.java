package camiscollegecorner.commandhandlers;

import camiscollegecorner.Constants;
import sx.blah.discord.handle.obj.IMessage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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

		String response = "";

		try {
			commandList = new File(Constants.COMMAND_LIST);

			br = new BufferedReader(new FileReader(commandList));

			String line = "";

			while((line = br.readLine()) != null) {
				response += line + "\n";
			}

		} catch(IOException ex) {
			getMessage().getChannel().sendMessage(ERROR_LIST);
		}

		getMessage().getAuthor().getOrCreatePMChannel().sendMessage(response);
	}
}