package myTester;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by nick on 2017/3/13.
 */
@RestController
public class MyController {

    @Autowired
    private static Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<String, String> valueOperations;

    @RequestMapping("/test")
    @ResponseBody
    public String adds() {
        logger.info("adds");
        valueOperations.set("add", "add");
        return "hello new controller";
    }

    @RequestMapping("/set")
    @ResponseBody
    public String setKey() {
        logger.info("set key: add, add");
        valueOperations.set("add", "add");
        return "OK";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String getKey() {
        logger.info("get key add!");
        String value = valueOperations.get("add");
        return value;
    }
}
