package basic.example.spring._ioc.day2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class PrimarySchool implements ISchool{
    @Value("${schoolName}")
    private String schoolName;

    @Value("${address}")
    private String address;

    @Override
    public String toString() {
        return "School{" +
                "schoolName='" + schoolName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
