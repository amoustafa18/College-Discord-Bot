package camiscollegecorner.listeners.children.messagelisteners;

import camiscollegecorner.listeners.MessageListener;
import camiscollegecorner.minigames.Anagram;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.Random;

public class RandomMinigameStartListener extends MessageListener {

	/** For randomly choosing a minigame. */
	private Random random = new Random();

	/** The chances of a minigame starting per message per 10 000. For example, if this is x, there is likely to be x
	 *  minigames per 10 000 messages received. */
	private static final int MINIGAME_START_CHANCE = 20;

	/** The singleton instance of this class. */
	private static final RandomMinigameStartListener INSTANCE = new RandomMinigameStartListener();

	private RandomMinigameStartListener() {

	}

	@Override
	public void handleEvent(Event event) {
		MessageReceivedEvent messageEvent = (MessageReceivedEvent) event;

		if(currentMinigame != null) {
			//the active minigame should handle all message events while it is active
			currentMinigame.handleMessage(messageEvent);
		} else {
			if(random.nextInt(10001) <= MINIGAME_START_CHANCE) {
				//randomly pick a minigame
				currentMinigame = new Anagram(messageEvent.getChannel(), null, true);
				currentMinigame.startGame();
			}
		}
	}

	public static RandomMinigameStartListener getInstance() {
		return INSTANCE;
	}
}