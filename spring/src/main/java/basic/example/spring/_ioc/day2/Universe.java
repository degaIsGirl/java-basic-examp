package basic.example.spring._ioc.day2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Universe implements ISchool{
    private String universeName = "清华大学";
}
