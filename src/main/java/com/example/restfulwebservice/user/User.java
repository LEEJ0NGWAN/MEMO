package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value = {"password","ssd"})
//@JsonFilter("UserInfo")
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min=2, message = "2글자 이상으로 입력하세요")
    @ApiModelProperty("사용자 이름")
    private String name;

    @Past
    @ApiModelProperty("사용자 등록일")
    @Nullable
    private Date joinDate;

//    @JsonIgnore
    @ApiModelProperty("사용자 패스워드")
    private String password;
//    @JsonIgnore
    @ApiModelProperty("사용자 ssd")
    private String ssd;
}
