package nu.njp.receptinator.core.producer;

import nu.njp.receptinator.core.qualifier.DefaultLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggerProducer {
    @Produces
    @DefaultLogger
    public Logger newInstance(InjectionPoint point) {
        return LoggerFactory.getLogger(point.getMember().getDeclaringClass().getName());
    }
}
