package camiscollegecorner;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionRemoveEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;

public class ReactionDeleteListener implements IListener<ReactionRemoveEvent> {

	@Override
	public void handle(ReactionRemoveEvent reactionRemoveEvent) {
		if(reactionRemoveEvent.getMessageID() == Constants.CHOOSE_ROLES_MESSAGE_ID) {
			//we are dealing with choose roles message
			IUser reactor = reactionRemoveEvent.getUser();
			IGuild sourceGuild = reactionRemoveEvent.getGuild();
			long reactionId = reactionRemoveEvent.getReaction().getEmoji().getLongID();

			if(reactionId == Constants.CHICKEN_EGG_EMOJI.getLongID()) {
				//freshman
				IRole freshmanRole = sourceGuild.getRoleByID(Constants.FRESHMAN_ROLE_ID);

				if(reactor.hasRole(freshmanRole))
				reactor.removeRole(freshmanRole);
			} else if(reactionId == Constants.HATCHING_EMOJI.getLongID()) {
				//sophomore
				IRole sophomoreRole = sourceGuild.getRoleByID(Constants.SOPHOMORE_ROLE_ID);

				reactor.removeRole(sophomoreRole);
			} else if(reactionId == Constants.HATCHED_EMOJI.getLongID()) {
				//junior
				IRole juniorRole = sourceGuild.getRoleByID(Constants.JUNIOR_ROLE_ID);

				reactor.removeRole(juniorRole);
			} else if(reactionId == Constants.BABY_CHICK_EMOJI.getLongID()) {
				//senior
				IRole seniorRole = sourceGuild.getRoleByID(Constants.SENIOR_ROLE_ID);

				reactor.removeRole(seniorRole);
			} else if(reactionId == Constants.BIRD_EMOJI.getLongID()) {
				//undergrad
				IRole undergradRole = sourceGuild.getRoleByID(Constants.UNDERGRAD_ROLE_ID);

				reactor.removeRole(undergradRole);
			} else if(reactionId == Constants.WHITE_CIRCLE_EMOJI.getLongID()) {
				//they
				IRole theyRole = sourceGuild.getRoleByID(Constants.THEY_ROLE_ID);

				reactor.removeRole(theyRole);
			} else if(reactionId == Constants.BLUE_CIRCLE_EMOJI.getLongID()) {
				//he
				IRole heRole = sourceGuild.getRoleByID(Constants.HE_ROLE_ID);

				reactor.removeRole(heRole);
			} else if(reactionId == Constants.RED_CIRCLE_EMOJI.getLongID()) {
				//she
				IRole sheRole = sourceGuild.getRoleByID(Constants.SHE_ROLE_ID);

				reactor.removeRole(sheRole);
			} else if(reactionId == Constants.EVERGREEN_EMOJI.getLongID()) {
				//transfer
				IRole transferRole = sourceGuild.getRoleByID(Constants.TRANSFER_ROLE_ID);

				reactor.removeRole(transferRole);
			} else if(reactionId == Constants.AIRPLANE_EMOJI.getLongID()) {
				//international
				IRole internationalRole = sourceGuild.getRoleByID(Constants.INTERNATIONAL_ROLE_ID);

				reactor.removeRole(internationalRole);
			} else if(reactionId == Constants.WARNING_EMOJI.getLongID()) {
				//not so safe
				IRole nsfwRole = sourceGuild.getRoleByID(Constants.NSFW_ROLE_ID);

				reactor.removeRole(nsfwRole);
			}
		}
	}
}