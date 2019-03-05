package camiscollegecorner.listeners;

import camiscollegecorner.Constants;
import camiscollegecorner.listeners.children.reactionlisteners.add.ChooseRolesReactionAddListener;
import camiscollegecorner.listeners.children.reactionlisteners.add.PinStarredMessageReactionAddListener;
import camiscollegecorner.listeners.children.reactionlisteners.add.ReadRulesReactionAddListener;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;

/** This class listens for reactions being added to messages. */
public class ReactionAddListener extends AbstractListener implements IListener<ReactionAddEvent> {

	public ReactionAddListener() {
		getChildListeners().add(ReadRulesReactionAddListener.getInstance());
		getChildListeners().add(ChooseRolesReactionAddListener.getInstance());
		getChildListeners().add(PinStarredMessageReactionAddListener.getInstance());
	}

	/** Called every time a reaction is added to a message. */
	@Override
	public void handle(ReactionAddEvent reactionAddEvent) {
		super.handleEvent(reactionAddEvent);
	}

	/**
	 * Checks whether or not a user has at least one of the following roles: HS freshman, HS sophomore, HS junior, HS
	 * senior, undergrad.
	 * @param user The user that is being checked
	 * @param guild The guild these roles are on
	 * @return Whether or not the user has one of these roles
	 */
	public boolean hasGradeRole(IUser user, IGuild guild) {
		IRole freshmanRole = guild.getRoleByID(Constants.FRESHMAN_ROLE_ID);
		IRole sophomoreRole = guild.getRoleByID(Constants.SOPHOMORE_ROLE_ID);
		IRole juniorRole = guild.getRoleByID(Constants.JUNIOR_ROLE_ID);
		IRole seniorRole = guild.getRoleByID(Constants.SENIOR_ROLE_ID);

		return user.hasRole(freshmanRole) || user.hasRole(sophomoreRole) ||
				user.hasRole(juniorRole) || user.hasRole(seniorRole);
	}
}