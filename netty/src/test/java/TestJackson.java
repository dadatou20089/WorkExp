import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jackson.entities.MyJson1;
import jackson.serialize.MyDeSerializy;
import jackson.serialize.MyDeSerializy1;

/**
 * Created by nick on 16/12/20.
 */
public class TestJackson {

    public static void main(String[] args) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        MyJson1 myJson1 = new MyJson1("nick", "json", "1111111", "22222222");
        System.out.println(myJson1);

        String json = mapper.writeValueAsString(myJson1);
        System.out.println(json);


        //json = "{\"name1\":\"nick\", \"description1\":\"json\", \"number1\":\"1111111\"}";

        SimpleModule module = new SimpleModule();
        module.addDeserializer(MyJson1.class, new MyDeSerializy());
        mapper.registerModule(module);

        MyJson1 myJson11 = mapper.readValue(json, MyJson1.class);
        System.out.println(myJson11);

        json = "{\"name1\":\"nick\", \"description1\":\"json\", \"number1\":\"1111111\"}";
        module = new SimpleModule();
        module.addDeserializer(MyJson1.class, new MyDeSerializy1());
        mapper.registerModule(module);
        mapper.findAndRegisterModules();
        myJson11 = mapper.readValue(json, MyJson1.class);
        System.out.println(myJson11);

//        MyJson1 myJson11 = mapper.readValue(json, MyJson1.class);
//        System.out.println(myJson11);
    }

}
