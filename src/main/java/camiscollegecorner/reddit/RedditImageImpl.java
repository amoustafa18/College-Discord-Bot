package camiscollegecorner.reddit;

import net.dean.jraw.models.*;
import net.dean.jraw.RedditClient;
import net.dean.jraw.pagination.DefaultPaginator;

import java.util.ArrayList;
import java.util.List;

/** An implementation of the RedditImageGrabber interface. */
public class RedditImageImpl implements RedditImageGrabber {

    /** Use a List of Submissions as a cache so users don't have to wait for the Reddit request each time an image is
     *  grabbed.
     */
    private static List<Submission> submissionsList = new ArrayList<>();

    /**
     * Pulls a random image from the specified client's front page.
     * @param client The client to pull a trending image from
     * @return The direct link to the image file.
     */
    public Submission randomImageFromFontPage(RedditClient client) {
        if(submissionsList.size() > 0) {
            Submission s = submissionsList.get(0);

            if((s.getUrl().contains("imgur.com") || s.getUrl().contains("i.redd.it"))  && !s.isSelfPost()) {
                submissionsList.remove(0);
                return s;
            } else {
                submissionsList.remove(0);
                return randomImageFromFontPage(client);
            }
        }

        DefaultPaginator<Submission> frontPage = client.frontPage()
                .sorting(SubredditSort.NEW)
                .timePeriod(TimePeriod.DAY)
                .limit(100)
                .build();

        submissionsList = frontPage.next();

        return randomImageFromFontPage(client);
    }
}