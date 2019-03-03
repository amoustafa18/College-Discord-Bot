package camiscollegecorner.reddit;

import net.dean.jraw.models.*;
import net.dean.jraw.RedditClient;
import net.dean.jraw.pagination.DefaultPaginator;
import sx.blah.discord.Discord4J;

import java.util.ArrayList;
import java.util.List;

/** An implementation of the RedditImageGrabber interface. */
public class RedditImageImpl implements RedditImageGrabber {

    /** Use a List of Submissions as a cache so users don't have to wait for the Reddit request each time an image is
     *  grabbed.
     */
    private List<Submission> submissionsList = new ArrayList<>();

    /** The RedditClient used to pull images from. */
    private RedditClient client;

    private DefaultPaginator<Submission> frontPage;

    /** When the number of cached submissions drops below this number, a new reddit request will be dispatched. */
    private static final int RESTOCK_THRESHOLD = 5;

    public RedditImageImpl(RedditClient client) {
        this.client = client;
        this.frontPage = client.frontPage()
                .sorting(SubredditSort.HOT)
                .timePeriod(TimePeriod.DAY)
                .limit(100)
                .build();
    }

    /**
     * Caches submissions from the front page into {@code submissionList}
     */
    public void cache() {
        List<Submission> cache = frontPage.next();

        submissionsList.addAll(cache);
    }

    /**
     * Pulls a random image from the specified client's front page.
     * @return The direct link to the image file.
     */
    public Submission randomImageFromFontPage() {
        if(submissionsList.size() > 0) {
            Submission s = submissionsList.get(0);

            if((s.getUrl().contains("imgur.com") || s.getUrl().contains("i.redd.it"))  && !s.isSelfPost()) {
                submissionsList.remove(0);
                checkRestock();
                return s;
            } else {
                submissionsList.remove(0);
                checkRestock();
                return randomImageFromFontPage();
            }

        }

        submissionsList = frontPage.next();

        return randomImageFromFontPage();
    }

    /**
     * Restocks the cache if needed. The cache needs to be restocked if and only if the number of submission entries
     * drops below {@code RESTOCK_THRESHOLD}. The request will be dispatched on another thread to avoid pauses.
     */
    private void checkRestock() {
        if(submissionsList.size() < RESTOCK_THRESHOLD) {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    cache();
                }
            };

            Thread thread = new Thread(r);
            thread.start();
        }
    }
}