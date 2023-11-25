package basic.example.spring._resource;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
/**
 * spring 将我们的常见的磁盘文件、网络数据等抽象为了资源Resource 接口
 * spring 将资源的定义和资源的加载进行了隔离, 将资源的加载抽象为了ResourceLoader 接口
 */
public class Day1 {

    /**
     * 入门案例
     *
     * 我们需要先熟悉下整体的结构
     * ResourceLoader 定义了资源加载的接口, 核心方法是: Resource getResource(String location)
     * DefaultResourceLoader 是对资源加载接口的默认实现, 它的核心逻辑如下:
     *      1、是否有用 户自定义协议资源解决策略, 这个优先级最高
     *      2、如果路径是以 "/" 开头的话返回ClassPathContextResource资源实例
     *      3、如果路径是以 "classpath:" 开头的话返回ClassPathResource 资源实例
     *      4、如果路径是协议开头的路径, 如果http:// ftp:// ... (开头的路径返回UrlResource资源实例)  file://(开头的路径返回FileUrlResource资源实例)
     *
     * 在上面如果以 "/" 开头的话返回ClassPathContextResource 并不十分合理, 所以提供了FileSystemResourceLoader返回的是FileSystemContextResource
     * 用这个更为的的合理
     */
    @Test
    public void testDemo() {
        // DefaultResourceLoader 是Spring为我们提供的资源加载的默认实现
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();

        // 基于路径的不同方式, 创建对应的资源
        // 这里的对应资源对象是org.springframework.core.io.ClassPathResource;
        Resource resource = defaultResourceLoader.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + "/_env/demo.properties");
        if (resource.exists() && resource.isFile()) {
            try {
                Properties properties = new Properties();
                InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
                properties.load(inputStreamReader);
                System.out.println(properties.get("city"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * FileSystemResourceLoader 只能够支持单个资源的加载, 如果希望加载多个资源怎么办呢?
     *
     * Spring 提供了PathMatchingResourcePatternResolver:getResources, 能够支持antPath 风格的路径获取多个资源;
     * 我们在创建PathMatchingResourcePatternResolver对象的时候, 可以指定ResourceLoader, 如果不指定的话, 那么默认使用的是DefaultResouceLoader;
     *
     * 匹配逻辑如下:
     *      非 "classpath*:" 开头，且路径不包含通配符，直接委托给相应的 ResourceLoader 来实现。
     *      其他情况，调用 #findAllClassPathResources(...)、或 #findPathMatchingResources(...) 方法，返回多个 Resource
     */
    @Test
    public void testPatternResources() {
        FileSystemResourceLoader fileLoader = new FileSystemResourceLoader();
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver(fileLoader);

        /**
         * antPath 常见的语法结构
         * ?：匹配任意单字符
         * `*`：匹配任意数量的字符
         * `**`：匹配任意层级的路径/目录
         */
        // 查找classpath:/_resource/day1/antResources/路径下ant?.txt 的文件, ?代表一个字符
        try {
            Resource[] resources = patternResolver.getResources("classpath:/_resource/day1/antResources/ant?.txt");
            for (Resource resource : resources) {
                System.out.println(resource);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // classpath:/ 和classpath*/之间的区别 见 https://blog.csdn.net/guoxilen/article/details/81435553
    }
}
