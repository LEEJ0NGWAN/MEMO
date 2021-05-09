[돌아가기](https://github.com/LEEJ0NGWAN/Springboot-rest-webservice)

# Lombok

프로퍼티에 대한 Setter/Getter, 생성자와 toString와 같은 메소드를 자동으로 생성해주는 라이브러리

→ 프로젝트 속성에서 compiler의 annotation processing enable 필요

# Lombok: @Data

애노테이션을 부착한 어떤 클래스의 Getter/Setter 자동 생성

```java
@Data
public class Hamburger {

	private String name;

}
```

# Lombok: @AllArgsConstructor

애노테이션을 부착한 어떤 클래스의 프로퍼티에 대해 값을 주입하는 생성자 자동 생성

```java
@Data
@AllArgsConstructor
public class Hamburger {

	private String name;

}
```

### @NoArgsConstructor

어떤 프로퍼티도 입력받지 않는 빈 생성자 자동 생성

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hamburger {

	private String name;

}
```

# @Builder

해당 객체에 대한 빌더를 자동 생성

### 클래스 부착

```java
@Builder
public class Hamburger {
	...
}
```

→ 해당 객체의 모든 프로퍼티에 대한 메소드 체이닝 가능

### 생성자 부착

```java
public class User {

	private Long id;
	private String email;
	private String username;
	private String password;

	@Builder
	public User (String email, String username, String password) {
		this.email = email;
		this.username = username;
		this.password = password;
	}
}
```

→ 해당 생성자의 인자로부터 주입 받는 프로퍼티에 대한 메소드 체이닝 가능