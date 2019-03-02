package camiscollegecorner.commandhandlers;

import camiscollegecorner.Constants;
import camiscollegecorner.reddit.CatImageGrabber;
import net.dean.jraw.models.Submission;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IMessage;

import java.security.SecureRandom;

/** This class handles the cat command. */
public class CatHandler extends AbstractHandler {

	private static CatImageGrabber catImageGrabber = new CatImageGrabber();

	/** For generating random hexadecimal color codes. */
	private static SecureRandom random = new SecureRandom();

	public CatHandler(IMessage message) {
		super(message);
	}

	@Override
	public void run() {
		if(getMessage().getChannel().getLongID() != Constants.CUTE_PICS_CHANNEL_ID) {
			return;
		}

		Submission catPostSubmission = catImageGrabber.randomCatImage();
		String imageLink = catPostSubmission.getUrl();

		//imgur posts are usually not direct links. Get direct link from URL
		if(imageLink.contains("imgur")) {
			if(!(imageLink.endsWith("jpg") || imageLink.endsWith("png") || imageLink.endsWith("jpeg"))) {
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

		int color = random.nextInt(1000000);
		embed.color = color;

		getMessage().getChannel().sendMessage(embed);
	}
}