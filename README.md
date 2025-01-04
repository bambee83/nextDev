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
	•	api: API 컨트롤러 및 DTO 관련 모듈.
	•	service: 서비스 계층과 비즈니스 로직을 담은 모듈.
	•	domain (or db): 엔티티, 레포지토리 및 DB 관련 모듈.

- project-root/
- module-api/
    - controller/
    - dto/
    - vo/
- module-service/
    - service/
- module-domain/
    - entity/
    - repository/
- module-core/ (선택, 공통 유틸리티, 설정)
    - conf/
    - utils/
    - exception/