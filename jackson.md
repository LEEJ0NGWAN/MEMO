[돌아가기](https://github.com/LEEJ0NGWAN/Springboot-rest-webservice)

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