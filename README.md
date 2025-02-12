# NextDev

# ✏️ 개발 환경 

| Tool        | Version               |
|-------------|-----------------------|
| Java        | 11                    |
| Spring Boot | 2.7.5                 |
| MySql       | 8.0.36                |

<br />

---

# ✔ Coding Convention

```

```

<br />

---

# 🙏 Commit Rule

<br />

---

# 📜 Branch Rule

<br />

---

# 🧪 테스트

<br />

---

# 💡 Swagger

http://localhost:8080/swagger-ui/index.html 로 접속

<br />

---

# 배포

### 방법

<br />

---

# 멀티모듈
### 계층 기반 구성 (API, SERVICE, DOMAIN 등)
	•	api: API 컨트롤러 관련 모듈.
	•	service: 서비스 계층과 비즈니스 로직을 담은 모듈.
	•	domain (or db): 엔티티, 레포지토리 및 DB 관련 모듈.

- project-root/
- module-api/
    - aop/
    - conf/ 
    - controller/
    - exception/
    - utils/
- module-service/
    - dto/
    - service/
- module-domain/
    - entity/
    - vo/
    - repository/
- module-core/ (선택, 공통 유틸리티, 설정)
    - conf/
    - exception/


      module-core <- module-domain <- module-service <- module-api
      		
1. module-core (공통 유틸리티, 설정, 예외 처리 등): 가장 아래쪽에 위치하며, 모든 모듈이 참조 가능.
2.	module-domain (엔티티, 레포지토리):
비즈니스 데이터와 데이터 접근 계층을 정의하며, module-core만 참조 가능.
3.	module-service (비즈니스 로직):
            도메인 로직을 처리하며, module-core와 module-domain만 참조 가능.
4.	module-api (컨트롤러, DTO, 요청/응답):
            최상위 계층으로, 외부로 노출되는 API를 담당. 다른 모든 하위 모듈을 참조 가능.
