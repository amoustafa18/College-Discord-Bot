package camiscollegecorner.reddit;

import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.*;
import net.dean.jraw.RedditClient;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.pagination.DefaultPaginator;

import java.util.ArrayList;
import java.util.List;

public class RedditImpl implements RedditGrabber {
    private UserAgent userAgent = new UserAgent("bot", "camiscollegecorner.reddit",
            "v0.1", "collegecorner");
    private Credentials credentials = Credentials.script(System.getProperty("redditUser"),
            System.getProperty("redditPass"),System.getProperty("clientID"), System.getProperty("secret"));

    private NetworkAdapter adapter = new OkHttpNetworkAdapter(userAgent);

    private RedditClient reddit = OAuthHelper.automatic(adapter, credentials);

    private static List<Submission> submissionsList = new ArrayList<>();

    public String randomImage() {
        if(submissionsList.size() > 0) {
            Submission s = submissionsList.get(0);

            if((s.getUrl().contains("imgur.com") || s.getUrl().contains("i.redd.it"))  && !s.isSelfPost()) {
                submissionsList.remove(0);
                return s.getUrl();
            } else {
                submissionsList.remove(0);
                return randomImage();
            }
        }

        DefaultPaginator<Submission> frontPage = reddit.frontPage()
                .sorting(SubredditSort.NEW)
                .timePeriod(TimePeriod.DAY)
                .limit(100)
                .build();

        submissionsList = frontPage.next();

        return randomImage();
    }
}