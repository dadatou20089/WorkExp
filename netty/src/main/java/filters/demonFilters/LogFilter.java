package filters.demonFilters;

import filters.MyFilter;
import org.apache.log4j.Logger;
import utils.MyLogger;

public class LogFilter implements MyFilter {
    private Logger logger = MyLogger.getLogger(LogFilter.class);

    @Override
    public Object enter() {
        logger.info("enter!");
        return null;
    }

    @Override
    public Object exit() {
        logger.info("exit!");
        return null;
    }
}
