package utils;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class MyLogger extends Logger{
    static {
        BasicConfigurator.configure();
    }

    public static Logger getLogger(String name) {
        return LogManager.getLogger(name);
    }

    public static Logger getLogger() {
        return LogManager.getLogger("default");
    }

    public static Logger getLogger(Class clazz) {
        return LogManager.getLogger(clazz.getName());
    }

    public static Logger getRootLogger() {
        return LogManager.getRootLogger();
    }

    public static Logger getLogger(String name, LoggerFactory factory) {
        return LogManager.getLogger(name, factory);
    }

    protected MyLogger(String name) {
       super(name);
    }
}
