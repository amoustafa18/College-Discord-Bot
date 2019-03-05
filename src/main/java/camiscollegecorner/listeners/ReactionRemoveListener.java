package camiscollegecorner.listeners;

import camiscollegecorner.listeners.children.reactionlisteners.remove.ChooseRolesReactionRemoveListener;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionRemoveEvent;

/** This class listens for reactions being deleted from messages. */
public class ReactionRemoveListener extends AbstractListener implements IListener<ReactionRemoveEvent> {

	public ReactionRemoveListener() {
		getChildListeners().add(ChooseRolesReactionRemoveListener.getInstance());
	}

	@Override
	public void handle(ReactionRemoveEvent reactionRemoveEvent) {
		super.handleEvent(reactionRemoveEvent);
	}
}