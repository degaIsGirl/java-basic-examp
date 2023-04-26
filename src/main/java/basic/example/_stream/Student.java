package basic.example._stream;

import lombok.Data;

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
    private String nickName;

    public Student(String name, Double score, String nickName) {
        this.name = name;
        this.score = score;
        this.nickName = nickName;
    }

    public boolean equals(Student student) {
        if (student.getNickName().equals(this.getNickName())) {
            return true;
        } else {
            return false;
        }
    }
}
