[돌아가기](https://github.com/LEEJ0NGWAN/Springboot-rest-webservice)

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

