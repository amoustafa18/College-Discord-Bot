package camiscollegecorner.reddit;

import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.*;
import net.dean.jraw.RedditClient;
import net.dean.jraw.models.EmbeddedMedia.OEmbed;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.pagination.DefaultPaginator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RedditImpl implements RedditGrabber {

    private String testing = "this is an implement test ";
    private Random random = new Random();

    private UserAgent userAgent = new UserAgent("bot", "camiscollegecorner.reddit",
            "v0.1", "collegecorner");
    private Credentials credentials = Credentials.script(System.getProperty("redditUser"),
            System.getProperty("redditPass"),System.getProperty("clientID"), System.getProperty("secret"));

    private NetworkAdapter adapter = new OkHttpNetworkAdapter(userAgent);

    private RedditClient reddit = OAuthHelper.automatic(adapter, credentials);

    public String randomImage() {
//        List<String> images = new ArrayList<String>();
//        for (Submission s : subreddit.next())
//            if (!s.isSelfPost() && s.getUrl().contains("i.imgur.com")) {
//                images.add(s.getUrl());
//            }
//        return (images.get(0));

        DefaultPaginator<Submission> frontPage = reddit.frontPage()
                .sorting(SubredditSort.TOP)
                .timePeriod(TimePeriod.DAY)
                .limit(10)
                .build();

        Listing<Submission> submissions = frontPage.next();
        int randomNumber = random.nextInt(submissions.size());
        Submission s = submissions.get(randomNumber);

        if((s.getUrl().contains("i.imgur.com") || s.getUrl().contains("i.redd.it"))  && !s.isSelfPost()) {
            return s.getUrl();
        }

        return randomImage();
    }
}