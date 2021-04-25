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

# application.properties or application.yml

### application.properties

```java
logging.level.org.springframework = debug
```

### application.yml

```java
logging:
	level:
		org.springframework: debug
```

# DispatcherServlet

spring 프레임워크에서 사용하는 front controller

- 클라이언트의 모든 요청을 한 곳에서 받아 필요한 처리
- 요청에 맞는 handler로 요청을 dispatch(전달)
- 각 handler의 결과를 HTTP response로 가공 및 반환

### Front Controller

레거시의 방식(사용자 요청 유형마다 web.xml에 새로운 servlet 등록 및 맵핑)과 달리, 하나의 servlet에서 모든 요청을 받아들여 적절한 controller로 요청을 위임하는 패턴

## Request dispatch 순서

1. request → DispatcherServlet
2. DispatcherServlet → HandlerMapping
3. DispatcherServlet → Controller
4. Controller → DispatcherServlet (Model과 View 반환)
5. DispatcherServlet → ViewResolver
6. DispatcherServlet → View

# RestController

Spring4부터 지원하는 Spring Bean

→ `@Controller` + `@ResponseBody` 의 형태로, View를 갖지 않는 REST Data(json, XML)를 반환한다

### http request → http reponse 과정

http request

→ DispatcherServlet → Handler Mapping → REST Controller → http response(json, xml)


# ResponseEntity

HTTP status code를 포함한 response 응답을 만들기 위한 클래스

→ 여러 상태코드를 포함하는 response 객체 생성 static method가 정의 되어 있다

e.g., 생성된 user에 대한 api 설정

```java
URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

return ResponseEntity.created(location).build();
// CREATED[201] 상태 코드를 가지고, 헤더에 location 값이 빌드된 객체 리턴
```

e.g., 특정 객체 파싱 및 상태 코드

```java
return new ResponseEntity(new Something(), HttpStatus.INTERNAL_SERVER_ERROR);
```

## @ResponseStatus

메소드 또는 exception class에 부착되는 애노테이션으로, 반환되는 response의 http status 설정 가능

e.g., user not found exception

```java
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String format) {
        super(format);
    }
}
```

# @ControllerAdvice

모든 컨트롤러가 실행 될 때, 사전에 실행될 빈 객체를 나타내는 애노테이션

e.g., aop setting

```java
@RestController
@ControllerAdvice
public class ...
```

# ResponseEntityExceptionHandler

Controller에서 발생하는 예외에 대해 적절한 http status코드와 함께 리스폰스를 반환 및 에러 핸들링하도록 돕는 베이스 클래스


# @ExceptionHandler

어떤 메소드 또는 클래스가 어떤 예외를 다루는 핸들러라는 것을 나타내는 애노테이션

e.g., all exception handler

```java
@ExceptionHandler(Exception.class)
public final ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {
		...
}
```

# Validation

ORM 관련하여 Entity에 여러 제약사항을 편리하게 추가시켜주는 애노테이션을 제공

SpringBoot 2.3.0부터 starter web 의존성에 spring-boot-starter-validation이 제외 되었다

→ pom.xml 또는 build.gradle 에 추가적으로 spring-boot-starter-validation을 추가해줘야 한다

**pom.xml**

```java
<dependencies>

	...

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-validation</artifactId>
	</dependency>

	...

</dependencies>
```

**build.gradle**

```java
dependencies {

	...

	implementation 'org.springframework.boot:spring-boot-starter-validation'
	
	...

}
```

## @Valid

부착 대상이 되는 어떤 객체의 유효성을 검사하는 애노테이션

프로퍼티, 메소드 파라매터, 리턴 타입이 되는 객체 등에 부착될 수 있다

e.g.,

user 생성 api에서 리퀘스트 json 유효성 검사

```java
@PostMapping(path="/users")
public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

	...

}
```

### MethodArgumentNotValidException

@Valid 애노테이션의 유효성 검사를 통과하지 못하면 발생하는 Exception
