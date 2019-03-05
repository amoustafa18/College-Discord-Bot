package camiscollegecorner.listeners.children.reactionlisteners.add;

import camiscollegecorner.Constants;
import camiscollegecorner.listeners.ReactionAddListener;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;

import java.util.List;

/** This class listens for reactions being added to the "read rules and react :ok:" message. */
public class ReadRulesReactionAddListener extends ReactionAddListener {

	/** The singleton instance of this class. */
	private static final ReadRulesReactionAddListener INSTANCE = new ReadRulesReactionAddListener();

	private ReadRulesReactionAddListener() {

	}

	@Override
	public void handleEvent(Event event) {
		ReactionAddEvent reactionAddEvent = (ReactionAddEvent) event;

		if(reactionAddEvent.getMessageID() == Constants.READ_RULES_MESSAGE_ID) {
			//we are dealing with the "react with squared OK to agree with rules" message
			if(Constants.OK_EMOJI.getName().equals(reactionAddEvent.getReaction().getEmoji().getName())) {
				List<IUser> reactors = reactionAddEvent.getReaction().getUsers();
				IRole visibleRole = reactionAddEvent.getGuild().getRoleByID(Constants.MEMBER_ROLE_ID);

				for(IUser user : reactors) {
					if(!user.hasRole(visibleRole) && !hasGradeRole(user, reactionAddEvent.getGuild())) {
						user.addRole(visibleRole);
					}
				}
			}
		}
	}

	public static ReadRulesReactionAddListener getInstance() {
		return INSTANCE;
	}
}