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

public class ReactionListener implements IListener<ReactionAddEvent> {
	private static final ReactionEmoji OK_EMOJI = ReactionEmoji.of("\u1F197");
	private static final ReactionEmoji CHICKEN_EGG_EMOJI = ReactionEmoji.of("\u1F95A");
	private static final ReactionEmoji HATCHING_EMOJI = ReactionEmoji.of("\u1F423");
	private static final ReactionEmoji HATCHED_EMOJI = ReactionEmoji.of("\u1F425");
	private static final ReactionEmoji BABY_CHICK_EMOJI = ReactionEmoji.of("\u1F424");
	private static final ReactionEmoji BIRD_EMOJI = ReactionEmoji.of("\u1F426");
	private static final ReactionEmoji WHITE_CIRCLE_EMOJI = ReactionEmoji.of("\u26AA");
	private static final ReactionEmoji BLUE_CIRCLE_EMOJI = ReactionEmoji.of("\u1F535");
	private static final ReactionEmoji RED_CIRCLE_EMOJI = ReactionEmoji.of("\u1F534");
	private static final ReactionEmoji EVERGREEN_EMOJI = ReactionEmoji.of("\u1F332");
	private static final ReactionEmoji AIRPLANE_EMOJI = ReactionEmoji.of("\u2708");
	private static final ReactionEmoji WARNING_EMOJI = ReactionEmoji.of("\u26A0");

	private static final long READ_RULES_MESSAGE_ID = 551168954714947605l;
	private static final long CHOOSE_ROLES_MESSAGE_ID = 551182441092481025l;
	private static final long VISIBLE_ROLE_ID = 551171218485805083l;
	private static final long FRESHMAN_ROLE_ID = 550353511699841034l;
	private static final long SOPHOMORE_ROLE_ID = 550353490086723584l;
	private static final long JUNIOR_ROLE_ID = 550353218337636414l;
	private static final long SENIOR_ROLE_ID = 550353223920254980l;
	private static final long UNDERGRAD_ROLE_ID = 550353186871836673l;
	private static final long THEY_ROLE_ID = 551167505918590989l;
	private static final long SHE_ROLE_ID = 551167419050360833l;
	private static final long HE_ROLE_ID = 551167286673801237l;
	private static final long TRANSFER_ROLE_ID = 550377261044334592l;
	private static final long INTERNATIONAL_ROLE_ID = 551066986784751626l;
	private static final long NSFW_ROLE_ID = 550381039697133588l;

	@Override
	public void handle(ReactionAddEvent reactionAddEvent) {
		System.out.println("Reaction detected");
		//System.out.println(reactionAddEvent.getMessageID());
		System.out.println(READ_RULES_MESSAGE_ID);

		if(reactionAddEvent.getMessageID() == READ_RULES_MESSAGE_ID) {
			System.out.println("Is the right message");
			//we are dealing with the "react with squared OK to agree with rules" message
			if(OK_EMOJI.getLongID() == reactionAddEvent.getReaction().getEmoji().getLongID()) {
				List<IUser> reactors = reactionAddEvent.getReaction().getUsers();
				IRole visibleRole = reactionAddEvent.getGuild().getRoleByID(VISIBLE_ROLE_ID);

				String reactorsString = "";
				for(IUser user : reactors) {
					reactorsString += user.getName() + " ";
					if(!user.hasRole(visibleRole) && !hasGradeRole(user, reactionAddEvent.getGuild())) {
						user.addRole(visibleRole);
					}
				}

				//TODO only for debug purposes
				reactionAddEvent.getChannel().sendMessage("Reaction detected by " + reactorsString);
			}
		} else if(reactionAddEvent.getMessageID() == CHOOSE_ROLES_MESSAGE_ID) {
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

			if(reactionId == CHICKEN_EGG_EMOJI.getLongID()) {
				//freshman
				if(!hasGradeRole(reactor, reactionAddEvent.getGuild())) {
					reactor.addRole(sourceGuild.getRoleByID(FRESHMAN_ROLE_ID));
				}
			} else if(reactionId == HATCHING_EMOJI.getLongID()) {
				//sophomore
				if(!hasGradeRole(reactor, reactionAddEvent.getGuild())) {
					reactor.addRole(sourceGuild.getRoleByID(SOPHOMORE_ROLE_ID));
				}
			} else if(reactionId == HATCHED_EMOJI.getLongID()) {
				//junior
				if(!hasGradeRole(reactor, reactionAddEvent.getGuild())) {
					reactor.addRole(sourceGuild.getRoleByID(JUNIOR_ROLE_ID));
				}
			} else if(reactionId == BABY_CHICK_EMOJI.getLongID()) {
				//senior
				if(!hasGradeRole(reactor, reactionAddEvent.getGuild())) {
					reactor.addRole(sourceGuild.getRoleByID(SENIOR_ROLE_ID));
				}
			} else if(reactionId == BIRD_EMOJI.getLongID()) {
				//undergrad
				if(!hasGradeRole(reactor, reactionAddEvent.getGuild())) {
					reactor.addRole(sourceGuild.getRoleByID(UNDERGRAD_ROLE_ID));
				}
			} else if(reactionId == WHITE_CIRCLE_EMOJI.getLongID()) {
				//they
				reactor.addRole(sourceGuild.getRoleByID(THEY_ROLE_ID));
			} else if(reactionId == BLUE_CIRCLE_EMOJI.getLongID()) {
				//he
				reactor.addRole(sourceGuild.getRoleByID(HE_ROLE_ID));
			} else if(reactionId == RED_CIRCLE_EMOJI.getLongID()) {
				//she
				reactor.addRole(sourceGuild.getRoleByID(SHE_ROLE_ID));
			} else if(reactionId == EVERGREEN_EMOJI.getLongID()) {
				//transfer
				reactor.addRole(sourceGuild.getRoleByID(TRANSFER_ROLE_ID));
			} else if(reactionId == AIRPLANE_EMOJI.getLongID()) {
				//international
				reactor.addRole(sourceGuild.getRoleByID(INTERNATIONAL_ROLE_ID));
			} else if(reactionId == WARNING_EMOJI.getLongID()) {
				//not so safe
				reactor.addRole(sourceGuild.getRoleByID(NSFW_ROLE_ID));
			}
		}
	}

	private boolean hasGradeRole(IUser user, IGuild guild) {
		IRole freshmanRole = guild.getRoleByID(FRESHMAN_ROLE_ID);
		IRole sophomoreRole = guild.getRoleByID(SOPHOMORE_ROLE_ID);
		IRole juniorRole = guild.getRoleByID(JUNIOR_ROLE_ID);
		IRole seniorRole = guild.getRoleByID(SENIOR_ROLE_ID);

		return user.hasRole(freshmanRole) || user.hasRole(sophomoreRole) ||
				user.hasRole(juniorRole) || user.hasRole(seniorRole);
	}

	public void handleCommand(IMessage message) {
		CmdHandler handler = new CmdHandler(message);
		handler.execute();
	}
}