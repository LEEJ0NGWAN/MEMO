[돌아가기](https://github.com/LEEJ0NGWAN/Springboot-rest-webservice)

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
