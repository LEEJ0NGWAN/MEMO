[돌아가기](https://github.com/LEEJ0NGWAN/Springboot-rest-webservice)

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
