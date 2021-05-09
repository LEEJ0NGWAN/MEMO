[돌아가기](https://github.com/LEEJ0NGWAN/Springboot-rest-webservice)

# Swagger

개발자를 위한 도큐멘트 페이지 생성 도구

json을 표현하기 위한 문서를 넘어, api 도큐먼트와 클라이언트 SDK 문서까지 다양한 생성을 지원

**pom.xml**

```java
<dependencies>

	...

	<dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-boot-starter</artifactId>
      <version>3.0.0</version>
  </dependency>
	...

</dependencies>
```

**build.gradle**

```java
dependencies {

	...

	implementation 'io.springfox:springfox-boot-starter:3.0.0'
	
	...

}
```

## Swagger-ui 설정

스프링 부트와 Swagger 라이브러리 충돌 시 Swagger ui 페이지 매핑 불가능으로 404오류가 발생한다

→ 다음의 코드를 설정한다

```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
```

## Swagger Documentation

Swagger에 나타날 api 도큐먼트의 제공자나 라이센스, produces와 consumes와 같은 커스텀 정보를 설정

### Contact

해당 api 관련 담당자의 정보를 저장하는 객체

```java
Contact DEFAULT_CONTACT = new Contact("이종완","test.com","ljw@test.com");
```

### ApiInfo

해당 api 정보를 저장하는 객체로 빌더의 메소드 체이닝으로 정보를 빌딩한다

```java
Contact DEFAULT_CONTACT = new Contact("이종완","test.com","ljw@test.com");

ApiInfo DEFAULT_API_INFO =
            new ApiInfoBuilder()
            .title("유저 관리 api")
            .description("유저를 관리하는 기능을 제공하는 api 입니다.")
            .version("1.0")
            .termsOfServiceUrl("urn:tos")
            .contact(DEFAULT_CONTACT)
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/license/LICENSE-2.0")
            .build();
```

### Produces & Consumes

api 도큐먼트의 Content-type을 나타내는 정보를 저장하는 객체로 Set<String> 타입을 사용한다

```java
Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
		new HashSet<>(Arrays.asList("application/json", "application/xml"));
```

### Docket에 정보 추가

api 도큐먼트를 커스터마이징하기 위해 Docket 빌드에 Contact, ApiInfo, produces, consumes 정보를 주입하고 빌딩한다

```java
		@Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
```

### ApiModel

Swagger 문서에 설명할 모델로 설정하는 애노테이션

```java
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
public class User {
	...
}
```

### ApiModelProperty

ApiModel로 설정된 객체의 프로퍼티를 설명하는 애노테이션

```java
		@Past
    @ApiModelProperty("사용자 등록일")
    private Date joinDate;

    @ApiModelProperty("사용자 패스워드")
    private String password;
```
