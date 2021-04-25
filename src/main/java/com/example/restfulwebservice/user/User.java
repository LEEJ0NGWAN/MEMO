package com.example.restfulwebservice.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    @Size(min=2, message = "2글자 이상으로 입력하세요")
    private String name;

    @Past
    private Date joinDate;
}
