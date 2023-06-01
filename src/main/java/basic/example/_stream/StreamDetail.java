package basic.example._stream;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Copyright(C), 2020 - 2023, 小码教育
 *
 * @Date: 2023/4/20 15:58
 * @Description:
 * @Author: mawb<mawb @ xiaoma.cn>
 */
public class StreamDetail {

    /**
     * 步骤1.创建Stream
     */
    @Test
    //1.1-基于集合创建Stream
    public void genStreamByArray() {
        //基于数组创建-方式1
        String[] strings = {"first", "second"};
        Stream.of(strings).map(item -> item.toUpperCase() + " by Stream.of").forEach(System.out::println);

        //基于数组创建-方式2
        Arrays.stream(strings).map(item -> item.toUpperCase() + " by Arrays.stream()").forEach(System.out::println);
    }

    //1.2-基于集合创建
    @Test
    public void genStreamByCollection() {
        //Collection.stream()方法 适用于 list set, 并不适用于Map

        //list
        LinkedList<String> strings = new LinkedList<>();
        strings.add("first");
        strings.add("second");
        strings.stream().map(item -> item.toUpperCase() + " by list Collection.stream()").forEach(System.out::println);

        //set
        HashSet<String> strings1 = new HashSet<>();
        strings1.add("first");
        strings1.add("second");
        strings1.stream().map(item -> item.toUpperCase() + " by set Collection.stream()").forEach(System.out::println);

        //map
        HashMap<String, Integer> map = new HashMap<>();
        map.put("first", 1);
        map.put("second", 2);
        //如果我们数据集合想直接使用.stream 方法, 那么这个集合需要实现Collection接口
        //报错 因为map不是Collection的子类
        //map.stream().map(item -> item.toUpperCase())
        map.entrySet().stream().map(item -> item.getKey().toUpperCase() + " by map Collection.stream()").forEach(System.out::println);
    }

    public int count = 0;

    @Test
    //1.3-基于Supplier创建
    public void genStreamBySupplier() {
        //Supplier
        List<String> collect = Stream.generate(() -> ++count + " by supplier").limit(10).collect(Collectors.toList());
        System.out.println("collect = " + collect);

        //如果我们不指定limit的话将会是一个无限流
        //Stream.generate(() -> ++count + " by supplier").limit(10).forEach(System.out::println);
    }

    /**
     * 步骤2.过滤元素
     * 这里需要注意的是, 符合我们要求的元素才会被保留下来
     */
    private int step;

    @Test
    public void testFilter() {
        Stream.generate(() -> ++step).limit(10).filter((item) -> item > 5).forEach(System.out::println);
    }

    @Data
    static class Match {
        private String name;
        private int score;

        private int age;

        public Match(String name, int score, int age) {
            this.name = name;
            this.score = score;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            Match match = (Match) o;
            return name.equals(match.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "Match{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    ", age=" + age +
                    '}';
        }
    }

    int start = 97;

    /**
     * collect 方法的参数值类型 => java.util.stream.Collector
     * java.util.stream.Collectors 工具类中提供了很多静态方法, 可以返回Collector的实现类对象
     * Collectors.toList()、Collectors.toSet(), Collectors.toMap(), Collectors.toCollection(), Collectors.joining()
     * @return
     */
    private List<Match> getDataSource() {
        //创造10个选手
        int limit = 10;
        int init = start;
        return Stream.generate(() -> {
                    Match match = new Match((char) start + "",
                            (int) Math.round(Math.random() * 10),
                            (int) Math.round(Math.random() * 10)
                    );
                    if (start >= (init + 2)) {
                        start = init;
                    } else {
                        start++;
                    }
                    return match;
                }
        ).limit(limit).collect(Collectors.toList());
    }


    @Test
    public void testSort() {
        // 产生数据来源
        List<Match> collect = getDataSource();

        System.out.println("原始数据");
        collect.forEach(System.out::println);

        /**
         * 步骤3.排序
         */
        System.out.println("排序后");

        class MatchComparator implements Comparator<Match> {
            @Override
            public int compare(Match o1, Match o2) {
                if (o1.getScore() > o2.getScore()) {
                    return -1;
                } else if (o1.getScore() < o2.getScore()) {
                    return 1;
                } else {
                    return o1.getName().compareTo(o2.getName());
                }
            }
        }
        System.out.println("排序方式1");
        collect.stream().sorted(new MatchComparator()).collect(Collectors.toList()).forEach(System.out::println);

        /**
         * 两种方式排序效果一致, 但是方式2更加简洁
         * 原因是java.util.Comparator 接口中有很多默认的静态方法, 会返回Comparator的实现类对象
         */
        Comparator<Match> comparator = Comparator.comparing(Match::getScore, Comparator.reverseOrder())
                .thenComparing(Match::getName, Comparator.naturalOrder());

        System.out.println("排序方式2");
        collect.stream().sorted(
                comparator
        ).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("前三名");

        /**
         * 步骤4.对排序后的数据进行截取-类似于分页
         */
        // 获取
        collect.stream().skip(0).limit(3).collect(Collectors.toList()).forEach(System.out::println);

        /**
         * 步骤5去重
         * 去重标准: hashcode & equals相等
         */
        System.out.println("去重后");
        collect.stream().distinct().forEach(System.out::println);

        /**
         * 步骤6.分组
         * 需求: 基于用户姓名进行分组
         */
        Map<String, List<Match>> groupList = collect.stream().collect(Collectors.groupingBy(Match::getName));
        System.out.println("分组后");
        groupList.forEach((key, value) -> {
            System.out.println(key + " : " + value + "( collection.class{" + value.getClass() + "} )");
        });

        //分组后统计
        Map<String, Long> groupCount = collect.stream().collect(Collectors.groupingBy(Match::getName, Collectors.counting()));
        System.out.println("分组后统计, 每个分组的数量");
        groupCount.forEach((key, value) -> {
            System.out.println(key + " : " + value);
        });

        System.out.println("分组后统计, 每个分组的总分");
        Map<String, Integer> groupSum = collect.stream().collect(Collectors.groupingBy(Match::getName, Collectors.summingInt(Match::getScore)));
        groupSum.forEach((key, value) -> {
            System.out.println(key + " : " + value);
        });

        System.out.println("分组后统计, 每个分组的最大分数");
        Map<String, Optional<Match>> groupMax = collect.stream().collect(Collectors.groupingBy(Match::getName, Collectors.maxBy(Comparator.comparing(Match::getScore))));
        groupMax.forEach((key, value) -> {
            System.out.println(key + " : " + value);
        });

        Map<String, Double> groupAverage = collect.stream().collect(Collectors.groupingBy(Match::getName, Collectors.averagingDouble(Match::getScore)));
        System.out.println("分组后统计, 每个分组的平均分");
        groupAverage.forEach((key, value) -> {
            System.out.println(key + " : " + value);
        });


        /**
         * 前面是分组后常见的统计, 更为通用的规则我们可以通过Collectors.reducing来实现
         * 需求如下: 按照姓名分组, 取每个分组的最高分赛事记录, 分数相同的情况下, 取名字最小的
         */
        List<Match> dataSource = Stream.of(
                new Match("a", 10, 9),
                new Match("a", 11, 10),//hit
                new Match("a", 11, 11),
                new Match("b", 10, 9),
                new Match("b", 12, 10),//hit
                new Match("b", 12, 11)
        ).collect(Collectors.toList());
        Map<String, Optional<Match>> groupMax2 = dataSource.stream().collect(
                Collectors.groupingBy(
                        Match::getName,
                        Collectors.reducing((a, b) -> {
                                    if (a.getScore() > b.getScore()) {
                                        return a;
                                    } else if (a.getScore() < b.getScore()) {
                                        return b;
                                    } else {
                                        return a.getAge() < b.getAge() ? a : b;
                                    }
                                }
                        )
                )
        );
        System.out.println("分组后统计, 每个分组的符合我么规则的最高分记录");
        groupMax2.forEach((key, value) -> {
            System.out.println(key + " : " + value);
        });
    }

    /**
     * 步骤7.2-将处理后的数据转换为set
     * Set的特性是, 相同的元素只有一个, 所以在转换的时候, 会自动去重
     * 相同的标准: hashcode & equals相等
     */
    @Test
    public void testToList() {
        List<Match> dataSource = getDataSource();
        System.out.println("原始数据");
        dataSource.forEach(System.out::println);
        System.out.println("转换为set");
        //转换为set, 我们这里认为只要name相同, 那么就是同一个元素
        Set<Match> collect = dataSource.stream().collect(Collectors.toSet());
        collect.forEach(System.out::println);
    }

    /**
     * 步骤7.3-将处理后的数据转为map
     * 这里我们需要注意转换为map, key可能出现冲突的情况
     */
    @Test
    public void testMap() {
        List<Match> dataSource = getDataSource();
        System.out.println("原始数据");
        dataSource.forEach(System.out::println);
        System.out.println("转换为map");

        // 转换为map的过程中报错了, 原因是key重复了
        Map<String, Match> collect = dataSource.stream().collect(Collectors.toMap(Match::getName, Function.identity()));
        collect.forEach((key, value) -> {
            System.out.println(key + " : " + value);
        });

        // 如何解决key冲突的问题? 通过Collectors.toMap的第三个参数来解决
        Map<String, Match> collect1 = dataSource.stream().collect(Collectors.toMap(Match::getName,
                Function.identity(),
                (pre, next) -> {
                    System.out.println("key冲突了, pre:" + pre + ", next:" + next);
                    //基于需求决定要哪一个元素, 这里我们选择next
                    return next;
                }
        ));
        System.out.println(collect1);
    }

    /**
     * 步骤7.4-将处理后的数据转为String
     */
    @Test
    public void testToString() {
        List<Match> dataSource = getDataSource();
        System.out.println("原始数据");
        dataSource.forEach(System.out::println);
        System.out.println("转换为String");
        String collectName = dataSource.stream().map(Match::getName).collect(Collectors.joining(","));
        List<Integer> collect = dataSource.stream().map(Match::getScore).collect(Collectors.toList());
        System.out.println(collect);
        String collectNameV2 = dataSource.stream().map(Match::getName).collect(Collectors.joining(",", "[", "]"));
        System.out.println(collectNameV2);
    }

    /**
     * 步骤7.5-将处理后的数据转换为任意容器
     */
    @Test
    public void testToAnyCollection() {
        List<Match> dataSource = getDataSource();
        System.out.println("原始数据, 容器使用的是 :" + dataSource.getClass());
        dataSource.forEach(System.out::println);

        LinkedList<Match> collect = dataSource.stream().collect(Collectors.toCollection(LinkedList::new));
        System.out.println("转换为ArrayDeque, 容器使用的是 :" + collect.getClass());
        collect.forEach(System.out::println);
        Match pop = collect.pop();
        System.out.println("转换为ArrayDeque, pop 出一个元素:" + pop);
    }

    /**
     * 步骤8-获取最小值
     */
    @Test
    public void testMin() {
        List<Match> dataSource = getDataSource();
        System.out.println("原始数据");
        dataSource.forEach(System.out::println);
        System.out.println("获取最小的年龄");
        System.out.println("获取总分数");
        Integer sum = dataSource.stream().map(Match::getScore).reduce(0, Integer::sum);


        // 获取总合
        Double collect = dataSource.stream().map(Match::getScore).mapToDouble(Integer::intValue).sum();
        int sum2 = dataSource.stream().map(Match::getAge).mapToInt(Integer::intValue).sum();

        final int sum1 = 0;

        System.out.println(sum);
    }

    /**
     * 步骤9-获取最大值
     */
    @Test
    public void testMax() {
        List<Match> dataSource = getDataSource();
        System.out.println("原始数据");
        dataSource.forEach(System.out::println);
        Optional<Match> max = dataSource.stream().max(Comparator.comparing(Match::getAge));
        System.out.println(max);
    }

    /**
     * 步骤10-获取数量
     */
    @Test
    public void testCount() {
        List<Match> dataSource = getDataSource();
        System.out.println("原始数据");
        dataSource.forEach(System.out::println);
        long count = dataSource.stream().filter(item -> item.getScore() > 5).count();
        System.out.println("获取分数大于5的个数" + count);
    }

    /**
     * 步骤11-获取总和
     */
    @Test
    public void testSum() {
        List<Match> dataSource = getDataSource();
        System.out.println("原始数据");
        dataSource.forEach(System.out::println);

        /**
         * mapToInt 转换为了java.util.stream.IntStream (interface), 这个接口中有sum方法;
         * 具体的实现类为java.util.stream.IntPipeline, 通过阅读源代码,我们发现其实底层使用还是 reduce(0, Integer::sum)规约操作
         */
        int sum1 = dataSource.stream().mapToInt(Match::getScore).sum();
        System.out.println("方式1获取总分数" + sum1);

        Integer sum = dataSource.stream().map(Match::getScore).reduce(0, Integer::sum);
        System.out.println("方式2获取总分数" + sum);
    }

    @Test
    public void testMapConvert() {
        List<Match> dataSource = getDataSource();
        System.out.println("原始数据");
        dataSource.forEach(System.out::println);

        LinkedList<String> strings = new LinkedList<>();
        for(Match item: dataSource) {
           strings.add(item.getName());
        }
        System.out.println(strings);

        System.out.println("转换后的数据");
        List<String> nameList = dataSource.stream().map(Match::getName).collect(Collectors.toList());
        nameList.forEach(System.out::println);
    }

    @Test
    public void testFlatMapConvert() {
        List<List<Match>> collect = Stream.generate(this::getDataSource).limit(3).collect(Collectors.toList());
        System.out.println("原始数据");
        collect.forEach(System.out::println);

        System.out.println("原始方法转换后的数据");
        LinkedList<String> matches = new LinkedList<>();
        for(List<Match> list: collect) {
            for(Match match: list) {
                    matches.add(match.getName());
            }
        }
        System.out.println(matches);


        List<String> collect1 = collect.stream().flatMap(Collection::stream).map(Match::getName).collect(Collectors.toList());
        System.out.println(collect1);
    }

}