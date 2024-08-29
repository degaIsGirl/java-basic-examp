package basic.example._actuators;

import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

@Component
@Endpoint(id = "hello")
public class Demo {

    HashMap<String, String> map = new HashMap<>();

    @ReadOperation
    public String get(String name) {
       return map.getOrDefault(name, "emptyData");
    }

    @WriteOperation
    public String writeData(@RequestBody  ReqBody reqBody) {
        return map.put(reqBody.getName(), reqBody.getValue());
    }
}
