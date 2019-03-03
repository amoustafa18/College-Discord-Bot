package camiscollegecorner.commandhandlers;

import camiscollegecorner.Constants;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;

public class StopHandler extends AbstractHandler {

    public StopHandler(IMessage message) { super(message); }

    @Override
    public void run() {
      IUser author = getMessage().getAuthor();
      IGuild sourceGuild = getMessage().getGuild();
      IRole admin = sourceGuild.getRoleByID(Constants.ADMIN_ROLE_ID);
      IRole botteam = sourceGuild.getRoleByID(Constants.BOT_TEAM_ROLE_ID);

        if(author.hasRole(botteam) || author.hasRole(admin))
        {
            getMessage().getChannel().sendMessage("Shutting down");
            System.exit(0);
        }
        else
            getMessage().getChannel().sendMessage("You are not admin or on bot team" +
                    ". Ask bot team or any admin. Please do not spam this command");
    }


}
