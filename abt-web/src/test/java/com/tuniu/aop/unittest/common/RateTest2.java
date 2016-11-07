package com.tuniu.aop.unittest.common;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created by chengyao on 2016/4/21.
 */
public class RateTest2 {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(0.9);

        // ...
        {
            G g2 = new G(rateLimiter);
            g2.start();
        }
        {
            G g1 = new G(rateLimiter);
            g1.start();
        }


    }

    static class G extends Thread {
        private RateLimiter rateLimiter;

        public G(RateLimiter rateLimiter) {
            this.rateLimiter = rateLimiter;
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

                boolean b = rateLimiter.tryAcquire();

                System.out.println(Thread.currentThread().getId() + ": " + b);
            }
        }
    }

}
