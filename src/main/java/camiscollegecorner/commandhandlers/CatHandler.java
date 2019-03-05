package camiscollegecorner.commandhandlers;

import camiscollegecorner.Constants;
import camiscollegecorner.reddit.CatImageGrabber;
import net.dean.jraw.models.Submission;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IMessage;

import java.security.SecureRandom;
import java.util.List;

/** This class handles the cat command. */
public class CatHandler extends AbstractHandler {

	/** For generating random hexadecimal color codes. */
	private static SecureRandom random = new SecureRandom();

	/** This constructs a CatHandler. This object should handle the cat command. The cat
	 * command should cause the object to embed a random image of a cat using a CatGrabber and send it to the channel
	 * the command was issued on.
	 * @param message The IMessage issuing this command.
	 */
	public CatHandler(IMessage message) {
		super(message);

		List<Long> activeChannels = channelsActive();

		for(long l : Constants.CAT_CHANNELS) {
			activeChannels.add(l);
		}
	}

	@Override
	public void run() {
		super.run();

		if(!shouldRun()) {
			return;
		}

		Submission catPostSubmission = CatImageGrabber.getInstance().randomCatImage();
		String imageLink = catPostSubmission.getUrl();

		//discord doesn't allow gifs to be embedded, so ensure the pic is not a gif
		while(imageLink.endsWith(".gifv") || imageLink.endsWith(".gif")) {
			catPostSubmission = CatImageGrabber.getInstance().randomCatImage();
			imageLink = catPostSubmission.getUrl();
		}

		//imgur posts are usually not direct links. Get direct link from URL
		if(imageLink.contains("imgur")) {
			if(!(imageLink.endsWith(".jpg") || imageLink.endsWith(".png") || imageLink.endsWith(".jpeg"))) {
				//it is not a direct image link
				imageLink += ".png";
			}
		}

		String title = catPostSubmission.getTitle();
		String sourceSubreddit = catPostSubmission.getSubreddit();

		EmbedObject.ImageObject image = new EmbedObject.ImageObject();
		image.url = imageLink;

		EmbedObject embed = new EmbedObject();
		embed.image = image;
		embed.type = "image";
		embed.title = title;
		embed.description = "Found on r/" + sourceSubreddit;

		//generate a random color for the embed
		int color = random.nextInt(1000000);
		embed.color = color;

		getMessage().getChannel().sendMessage(embed);
	}
}