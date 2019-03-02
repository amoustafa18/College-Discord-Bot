package camiscollegecorner.commandhandlers;

import sx.blah.discord.handle.obj.IMessage;

/** This is an abstract handler class that stores the message that dispatched the handler. */
public abstract class AbstractHandler extends Thread {

	/** The message that dispatched the handler. */
	private IMessage message;

	public AbstractHandler(IMessage message) {
		this.message = message;
	}

	public IMessage getMessage() {
		return message;
	}
}