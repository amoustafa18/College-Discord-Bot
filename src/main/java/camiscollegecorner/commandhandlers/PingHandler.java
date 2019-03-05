package camiscollegecorner.commandhandlers;

import sx.blah.discord.handle.obj.IMessage;
import java.time.Instant;
import java.time.temporal.TemporalField;
import java.util.concurrent.TimeUnit;

/** This class handles the ping command. */
public class PingHandler extends AbstractHandler {

	public PingHandler(IMessage message) { super(message); }

	@Override
	public void run() {
		final double NANO_TO_MILL = .000001;

		getMessage().getChannel().sendMessage("pong \n" +
				( (Instant.now().getNano() - getMessage().getTimestamp().getNano())*NANO_TO_MILL)
		+ "ms");

	}
}
