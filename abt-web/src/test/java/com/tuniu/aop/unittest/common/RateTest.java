package com.tuniu.aop.unittest.common;

import org.isomorphism.util.TokenBucket;
import org.isomorphism.util.TokenBuckets;

import java.util.concurrent.TimeUnit;

/**
 * Created by chengyao on 2016/4/21.
 */
public class RateTest {

    public static void main(String[] args) {

        // Create a token bucket with a capacity of 1 token that refills at a fixed interval of 1 token/sec.
        final TokenBucket bucket = TokenBuckets.builder()
                .withCapacity(5)
                .withFixedIntervalRefillStrategy(2, 1, TimeUnit.SECONDS)
                .build();



        // ...
        {
            G g2 = new G(bucket);
            g2.start();
        }
        {
            G g1 = new G(bucket);
            g1.start();
        }


    }

    static class G extends Thread {
        private TokenBucket tokenBucket;

        public G(TokenBucket tokenBucket) {
            this.tokenBucket = tokenBucket;
        }
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Consume a token from the token bucket.  If a token is not available this method will block until
                // the refill strategy adds one to the bucket.

                boolean b = tokenBucket.tryConsume(1);

                System.out.println(Thread.currentThread().getId() + ": " + b);
            }
        }
    }

}
