package basic.example._jackson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    User user;

    /**
     *
     * @return
     */
    @GetMapping("/test/debug")
    public User debug() {
        //return JsonUtil.formatToStr(this.user);
        return user;
    }
}
