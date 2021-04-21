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
