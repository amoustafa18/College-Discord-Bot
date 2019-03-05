package camiscollegecorner.commandhandlers;

import camiscollegecorner.Constants;
import sx.blah.discord.handle.obj.IMessage;
import java.time.Instant;
import java.util.List;

/** This class handles the ping command. */
public class PingHandler extends AbstractHandler {

	/** This constructs a PingHandler. This object should handle the ping command. The ping
	 * command should respond with "pong" and give the latency of the ping command being handled in milliseconds.
	 * @param message The IMessage issuing this command.
	 */
	public PingHandler(IMessage message) {
		super(message);

		List<Long> activeChannels = channelsActive();

		for(long l : Constants.ALL_CHANNELS_FLAG) {
			activeChannels.add(l);
		}
	}

	//TODO: fix this. should not return negative latency.
	@Override
	public void run() {
		super.run();

		if(!shouldRun()) {
			return;
		}

		final double NANO_TO_MILL = .000001;

		getMessage().getChannel().sendMessage("pong \n" +
				( (Instant.now().getNano() - getMessage().getTimestamp().getNano())*NANO_TO_MILL)
		+ "ms");
	}
}
