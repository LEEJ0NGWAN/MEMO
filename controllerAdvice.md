[돌아가기](https://github.com/LEEJ0NGWAN/Springboot-rest-webservice)

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