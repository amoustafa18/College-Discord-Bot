package camiscollegecorner.listeners.children.reactionlisteners.add;

import camiscollegecorner.Constants;
import camiscollegecorner.listeners.ReactionAddListener;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;

/** This class handles the system of users reacting to a message with particular emojis corresponding to the role
 * they want. */
public class ChooseRolesReactionAddListener extends ReactionAddListener {

	/** The singleton instance of this class. */
	private static final ChooseRolesReactionAddListener INSTANCE = new ChooseRolesReactionAddListener();

	private ChooseRolesReactionAddListener() {

	}

	@Override
	public void handleEvent(Event event) {
		ReactionAddEvent reactionAddEvent = (ReactionAddEvent) event;

		if(reactionAddEvent.getMessageID() == Constants.CHOOSE_ROLES_MESSAGE_ID) {
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
				if(!reactor.hasRole(he)) {
					reactor.addRole(he);
				}
			} else if(reactionName.equals(Constants.BLUE_CIRCLE_EMOJI.getName())) {
				//she
				IRole she = sourceGuild.getRoleByID(Constants.SHE_ROLE_ID);

				if(!reactor.hasRole(she)) {
					reactor.addRole(she);
				}
			} else if(reactionName.equals(Constants.EVERGREEN_EMOJI.getName())) {
				//transfer
				IRole transfer = sourceGuild.getRoleByID(Constants.TRANSFER_ROLE_ID);

				if(!reactor.hasRole(transfer)) {
					reactor.addRole(transfer);
				}
			} else if(reactionName.equals(Constants.AIRPLANE_EMOJI.getName())) {
				//international
				IRole international = sourceGuild.getRoleByID(Constants.INTERNATIONAL_ROLE_ID);

				reactor.addRole(international);
			} else if(reactionName.equals(Constants.WARNING_EMOJI.getName())) {
				//not so safe
				IRole nsfw = sourceGuild.getRoleByID(Constants.NSFW_ROLE_ID);

				if(!reactor.hasRole(nsfw)) {
					reactor.addRole(nsfw);
				}
			} else if(reactionName.equals(Constants.CHICKEN_EMOJI.getName())) {
				//alum
				IRole alum = sourceGuild.getRoleByID(Constants.ALUM_ROLE_ID);

				if(!reactor.hasRole(alum)) {
					reactor.addRole(alum);
				}
			} else if(reactionName.equals(Constants.HEART_DECORATION_EMOJI.getName())) {
				//gap year
				IRole gap = sourceGuild.getRoleByID(Constants.GAP_YEAR_ROLE_ID);

				if(!reactor.hasRole(gap)) {
					reactor.addRole(gap);
				}
			}
		}
	}

	public static ChooseRolesReactionAddListener getInstance() {
		return INSTANCE;
	}
}