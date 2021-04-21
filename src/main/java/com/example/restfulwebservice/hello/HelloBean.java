package com.example.restfulwebservice.hello;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelloBean {

    private String msg;

    // @Data에 의한 자동 msg Setter/Getter
    // @AllArgsConstructor에 의한 msg 필드 생성자 자동 생성
    // @NoArgsConstructor에 의한 빈 생성자 자동 생성
}
