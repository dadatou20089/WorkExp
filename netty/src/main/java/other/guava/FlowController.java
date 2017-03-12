package other.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalDateTime;

/**
 * Created by nick on 2017/3/12.
 */
public class FlowController {

    public RateLimiter rateLimiter;

    public FlowController() {
        rateLimiter = RateLimiter.create(1);
    }

    public void setRate(int rate) {
        if (rate <= 0) {
            System.out.println("error!");
            return;
        }
        rateLimiter.setRate(rate);
    }

    public void accquire(int id) {
        System.out.println(id + "- start acquired a token, " + LocalDateTime.now());
        rateLimiter.acquire();
        System.out.println(id + "- acquired a token!" + LocalDateTime.now());
    }

}
