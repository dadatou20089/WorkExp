package netty;

import netty.filters.MyFilter;
import org.apache.log4j.Logger;
import netty.utils.MyJson;
import netty.utils.MyLogger;

import java.util.ArrayList;
import java.util.List;

public class Entry {
    public static Logger logger = MyLogger.getLogger(Entry.class);
    public static List<Class<MyFilter>> filters = new ArrayList<>();

    public static void addFilters(MyFilter filter) {
        filters.add(MyFilter.class);
    }

    public static void execute() {

    }

    public static void main(String[] args) {
        String service = "aaaa";
        String method = "bbbb";
        String argJson = "{\"aaa\":\"aaa\"}";

        logger.info(MyJson.writeAsString(service));
        logger.info(MyJson.readValue(argJson, String.class));
    }
}
