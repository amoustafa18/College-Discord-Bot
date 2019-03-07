package camiscollegecorner.listeners.children.reactionlisteners.remove;

import camiscollegecorner.Constants;
import camiscollegecorner.listeners.ReactionRemoveListener;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionRemoveEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;

/** This class listens for reactions being removed from the "choose roles" message. */
public class ChooseRolesReactionRemoveListener extends ReactionRemoveListener {

	/** The singleton instance of this class. */
	private static final ChooseRolesReactionRemoveListener INSTANCE = new ChooseRolesReactionRemoveListener();

	private ChooseRolesReactionRemoveListener() {

	}

	@Override
	public void handleEvent(Event event) {
		ReactionRemoveEvent reactionRemoveEvent = (ReactionRemoveEvent) event;

		if(reactionRemoveEvent.getMessageID() == Constants.CHOOSE_ROLES_MESSAGE_ID) {
			//we are dealing with choose roles message
			IUser reactor = reactionRemoveEvent.getUser();
			IGuild sourceGuild = reactionRemoveEvent.getGuild();
			String reactionName = reactionRemoveEvent.getReaction().getEmoji().getName();

			if(reactionName.equals(Constants.CHICKEN_EGG_EMOJI.getName())) {
				//freshman
				IRole freshmanRole = sourceGuild.getRoleByID(Constants.FRESHMAN_ROLE_ID);

				if(reactor.hasRole(freshmanRole))
					reactor.removeRole(freshmanRole);
			} else if(reactionName.equals(Constants.HATCHING_EMOJI.getLongID())) {
				//sophomore
				IRole sophomoreRole = sourceGuild.getRoleByID(Constants.SOPHOMORE_ROLE_ID);

				reactor.removeRole(sophomoreRole);
			} else if(reactionName.equals(Constants.HATCHED_EMOJI.getName())) {
				//junior
				IRole juniorRole = sourceGuild.getRoleByID(Constants.JUNIOR_ROLE_ID);

				reactor.removeRole(juniorRole);
			} else if(reactionName.equals(Constants.BABY_CHICK_EMOJI.getName())) {
				//senior
				IRole seniorRole = sourceGuild.getRoleByID(Constants.SENIOR_ROLE_ID);

				reactor.removeRole(seniorRole);
			} else if(reactionName.equals(Constants.BIRD_EMOJI.getName())) {
				//undergrad
				IRole undergradRole = sourceGuild.getRoleByID(Constants.UNDERGRAD_ROLE_ID);

				reactor.removeRole(undergradRole);
			} else if(reactionName.equals(Constants.WHITE_CIRCLE_EMOJI.getName())) {
				//they
				IRole theyRole = sourceGuild.getRoleByID(Constants.THEY_ROLE_ID);

				reactor.removeRole(theyRole);
			} else if(reactionName.equals(Constants.BLUE_CIRCLE_EMOJI.getName())) {
				//he
				IRole heRole = sourceGuild.getRoleByID(Constants.HE_ROLE_ID);

				reactor.removeRole(heRole);
			} else if(reactionName.equals(Constants.RED_CIRCLE_EMOJI.getName())) {
				//she
				IRole sheRole = sourceGuild.getRoleByID(Constants.SHE_ROLE_ID);

				reactor.removeRole(sheRole);
			} else if(reactionName.equals(Constants.EVERGREEN_EMOJI.getName())) {
				//transfer
				IRole transferRole = sourceGuild.getRoleByID(Constants.TRANSFER_ROLE_ID);

				reactor.removeRole(transferRole);
			} else if(reactionName.equals(Constants.AIRPLANE_EMOJI.getName())) {
				//international
				IRole internationalRole = sourceGuild.getRoleByID(Constants.INTERNATIONAL_ROLE_ID);

				reactor.removeRole(internationalRole);
			} else if(reactionName.equals(Constants.WARNING_EMOJI.getName())) {
				//not so safe
				IRole nsfwRole = sourceGuild.getRoleByID(Constants.NSFW_ROLE_ID);

				reactor.removeRole(nsfwRole);
			}
			else if(reactionName.equals(Constants.CHICKEN_EMOJI.getName())) {
				//alum
				IRole alum = sourceGuild.getRoleByID(Constants.ALUM_ROLE_ID);

				reactor.removeRole(alum);
			}else if(reactionName.equals(Constants.HEART_DECORATION_EMOJI.getName())) {
				//gap year
				IRole gap = sourceGuild.getRoleByID(Constants.GAP_YEAR_ROLE_ID);

				reactor.removeRole(gap);
			}
		}
	}

	public static ChooseRolesReactionRemoveListener getInstance() {
		return INSTANCE;
	}
}
