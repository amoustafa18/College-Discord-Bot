package camiscollegecorner.listeners.children.reactionlisteners.add;

import camiscollegecorner.Constants;
import camiscollegecorner.listeners.ReactionAddListener;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;
import sx.blah.discord.handle.obj.IMessage;

/** This class handles the system of users reacting to a message with stars to get it pinned. */
public class PinStarredMessageReactionAddListener extends ReactionAddListener {

	/** The singleton instance of this class. */
	private static final PinStarredMessageReactionAddListener INSTANCE = new PinStarredMessageReactionAddListener();

	private PinStarredMessageReactionAddListener() {

	}

	@Override
	public void handleEvent(Event event) {
		ReactionAddEvent reactionAddEvent = (ReactionAddEvent) event;
		long sourceChannelId = reactionAddEvent.getChannel().getLongID();

		if((reactionAddEvent.getReaction().getEmoji().getName().equals(Constants.STAR_EMOJI.getName())) &&
				(sourceChannelId == Constants.GENERAL_CHANNEL_ID || sourceChannelId == Constants.NSFW_GENERAL_CHANNEL_ID ||
						sourceChannelId == Constants.VIDEOS_AND_MEMES_CHANNEL_ID || sourceChannelId == Constants.NSFW_MEMES_CHANNEL_ID)) {
			//pin message if it gets certain number of star reacts

			if(reactionAddEvent.getReaction().getUsers().size() == Constants.PIN_STAR_REACT_COUNT) {
				IMessage sourceMessage = reactionAddEvent.getMessage();
				reactionAddEvent.getChannel().pin(sourceMessage);
			}

		}
	}

	public static PinStarredMessageReactionAddListener getInstance() {
		return INSTANCE;
	}
}