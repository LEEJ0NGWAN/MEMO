[돌아가기](https://github.com/LEEJ0NGWAN/Springboot-rest-webservice)

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
