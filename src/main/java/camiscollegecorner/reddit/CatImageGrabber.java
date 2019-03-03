package camiscollegecorner.reddit;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.Submission;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;

/** A singleton class that uses a RedditImageGrabber to pull trending cat images from cat subreddits. */
public class CatImageGrabber implements HasRedditClient {
	private UserAgent userAgent = new UserAgent("bot", "camiscollegecorner.client",
			"v0.1", "collegecorner");
	private Credentials credentials = Credentials.script(System.getProperty("redditUser"),
			System.getProperty("redditPass"),System.getProperty("clientID"), System.getProperty("secret"));
	private NetworkAdapter adapter = new OkHttpNetworkAdapter(userAgent);
	private RedditClient client = OAuthHelper.automatic(adapter, credentials);

	private RedditImageImpl grabber = new RedditImageImpl(client);

	private static CatImageGrabber instance = new CatImageGrabber();

	private CatImageGrabber() {

	}

	@Override
	public RedditClient getClient() {
		return client;
	}

	/**
	 * Caches cat images in memory for speed efficiency.
	 */
	public void cache() {
		grabber.cache();
	}

	/**
	 * Grabs an image of a cat from Reddit
	 * @return The Submission object representing the cat post on Reddit
	 */
	public Submission randomCatImage() {
		return grabber.randomImageFromFontPage();
	}

	public static CatImageGrabber getInstance() {
		return instance;
	}
}