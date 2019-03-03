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
		File commandList = null;
		BufferedReader br = null;
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(Constants.COMMAND_LIST);
		System.out.println(is);


		Properties config = new Properties();
		File file = new File(Constants.COMMAND_LIST);
		if(file.exists()){
			try(FileInputStream in = new FileInputStream(file)){
				config.load(in);
				System.out.println("it works");
			}catch(IOException e){
				e.printStackTrace();
				System.out.println("\nThere was an error reading the config file...");
			}
		}

		String response = "";

		getMessage().getAuthor().getOrCreatePMChannel().sendMessage(response);
	}
}