package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Header;
import entity.Request;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by nick on 16/11/10.
 */
public class MyJson {

    private static ObjectMapper objectMapper;
    private static Logger logger = MyLogger.getLogger(MyJson.class);

    static {
        objectMapper = new ObjectMapper();
    }

    public static String writeAsString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("Error!", e);
            return null;
        }
    }

    public static <T> T readValue(String content, Class<T> clazz) {
        try {
            return objectMapper.readValue(content, clazz);
        } catch (IOException e) {
            logger.error("MyJson处理出错.", e);
            throw new RuntimeException("MyJson处理出错", e.getCause());
        }
    }


    public static void main(String[] args) {
        Logger logger = MyLogger.getLogger();
        Logger logger1 = MyLogger.getLogger("bbbb");
        String str = "aaaa";
        System.out.println(MyJson.writeAsString(str));
        Request request = new Request();
        request.setContent("asdasdasd");
        request.setUrl("asdasdasd");

        Header header1 = new Header();
        Header header2 = new Header();

        header1.setHeadId("1111111");
        header1.setHeadContent("asdasdasd");
        header2.setHeadId("2222222");
        header2.setHeadContent("asdasdasdasd");

        request.addHeader(header1);
        request.addHeader(header2);
        System.out.println(MyJson.writeAsString(request));

        logger.info(MyJson.writeAsString(request));
        logger1.debug(MyJson.writeAsString(request));

        String content = "{asdasdasd}";
        MyJson.readValue(content, Request.class);
    }

}
