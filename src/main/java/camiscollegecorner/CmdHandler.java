package camiscollegecorner;

import sx.blah.discord.handle.obj.IMessage;

import java.io.*;

public class CmdHandler {

    private IMessage message;

    private static final String INVALID_USAGE = "Invalid use of command.";

    private static final String CMD_HELP = "help";
    private static final String ERROR_LIST = "There was an error loading the commands list. Please try again later.";

    public CmdHandler(IMessage message) {
        this.message = message;
    }

    public void execute() {
        String content = message.getContent().toLowerCase();

        //remove the command prefix:
        content = content.substring(Main.CMD_PREFIX.length());

        if(content.startsWith(CMD_HELP)) {
            handleHelp(content);
        }
    }

    public void handleHelp(String content) {
        File commandList = null;
        BufferedReader br = null;

        String response = "";

        try {
            commandList = new File(Main.COMMAND_LIST);

            br = new BufferedReader(new FileReader(commandList));

            String line = "";

            while((line = br.readLine()) != null) {
                response += line + "\n";
            }

        } catch(IOException ex) {
            message.getChannel().sendMessage(ERROR_LIST);
        }

        message.getAuthor().getOrCreatePMChannel().sendMessage(response);
    }
}