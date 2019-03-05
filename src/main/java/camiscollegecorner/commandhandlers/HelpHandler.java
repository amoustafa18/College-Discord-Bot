package camiscollegecorner.commandhandlers;

import camiscollegecorner.Constants;
import sx.blah.discord.handle.obj.IMessage;

import java.io.*;
import java.util.List;
import java.util.Scanner;

/** This class handles the help command. */
public class HelpHandler extends AbstractHandler {

	private static final String ERROR_LIST = "There was an error loading the commands list. Please try again later.";

	/** This constructs a HelpHandler. This object should handle the help command. The help
	 * command should DM the user with a list of commands taken from a text file. The user can then type
	 * CMDPREFIXHELP [command] to learn more about a command and how to use it.
	 * @param message The IMessage issuing this command.
	 */
	public HelpHandler(IMessage message) {
		super(message);

		List<Long> activeChannels = channelsActive();

		for(long l : Constants.ALL_CHANNELS_FLAG) {
			activeChannels.add(l);
		}
	}

	@Override
	public void run() {
		super.run();

		if(!shouldRun()) {
			return;
		}

		String line = "";
		String response = "";

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