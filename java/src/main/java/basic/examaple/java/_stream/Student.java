package basic.examaple.java._stream;

import lombok.Data;

import java.util.Objects;

/**
 * Copyright(C), 2020 - 2023, 小码教育
 *
 * @Date: 2023/4/20 15:55
 * @Description:
 * @Author: mawb<mawb @ xiaoma.cn>
 */

@Data
class Student {
    private String name;
    private Double score;

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public Double getScore() {
        return score;
    }

    public Student setScore(Double score) {
        this.score = score;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public Student setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    private String nickName;

    public Student(String name, Double score, String nickName) {
        this.name = name;
        this.score = score;
        this.nickName = nickName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
