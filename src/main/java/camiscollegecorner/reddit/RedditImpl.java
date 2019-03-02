package camiscollegecorner.reddit;

import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.Submission;
import net.dean.jraw.models.Subreddit;
import net.dean.jraw.RedditClient;
import net.dean.jraw.models.EmbeddedMedia.OEmbed;
import net.dean.jraw.models.SubmissionPreview;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.pagination.DefaultPaginator;

import java.util.ArrayList;
import java.util.List;

public class RedditImpl implements RedditGrabber {

    private String testing = "this is an implement test ";
    UserAgent userAgent = new UserAgent("bot", "camiscollegecorner.reddit",
            "v0.1", "collegecorner";
    Credentials credentials = Credentials.script("collegecorner", "wearethebotteam",
                "t9Nlv_epKQ8xNg", "hr7RFyJs5fZg-1IOE81gdEZ5fx4");

    NetworkAdapter adapter = new OkHttpNetworkAdapter(userAgent);

    RedditClient reddit = OAuthHelper.automatic(adapter, credentials);
    //this is from the website and i have no idea why it breaks with .build and .next
    DefaultPaginator<Submission> subreddits = reddit.subreddits().build();



    public String randomImage(DefaultPaginator<Submission> subreddit) {
        List<String> images = new ArrayList<String>();
        for (Submission s : images.next())
            if (!s.isSelfPost() && s.getUrl().contains("i.imgur.com")) {
                images.add(s.getUrl());
            }
        return (images.get(0));
    }
}
