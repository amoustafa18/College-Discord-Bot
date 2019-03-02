package camiscollegecorner.reddit;

import net.dean.jraw.models.Submission;
import net.dean.jraw.pagination.DefaultPaginator;

/** This is an interface specifying the functionality of a class that pulls trending images from subreddits. */
public interface RedditGrabber {
	/**
	 * Pulls a random image from the specified subreddit.
	 *
	 * @param subreddit The subreddit to pull a trending image from
	 * @return The direct link to the image file
	 */
	String randomImage(DefaultPaginator<Submission> subreddit);
}