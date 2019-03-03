package camiscollegecorner.commandhandlers;

import camiscollegecorner.Constants;
import sx.blah.discord.handle.obj.IMessage;

public class StopHandler extends AbstractHandler {

    public StopHandler(IMessage message) { super(message); }

    @Override
    public void run() {
      long author = getMessage().getAuthor().getLongID();
        if(author == Constants.WAFFLE_USER_ID  || author == Constants.STANA_USER_ID)
        {
            getMessage().getChannel().sendMessage("Shutting down");
            System.exit(0);
        }
        else
            getMessage().getChannel().sendMessage("You are not <@"+Constants.WAFFLE_USER_ID+">" +
                    " or <@" + Constants.STANA_USER_ID + ">. Please do not spam this command");
    }


}
