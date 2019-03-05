package camiscollegecorner.commandhandlers;

import camiscollegecorner.Constants;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;

import java.util.List;

/** This class handles the stop command. */
public class StopHandler extends AbstractHandler {

    /** This constructs a StopHandler. This object should handle the stop command. The stop
     * command should close this program, logging the bot offline.
     * @param message The IMessage issuing this command.
     */
    public StopHandler(IMessage message) {
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

        IUser author = getMessage().getAuthor();
        IGuild sourceGuild = getMessage().getGuild();
        IRole admin = sourceGuild.getRoleByID(Constants.ADMIN_ROLE_ID);
        IRole botteam = sourceGuild.getRoleByID(Constants.BOT_TEAM_ROLE_ID);

        if(author.hasRole(botteam) || author.hasRole(admin)) {
            getMessage().getChannel().sendMessage("Shutting down");
            System.exit(0);
        }
    }
}