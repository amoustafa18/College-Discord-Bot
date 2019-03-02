package camiscollegecorner;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;
import sx.blah.discord.handle.impl.obj.Guild;
import sx.blah.discord.handle.impl.obj.ReactionEmoji;
import sx.blah.discord.handle.impl.obj.Role;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.RoleBuilder;

import java.util.List;

public class ReactionAddListener implements IListener<ReactionAddEvent> {
	@Override
	public void handle(ReactionAddEvent reactionAddEvent) {
		if(reactionAddEvent.getMessageID() == Constants.READ_RULES_MESSAGE_ID) {
			//we are dealing with the "react with squared OK to agree with rules" message
			if(Constants.OK_EMOJI.getLongID() == reactionAddEvent.getReaction().getEmoji().getLongID()) {
				List<IUser> reactors = reactionAddEvent.getReaction().getUsers();
				IRole visibleRole = reactionAddEvent.getGuild().getRoleByID(Constants.VISIBLE_ROLE_ID);

				String reactorsString = "";
				for(IUser user : reactors) {
					reactorsString += user.getName() + " ";
					if(!user.hasRole(visibleRole) && !hasGradeRole(user, reactionAddEvent.getGuild())) {
						user.addRole(visibleRole);
					}
				}

				//TODO only for debug purposes
				//reactionAddEvent.getChannel().sendMessage("Reaction detected by " + reactorsString);
			}
		} else if(reactionAddEvent.getMessageID() == Constants.CHOOSE_ROLES_MESSAGE_ID) {
			/*
				We are dealing with the "choose your roles" message

				A user can only have one of:
				HS Freshman
				HS Sophomore
				HS Junior
				HS Senior
				Undergrad

				A use can have either to all of these as they please:
				they/them/theirs
				she/her/hers
				he/him/his
				Transfer
				International
			 */

			long reactionId = reactionAddEvent.getReaction().getEmoji().getLongID();
			IUser reactor = reactionAddEvent.getUser();
			IGuild sourceGuild = reactionAddEvent.getGuild();

			if(reactionId == Constants.CHICKEN_EGG_EMOJI.getLongID()) {
				//freshman
				if(!hasGradeRole(reactor, reactionAddEvent.getGuild())) {
					reactor.addRole(sourceGuild.getRoleByID(Constants.FRESHMAN_ROLE_ID));
				}
			} else if(reactionId == Constants.HATCHING_EMOJI.getLongID()) {
				//sophomore
				if(!hasGradeRole(reactor, reactionAddEvent.getGuild())) {
					reactor.addRole(sourceGuild.getRoleByID(Constants.SOPHOMORE_ROLE_ID));
				}
			} else if(reactionId == Constants.HATCHED_EMOJI.getLongID()) {
				//junior
				if(!hasGradeRole(reactor, reactionAddEvent.getGuild())) {
					reactor.addRole(sourceGuild.getRoleByID(Constants.JUNIOR_ROLE_ID));
				}
			} else if(reactionId == Constants.BABY_CHICK_EMOJI.getLongID()) {
				//senior
				if(!hasGradeRole(reactor, reactionAddEvent.getGuild())) {
					reactor.addRole(sourceGuild.getRoleByID(Constants.SENIOR_ROLE_ID));
				}
			} else if(reactionId == Constants.BIRD_EMOJI.getLongID()) {
				//undergrad
				if(!hasGradeRole(reactor, reactionAddEvent.getGuild())) {
					reactor.addRole(sourceGuild.getRoleByID(Constants.UNDERGRAD_ROLE_ID));
				}
			} else if(reactionId == Constants.WHITE_CIRCLE_EMOJI.getLongID()) {
				//they
				reactor.addRole(sourceGuild.getRoleByID(Constants.THEY_ROLE_ID));
			} else if(reactionId == Constants.BLUE_CIRCLE_EMOJI.getLongID()) {
				//he
				reactor.addRole(sourceGuild.getRoleByID(Constants.HE_ROLE_ID));
			} else if(reactionId == Constants.RED_CIRCLE_EMOJI.getLongID()) {
				//she
				reactor.addRole(sourceGuild.getRoleByID(Constants.SHE_ROLE_ID));
			} else if(reactionId == Constants.EVERGREEN_EMOJI.getLongID()) {
				//transfer
				reactor.addRole(sourceGuild.getRoleByID(Constants.TRANSFER_ROLE_ID));
			} else if(reactionId == Constants.AIRPLANE_EMOJI.getLongID()) {
				//international
				reactor.addRole(sourceGuild.getRoleByID(Constants.INTERNATIONAL_ROLE_ID));
			} else if(reactionId == Constants.WARNING_EMOJI.getLongID()) {
				//not so safe
				reactor.addRole(sourceGuild.getRoleByID(Constants.NSFW_ROLE_ID));
			}
		}
	}

	private boolean hasGradeRole(IUser user, IGuild guild) {
		IRole freshmanRole = guild.getRoleByID(Constants.FRESHMAN_ROLE_ID);
		IRole sophomoreRole = guild.getRoleByID(Constants.SOPHOMORE_ROLE_ID);
		IRole juniorRole = guild.getRoleByID(Constants.JUNIOR_ROLE_ID);
		IRole seniorRole = guild.getRoleByID(Constants.SENIOR_ROLE_ID);

		return user.hasRole(freshmanRole) || user.hasRole(sophomoreRole) ||
				user.hasRole(juniorRole) || user.hasRole(seniorRole);
	}

	public void handleCommand(IMessage message) {
		CmdHandler handler = new CmdHandler(message);
		handler.execute();
	}
}