package camiscollegecorner.reddit;

import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.Subreddit;
import net.dean.jraw.RedditClient;
import net.dean.jraw.models.EmbeddedMedia.OEmbed;
import net.dean.jraw.models.SubmissionPreview;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;

public class RedditImpl implements RedditGrabber {

    private String testing = "this is an implement test ";
    UserAgent userAgent = new UserAgent("bot", "camiscollegecorner.reddit",
            "v0.1", "collegecorner";
    Credentials credentials = Credentials.script("collegecorner", "wearethebotteam",
                "t9Nlv_epKQ8xNg", "hr7RFyJs5fZg-1IOE81gdEZ5fx4");

    RedditClient reddit = OAuthHelper.automatic

    public String randomImage(String[] subreddit) {
        return "randomImage currently unimplemented";
    }
}
