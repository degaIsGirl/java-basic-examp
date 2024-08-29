package basic.example._jackson;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component

//@Scope(value = SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
public class User {
    private String name ;
}
