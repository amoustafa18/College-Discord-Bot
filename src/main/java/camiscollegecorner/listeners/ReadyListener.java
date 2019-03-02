package camiscollegecorner.listeners;

import camiscollegecorner.Constants;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.ActivityType;
import sx.blah.discord.handle.obj.StatusType;

/** This class listens for when our bot client is logged in and ready. */
public class ReadyListener implements IListener<ReadyEvent> {

	@Override
	public void handle(ReadyEvent readyEvent) {
		readyEvent.getClient().changePresence(StatusType.ONLINE, ActivityType.PLAYING, Constants.PLAYING_TEXT);
	}
}