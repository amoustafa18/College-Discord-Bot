package camiscollegecorner.reddit;

import net.dean.jraw.RedditClient;
import net.dean.jraw.models.Submission;

/** This is an interface specifying the functionality of a class that pulls trending images from subreddits. */
public interface RedditImageGrabber {
	/**
	 * Pulls a random image from the specified Reddit client's front page.
	 *
	 * @return The submission object representing the image post on Reddit
	 */
	Submission randomImageFromFontPage();
}