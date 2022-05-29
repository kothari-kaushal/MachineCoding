import com.scheduler.services.MyScheduler;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Application {

    public static void main(String[] args) {
        MyScheduler scheduler = new MyScheduler();

        scheduler.schedule(() -> {
            System.out.println(Instant.now() + ": once");
        }, 2000, TimeUnit.MILLISECONDS);

        scheduler.scheduleAtFixedRate(() -> {
            System.out.println(Instant.now() + ": repeat");
        }, 1000, 2000, TimeUnit.MILLISECONDS);

        scheduler.scheduleWithFixedDelay(() -> {
            System.out.println(Instant.now() + ": delay");
        }, 2500, 200, TimeUnit.MILLISECONDS);
    }
}
