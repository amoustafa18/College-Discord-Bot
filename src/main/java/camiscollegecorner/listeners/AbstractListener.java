package camiscollegecorner.listeners;

import sx.blah.discord.api.events.Event;

import java.util.ArrayList;
import java.util.List;

/** This class is an abstract listener. It stores a list of references to its child listeners and calls them each
 * time it is triggered.
 */
public abstract class AbstractListener {

	/** The list of child listeners to call each time an event is triggered. */
	private List<AbstractListener> childListeners;

	public AbstractListener() {
		childListeners = new ArrayList<>();
	}

	/** Call each child listener's handle methods. */
	public void handleEvent(Event event) {
		for(AbstractListener al : childListeners) {
			al.handleEvent(event);
		}
	}

	public List<AbstractListener> getChildListeners() {
		return childListeners;
	}
}