[돌아가기](https://github.com/LEEJ0NGWAN/Springboot-rest-webservice)

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
