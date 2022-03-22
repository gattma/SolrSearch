package at.contest.solr.application.interceptor;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.Random;

@ResponseTimeDelay
@Interceptor
public class ResponseTimeDelayInterceptor {

    @Inject
    Logger logger;

    @Inject
    @ConfigProperty(name = "delay.rate", defaultValue = "0.2")
    double delayRate;

    @Inject
    @ConfigProperty(name = "delay.minSec", defaultValue = "5")
    int minDelayInSec;

    @Inject
    @ConfigProperty(name = "delay.maxSec", defaultValue = "10")
    int maxDelayInSec;

    @AroundInvoke
    public Object responseTimeDelay(InvocationContext ctx) throws Exception {
        var rand = new Random();
        if(rand.nextDouble() < delayRate) {
            var delayInMillis = (rand.nextInt(maxDelayInSec - minDelayInSec) + minDelayInSec) * 1000;
            logger.debugf("Delay %d ms", delayInMillis);
            Thread.sleep(delayInMillis);
        }

        return ctx.proceed();
    }

}
