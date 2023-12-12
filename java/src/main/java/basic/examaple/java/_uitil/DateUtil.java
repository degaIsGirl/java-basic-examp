package basic.examaple.java._uitil;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * Copyright(C), 2020 - 2023, 小码教育
 *
 * @Date: 2023/5/3 22:15
 * @Description:
 * @Author: mawb<mawb @ xiaoma.cn>
 */
public class DateUtil {
    @Test
    public void testInstant() {
        Instant now = Instant.now();
        System.out.println(now);
    }

    @Test
    public void testGetInstantFromLong() {
        Instant instant = Instant.ofEpochSecond(System.currentTimeMillis());
        System.out.println(instant);
    }

    @Test
    public void testGetDateFromInstant() {
        new Date(Instant.now().toEpochMilli());
        System.out.println(new Date(Instant.now().toEpochMilli()));
    }

    @Test
    public void testGetInstantFromDate() {
        Instant instant = new Date().toInstant();
        System.out.println(instant);
    }

    @Test
    public void testLocalDateTime() {
//        LocalDateTime now = LocalDateTime.now();
//        int year = now.getYear();
//        int monthValue = now.getMonthValue();
//        int dayValue = now.getDayOfMonth();
//        int hour = now.getHour();
//        int minute = now.getMinute();
//        int second = now.getSecond();
//        DayOfWeek dayOfWeek = now.getDayOfWeek();
//        int value = dayOfWeek.getValue();
        System.out.println("");
    }

    @Test
    public void testLocalDate() {
        LocalDate of = LocalDate.of(2023, 05, 04);
        LocalDate now = LocalDate.now();
        ZonedDateTime zonedDateTime = now.atStartOfDay(ZoneId.systemDefault());
    }

    @Test
    public void testLocalTime() {
        LocalTime now = LocalTime.now();
        LocalTime of = LocalTime.of(23, 11, 11);
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        System.out.println(hour);
        System.out.println(minute);
        System.out.println(second);
    }

    @Test
    public void testPeriod() {
        LocalDate now = LocalDate.now();
        LocalDate of = LocalDate.of(2023, 10, 10);
        Period between = Period.between(now, of);
        int months = between.getMonths();
        System.out.println(between.getYears());
        System.out.println(months);
        System.out.println(between.getDays());
    }

    @Test
    public void testDuration() {
        LocalTime of = LocalTime.of(23, 11, 11);
        LocalTime now = LocalTime.now();
        Duration duration = Duration.between(now, of);
        System.out.println(duration.toHours());
        System.out.println(duration.toMinutes());
        System.out.println(duration.getSeconds());
    }

    @Test
    public void testDateTimeFormatter() {
        //To format an Instant a time-zone is required. Without a time-zone, the formatter does not know how to convert the instant to human date-time fields, and therefore throws an exception
        Instant now = Instant.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());
        String format = dateTimeFormatter.format(LocalDateTime.now());
        System.out.println(format);

        String format1 = dateTimeFormatter.format(Instant.now());
        System.out.println(format1);
    }

    /**
     * 日期转换
     * Temporal 是一个接口Instant LocalDateTime LocalDate LocalTime ZonedDateTime 都实现了这个接口
     * TemporalAdjuster 是一个接口, 里面有一个方法 Temporal adjustInto(Temporal temporal), Instant LocalDateTime LocalDate LocalTim 都实现了这个接口
     */
    @Test
    public void testConvert() {
        /**
         * timestamp = Instant = 是伦敦时间1970年1月1日0时0分0秒(UTC-世界协调时间)开始到现在的总秒数
         * LocalDateTime = timestamp + System.defaultZone(时间的偏移量), 如果我们手动指定了时区那么就会变为ZonedDateTime
         *                 [LocalDate + LocalTime]
         * ZonedDateTime = timestamp + ZoneId(指定时区的偏移量)
         *                 [ZoneId:时区/ZoneOffset:时区偏移量 是ZoneId的子类]
         * Instant -> LocalDateTime
         */
        //Instant convert to LocalDateTime
        Instant instant = Instant.now();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTimeFormatter.format(localDateTime));

        /**
         * LocalDateTime -> Instant: 将本地时间转换为时间戳. 为什么这么说呢?
         * 时间戳: 是伦敦时间1970年1月1日0时0分0秒(UTC-世界协调时间)开始到现在的总秒数. 如果我们希望将LocalDateTime 变为Instant
         * 1、LocalDateTime(是系统的默认时区 这里其实是UTC+8), 需要先将其减去8小时, 变为UTC时间
         * 2、基于UTC时间, 将其转换为时间戳
         */
        //LocalDateTime convert to Instant
        LocalDateTime now = LocalDateTime.now();
        Instant instant2 = now.atZone(ZoneId.systemDefault()).toInstant();
        System.out.println(instant2.toEpochMilli());

        /**
         * LocalDateTime -> ZonedDateTime
         */
        ZonedDateTime of = now.atZone(ZoneOffset.of("+9"));
        // 发现of 和 now 两个结果是一样的, 都是 2023-05-05 10:43:16
        // 只是含义有所不同, of表示的东京时间2023-05-05 10:43:16, now表示的是北京时间的2023-05-05 10:43:16
        System.out.println(dateTimeFormatter.format(of));

        /**
         * 但是如果此时我是北京时间的2023-05-05 10:43:16, 想知道东京时间是什么, 怎么办呢?
         * 需要先转换为时间戳, 让后基于时间戳去计算东京时间
         */
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant2, ZoneOffset.of("+9"));
        System.out.println(dateTimeFormatter.format(zonedDateTime));

        /**
         * ZonedDateTime -> LocalDateTime
         */
        LocalDateTime localDateTime1 = zonedDateTime.toLocalDateTime();
        System.out.println(dateTimeFormatter.format(localDateTime1));

    }

    @Test
    public void testAdjust() {
        LocalDateTime now = LocalDateTime.now();
        LocalTime localTime = now.toLocalTime();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime with = now.with(ChronoField.DAY_OF_WEEK, 2);
        System.out.println(dateTimeFormatter.format(with));

        LocalDateTime localDateTime = now.plusWeeks(1).with(ChronoField.DAY_OF_WEEK, 2)
                .withHour(10)
                .withMinute(0)
                .withSecond(0);
        String format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
        System.out.println(format);

        now.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));

        // 今天的开始时间
        LocalDateTime localDateTime1 = LocalDate.now().atTime(LocalTime.MIN);

        // 今天的结束时间
        LocalDateTime localDateTime2 = LocalDate.now().atTime(LocalTime.MAX);
    }
}
