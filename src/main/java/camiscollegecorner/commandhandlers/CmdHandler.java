package camiscollegecorner.commandhandlers;

import camiscollegecorner.Constants;
import camiscollegecorner.reddit.CatImageGrabber;
import sx.blah.discord.handle.obj.IMessage;

/** A class that handles all received messages starting with {@code Constants.CMD_PREFIX} */
public class CmdHandler {

    /** The message that begins with {@code Constants.CMD_PREFIX} */
    private IMessage message;

    private static final String INVALID_USAGE = "Invalid use of command.";

    private static final String CMD_HELP = "help";
    private static final String CMD_PING = "ping";
    private static final String CMD_CAT = "cat";
    private static final String CMD_STOP = "stop";
    private static final String CMD_PRESTON = "preston";
    private CatImageGrabber catImageGrabber;

    public CmdHandler(IMessage message) {
        this.message = message;
        this.catImageGrabber = CatImageGrabber.getInstance();

    }

    /**
     * Dispatches an AbstractHandler based on the command called.
     */
    public void handle() {
        String content = message.getContent().toLowerCase();
        //remove the command prefix:
        content = content.substring(Constants.CMD_PREFIX.length());

        if(content.equals(CMD_HELP)) {
            HelpHandler h = new HelpHandler(message);
            h.start();
        } else if(content.equals(CMD_PING)) {
            PingHandler h = new PingHandler(message);
            h.start();
        } else if(content.equals(CMD_CAT)) {
            CatHandler h = new CatHandler(message);
            h.start();
        }else if(content.equals(CMD_STOP)){
           StopHandler h = new StopHandler(message);
           h.start();
        }else if(content.equals(CMD_PRESTON)){
            PrestonHandler h = new PrestonHandler(message);
            h.start();
        }

    }
}