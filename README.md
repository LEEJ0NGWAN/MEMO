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


# XML response body

### request Headers : Accept

request에 Accept 헤더를 설정하는 것을 통해 리스폰스로 허용할 데이터 타입 지정 가능

- application/json

    rest api 리스폰스 바디로 흔하게 사용되는 json

- application/xml

    xml 폼으로 전송

# Jackson

리스폰스에 대해 여러 기능을 지원하는 의존성

**pom.xml**

```java
<dependencies>

	...

	<dependency>
		<groupId>com.fasterxml.jackson.dataformat</groupId>
		<artifactId>jackson-dataformat-xml</artifactId>
	</dependency>

	...

</dependencies>
```

**build.gradle**

```java
dependencies {

	...

	implementation 'com.fasterxml.jackson.dataformat:jackson-dataformat-xml'
	
	...

}
```

## Default Content-Type

jackson 의존성을 추가하면, 디폴트 리턴 타입을 xml로 설정한다

→ 이를 방지하고 디폴트 바디 타입을 다른 타입으로 설정하려면 RequestMapping의 produces를 별도 설정

```java
@RequestMapping(produces="application/json")
@RestController
public class UserController {

	...
}
```

## Filtering

리스폰스를 넘겨줄 때, 넘겨주거나 넘겨주지 않을 프로퍼티 필터링

### @JsonIgnore

어떤 Entity의 필드에 추가해서 Json Response에 담기지 않도록 설정하는 애노테이션

```java
@JsonIgnore
private String password;

@JsonIgnore
private String address;
```

### @JsonIgnoreProperties

클래스에 추가해서 Json Response에 담기지 않을 필드를 설정하는 애노테이션

```java
@JsonIgnoreProperties(value = { "password", "address")
public class User {

	...
	private String password;
	private String address;
}
```

### @JsonFilter

클래스에 추가해서 별도의 필터 제공자로 필터링 되지 않으면 리스폰스 될 수 없도록 설정하는 애노테이션

```java
@JsonFilter("UserFilter") // UserFilter 라는 이름의 필터 아이디 설정
public class User {
	...
}
```

```java
@GetMapping(path = "/users")
public ResponseEntity<MappingJacksonValue> retrieveAllUsers() {

    List<User> list = userService.findAll();

    SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter
            .filterOutAllExcept("id","name","joinDate","password","ssn");

    FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo", simpleBeanPropertyFilter);

    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
    mappingJacksonValue.setFilters(filterProvider);

    return ResponseEntity.ok(mappingJacksonValue);
}
```

필터 객체를 추가한 필터 제공자로 필터링 된 MappingJacksonValue 여야만 리스폰스 전송 가능

# Version

api 버전을 관리할 수 있다

### URL Versioning

api url에 버전을 추가

e.g.,

- v1: /v1/users/{id}
- v2: /v2/users/{id}

### Param Versioning

request parameter 를 이용하여 버전을 추가

→ Mapping 애노테이션에 param 값을 추가한다; 요구되는 param 없을 시 자동 400 처리

e.g.,

**v1**

```java
@GetMapping(value = "/users/{id}", param = "version=1")
public ResponseEntity<User> retrieveUserV1 (@PathVariable int id) {
	...
}
```

**v2**

```java
@GetMapping(value = "/users/{id}", param = "version=2")
public ResponseEntity<User> retrieveUserV2 (@PathVariable int id) {
	...
}
```

### Header Versioning

request header를 이용하여 버전을 설정

→ Mapping 애노테이션에 headers 값을 추가한다; 요구되는 headers 없을 시 자동 404 처리

e.g.,

**v1**

```java
@GetMapping(value = "/users/{id}", headers = "X-API-VERSION=1")
public ResponseEntity<User> retrieveUserV1 (@PathVariable int id) {
	...
}
```

**v2**

```java
@GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2")
public ResponseEntity<User> retrieveUserV2 (@PathVariable int id) {
	...
}
```

### Produce Versioning

Accept response type을 이용하여 버전을 설정

→ Mapping 애노테이션에 produces 값을 추가한다;

⇒ client 측은 request header에 Accept 키 밸류 페어를 추가 후 사용한다

e.g.,

**v1**

```java
@GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv1+json")
public ResponseEntity<User> retrieveUserV1 (@PathVariable int id) {
	...
}
```

**v2**

```java
@GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv2+json")
public ResponseEntity<User> retrieveUserV2 (@PathVariable int id) {
	...
}
```

produces는 맵핑의 디폴트 리스폰스 타입을 정하는 것으로, 버저닝과 + 기호를 이용한 타입 설정 가능

# HATEOAS

### Hypermedia As The Engine Of Application State

현재 리소스와 연관된 (호출 가능한) 자원 상태 정보를 제공

**pom.xml**

```java
<dependencies>

	...

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-hateoas</artifactId>
	</dependency>

	...

</dependencies>
```

**build.gradle**

```java
dependencies {

	...

	implementation 'org.springframework.boot:spring-boot-starter-hateoas'
	
	...

}
```

e.g., user에 대한 link 레퍼런스 추가

```java
EntityModel<User> userEntityModel = EntityModel.of(user);
WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUsers());

userEntityModel.add(linkTo.withRel("all-users"));

return userEntityModel;
```