package basic.example._jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowDemo {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Writer {
        private String name;
        @JsonIgnore
        private Integer age;

        public Writer(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Long birth;
    }

    /**
     * 1、将对象序列化为json
     * @throws JsonProcessingException
     */
    @Test
    public void testToJson() throws JsonProcessingException {
        Writer writer = Writer.builder().name("zimug").age(18).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(writer);
        System.out.println(s);
    }

    /**
     * 2、如果进行反序列化, 那么被反序列化的对象需要有一个空的构造函数, 否则会报错
     * 这就是为什么要要求Writer对象上要有@NoArgsConstructor
     */
    @Test
    public void testToObject() {
        System.out.println(Integer.MAX_VALUE);
        String json = "{\"name\":\"zimug\",\"age\":18}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Writer writer = objectMapper.readValue(json, Writer.class);
            System.out.println(writer);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 3、将json对象反序列化为JsonNode
     * @throws IOException
     */
    @Test
    public void testToJsonNode() throws IOException {
        Writer zs = new Writer("张三", 18);
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes = objectMapper.writeValueAsBytes(zs);

        JsonNode jsonNode = objectMapper.readTree(bytes);
        String name = jsonNode.get("name").asText();
        System.out.println(name);
    }

    /**
     * 4、将json对象反序列化为List, 解决容器泛型的问题
     * @throws JsonProcessingException
     */
    @Test
    public void testToCollect() throws JsonProcessingException {
        List<Writer> list = Stream.generate(() -> new Writer("张三", 18))
                .limit(2).collect(Collectors.toList());
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writer().writeValueAsString(list);
        System.out.println(s);

        // 反序列化
        String content = "[{\"name\":\"张三\",\"age\":18},{\"name\":\"张三\",\"age\":18}]";
        List<Writer> writers = objectMapper.readValue(content, new TypeReference<List<Writer>>() {
        });
        System.out.println(writers);
    }

    /**
     * 5、对解析json对象进行自定义的配置
     * @throws JsonProcessingException
     */
    @Test
    public void testConfig() throws JsonProcessingException {
        // 这里没有sex字段, 解析是要报错的
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writer().writeValueAsString(new Writer("debug", 18));

        //我们可以通过configure()方法忽略掉无法识别的字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Writer writer = objectMapper.readValue(jsonString, Writer.class);
        System.out.println(writer);
    }

    @Test
    public void testFormatter() {
        Writer writer = Writer.builder().name("zimug").age(18).birth(System.currentTimeMillis()).build();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(writer);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
