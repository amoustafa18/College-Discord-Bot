package camiscollegecorner.listeners;

import camiscollegecorner.Constants;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;

import java.util.List;

/** This class listens for reactions being added to messages. */
public class ReactionAddListener implements IListener<ReactionAddEvent> {

	@Override
	public void handle(ReactionAddEvent reactionAddEvent) {
		long sourceChannelId = reactionAddEvent.getChannel().getLongID();

		if(reactionAddEvent.getMessageID() == Constants.READ_RULES_MESSAGE_ID) {
			//we are dealing with the "react with squared OK to agree with rules" message
			if(Constants.OK_EMOJI.getName().equals(reactionAddEvent.getReaction().getEmoji().getName())) {
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

				Rules:
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

			String reactionName = reactionAddEvent.getReaction().getEmoji().getName();
			IUser reactor = reactionAddEvent.getUser();
			IGuild sourceGuild = reactionAddEvent.getGuild();

			if(reactionName.equals(Constants.CHICKEN_EGG_EMOJI.getName())) {
				//freshman
				if(!hasGradeRole(reactor, reactionAddEvent.getGuild())) {
					reactor.addRole(sourceGuild.getRoleByID(Constants.FRESHMAN_ROLE_ID));
				}
			} else if(reactionName.equals(Constants.HATCHING_EMOJI.getName())) {
				//sophomore
				if(!hasGradeRole(reactor, reactionAddEvent.getGuild())) {
					reactor.addRole(sourceGuild.getRoleByID(Constants.SOPHOMORE_ROLE_ID));
				}
			} else if(reactionName.equals(Constants.HATCHED_EMOJI.getName())) {
				//junior
				if(!hasGradeRole(reactor, reactionAddEvent.getGuild())) {
					reactor.addRole(sourceGuild.getRoleByID(Constants.JUNIOR_ROLE_ID));
				}
			} else if(reactionName.equals(Constants.BABY_CHICK_EMOJI.getName())) {
				//senior
				if(!hasGradeRole(reactor, reactionAddEvent.getGuild())) {
					reactor.addRole(sourceGuild.getRoleByID(Constants.SENIOR_ROLE_ID));
				}
			} else if(reactionName.equals(Constants.BIRD_EMOJI.getName())) {
				//undergrad
				if(!hasGradeRole(reactor, reactionAddEvent.getGuild())) {
					reactor.addRole(sourceGuild.getRoleByID(Constants.UNDERGRAD_ROLE_ID));
				}
			} else if(reactionName.equals(Constants.WHITE_CIRCLE_EMOJI.getName())) {
				//they
				IRole they = sourceGuild.getRoleByID(Constants.THEY_ROLE_ID);

				if(!reactor.hasRole(they)) {
					reactor.addRole(sourceGuild.getRoleByID(Constants.THEY_ROLE_ID));
				}
			} else if(reactionName.equals(Constants.RED_CIRCLE_EMOJI.getName())) {
				//he
				IRole he = sourceGuild.getRoleByID(Constants.HE_ROLE_ID);

				reactor.addRole(he);
			} else if(reactionName.equals(Constants.BLUE_CIRCLE_EMOJI.getName())) {
				//she
				IRole she = sourceGuild.getRoleByID(Constants.SHE_ROLE_ID);

				reactor.addRole(she);
			} else if(reactionName.equals(Constants.EVERGREEN_EMOJI.getName())) {
				//transfer
				IRole transfer = sourceGuild.getRoleByID(Constants.TRANSFER_ROLE_ID);

				reactor.addRole(transfer);
			} else if(reactionName.equals(Constants.AIRPLANE_EMOJI.getName())) {
				//international
				IRole international = sourceGuild.getRoleByID(Constants.INTERNATIONAL_ROLE_ID);

				reactor.addRole(international);
			} else if(reactionName.equals(Constants.WARNING_EMOJI.getName())) {
				//not so safe
				IRole nsfw = sourceGuild.getRoleByID(Constants.NSFW_ROLE_ID);

				reactor.addRole(nsfw);
			}else if(reactionName.equals(Constants.CHICKEN_EMOJI.getName())) {
				//alum
				IRole alum = sourceGuild.getRoleByID(Constants.ALUM_ROLE_ID);

				reactor.addRole(alum);
			}else if(reactionName.equals(Constants.HEART_DECORATION_EMOJI.getName())) {
				//gap year
				IRole gap = sourceGuild.getRoleByID(Constants.GAP_YEAR_ROLE_ID);

				reactor.addRole(gap);
			}


		} else if(reactionAddEvent.getReaction().getEmoji().getLongID() == Constants.STAR_EMOJI.getLongID() &&
				sourceChannelId == Constants.GENERAL_CHANNEL_ID || sourceChannelId == Constants.NSFW_GENERAL_CHANNEL_ID ||
				sourceChannelId == Constants.VIDEOS_AND_MEMES_CHANNEL_ID || sourceChannelId == Constants.NSFW_MEMES_CHANNEL_ID) {
			//pin message if it gets certain number of star reacts
			if(reactionAddEvent.getReaction().getUsers().size() >= Constants.PIN_STAR_REACT_COUNT) {
				IMessage sourceMessage = reactionAddEvent.getMessage();
				reactionAddEvent.getChannel().pin(sourceMessage);
			}

		}
	}

	/**
	 * Checks whether or not a user has at least one of the following roles: HS freshman, HS sophomore, HS junior, HS
	 * senior, undergrad.
	 * @param user The user that is being checked
	 * @param guild The guild these roles are on
	 * @return Whether or not the user has one of these roles
	 */
	private boolean hasGradeRole(IUser user, IGuild guild) {
		IRole freshmanRole = guild.getRoleByID(Constants.FRESHMAN_ROLE_ID);
		IRole sophomoreRole = guild.getRoleByID(Constants.SOPHOMORE_ROLE_ID);
		IRole juniorRole = guild.getRoleByID(Constants.JUNIOR_ROLE_ID);
		IRole seniorRole = guild.getRoleByID(Constants.SENIOR_ROLE_ID);

		return user.hasRole(freshmanRole) || user.hasRole(sophomoreRole) ||
				user.hasRole(juniorRole) || user.hasRole(seniorRole);
	}
}