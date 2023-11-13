package basic.example.spring._ioc.day2;

import com.xmw.component.common.util.JsonUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component
@Getter
public class Teacher {
    /**
     * 这里可以完成正常的注入, 因为首先参考的是byType类型
     *
     * 但是如果我们将@Autowired 换成 @Resource就会报错
     * Caused by: org.springframework.beans.factory.BeanNotOfRequiredTypeException:
     * Bean named 'universe' is expected to be of type 'basic.example.spring._ioc.day2.PrimarySchool'
     * but was actually of type 'basic.example.spring._ioc.day2.Universe'
     */
    @Autowired
    private PrimarySchool universe;

    public Teacher() {
        System.out.println("teacher default construct");
    }

    private ISchool universeOfInit;

    @Autowired
    private Teacher(ISchool universe) {
        System.out.println("teacher construct with ISchool");
        this.universeOfInit = universe;
    }

    /**
     * 我们可以脱离属性名称指定beanName
     */
    @Resource(name = "primarySchool")
    private ISchool myPrimarySchoolByResource;

    @Autowired
    @Qualifier(value = "universe")
    private ISchool myUniverseByAutowired;

    @Override
    public String toString() {
        return JsonUtil.formatToStr(this);
    }
}
