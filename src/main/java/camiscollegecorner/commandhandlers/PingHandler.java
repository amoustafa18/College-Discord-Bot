package camiscollegecorner.commandhandlers;

import sx.blah.discord.handle.obj.IMessage;

/** This class handles the ping command. */
public class PingHandler extends AbstractHandler {

	public PingHandler(IMessage message) {
		super(message);
	}

	@Override
	public void run() {
		getMessage().getChannel().sendMessage("pong");
	}
}