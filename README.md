# 프로젝트 특징

- 스프링부트를 기반으로 CRUD 기능이 포함된 웹서비스
- AWS에 스프링부트 프로젝트 배포


## 개요

- 명칭 : 리멤버 드림
- 개발 인원 : 1명
- 개발 기간 : 2021.03.20 ~ 2021. 03.22
- 주요 기능 : 전체 게시글 조회, 게시글 CRUD
- 개발 언어 : thymeleaf, css, javascript, java 8
- 개발 환경 : springboot 2.4.4, jpa, spring-security, junit5
- 데이터베이스 : mysql
- 형상관리 툴 : git
- 간단 소개 : 꿈(진짜 밤에 꾼 꿈)을 기록하는 커뮤니티

# 사이트
- [드림 리멤버](http://seongbindb.shop)
- [코드 설명영상](https://www.youtube.com/watch?v=WEPJDpo-PQU)
- [프로젝트 시연영상](https://www.youtube.com/watch?v=4oZTOPSNXKg)
# 화면 설계

## 전체페이지

![리멤버드림  전체 페이지](https://user-images.githubusercontent.com/60464424/111857675-60e99880-8976-11eb-9d8a-a108f27744ac.png)


1. 제목 작성자명 작성일을 리스트 형으로 보여준
2. 게시글 작성버튼 클릭시 게시글 작성 페이지로 이동한다.
3. 게시글 제목을 클릭시 게시글 상세조회 페이지로 이동한다.

### 게시글 작성 페이지

![리멤버드림  게시글 작성 페이지](https://user-images.githubusercontent.com/60464424/111857681-7494ff00-8976-11eb-95f7-5c1ce4f92c43.png)

1. 제목 작성자명 내용을 입력받는다.
2. 등록 버튼 클릭시 게시글이 등록되고 전체페이지로 이동한다.
3. 취소버튼 클릭시 전체페이지로 이동한다.

### 게시글 조회 페이지

![리멤버드림  게시글조회](https://user-images.githubusercontent.com/60464424/111857683-765ec280-8976-11eb-8827-e5f2b58133bb.png)

1. 제목 작성자명 작성일 작성내용을 보여준다.
2. 목록으로 클릭시 전체페이지로 이동한다.
3. 수정클릭시 수정되고 수정된 페이지를 보여준다.(리로드)
4. *삭제버튼 클릭시 게시글이 삭제되고 전체페이지로 이동한다.*

# API 설계

<img width="745" alt="스크린샷 2021-03-20 오후 12 22 35" src="https://user-images.githubusercontent.com/60464424/111857745-ff75f980-8976-11eb-98c3-005a1ba111f4.png">

# 테이블 설계

<img width="541" alt="스크린샷 2021-03-23 오후 1 00 01" src="https://user-images.githubusercontent.com/60464424/112090947-cbe5da00-8bd7-11eb-93d0-574d049f666e.png">

### 2021-03-20

1. 초기 환경 셋팅
2. api 설계에 맞게 controller, service, repository 코드 작성 완료

Todo list
- TDD 작성
- view 페이지 작성
- 유효성 검사

### 2021-03-22
1. 스프링 시큐리티 적용
2. 프론트 ,백엔드 유효성 검증 코드 적용
3. 화면 구현 완료
4. TDD 작성 중 (게시글 수정은 아직 미구현)
5. PUT, DELETE 사용을 위한 HttpMethodConfig @configuration 코드 추가

### 2021-03-23
1. AWS RDS (MySql) 생성 및 인텔리제이와 연동 완료
2. AWS EC2 보안 규칙에 8080 포트 개방
3. 기존의 포트포워딩 (80 -> 5000) (80 -> 8080) 포트로 변경
4. 스프링 시큐리티 구현 완료
5. 테스트코드(Junit5) 구현 완료 및 모튼 테스트 통과
6. jar 패키지 및 ec2 서버에 배포
