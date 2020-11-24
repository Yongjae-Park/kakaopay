# 카카오페이 사전과제 - 카카오페이 머니뿌리기 기능 구현하기
## 목차
- [개발 환경](#개발-환경)
- [기능 및 제약사항 및 핵심 문제해결 전략](#기능-요구사항)
- [데이터형식](#기능-데이터형식)

---

## 개발 환경
- 기본 환경
    - IDE: sts-4.8.1
    - OS: Window 10
    - GIT
- Server
    - Java8
    - Spring Boot 2.3.6
    - Mybatis
    - Mysql
    - Maven
    - Junit5


## 빌드 및 실행하기
### 터미널 환경
- Git, Java 는 설치되어 있다고 가정한다.

```
$ git clone https://github.com/Yongjae-Park/kakaopay.git
$ cd kakaopay/kakaopay_task
$ ./mvn clean install -DskipTests
$ java -jar target/kakaopay_task-0.0.1-SNAPSHOT.war
```

- 접속 Base URI: `http://localhost:8080`

## 기능요구사항 제약사항 및 핵심 문제해결 전략
### 필수사항
- 머니뿌리기 API 개발
  - token 생성(3자리 문자열, 예측불가)
    - 64진수 중 하나의 char 무작위로 선택하여 3자리 조합
	  - 3자리 = 2^18의 확률로 겹침 1/262144
  - token값과 함께 뿌릴 금액을 인원수에 맞게 분배하여 저장
    - 금액분배
	  - ThreadLocalRandom.current().nextInt(splashMoney+1) 사용하여 해당금액 이내의 숫자 무작위 추출하여 array에 저장
	  - 마지막 배열인자까지 할당했으나 할당된 money총합이 최초 등록 money보다 작은 경우 마지막 배열인자에 차이값 +
	- splash등록과 분배건 등록은 하나의 트랜잭션으로 처리 @Transactionnal사용
	  - 뿌리기건 자체가 등록되지 않으면 분배건, 뿌리기건 조회 가 불가능하므로 Dirty Read 고려 x
	  - token은 요청마다 고유하게 부여되므로 non-Repeatable read고려 x
	  - 위 사항 바탕으로 race condition 고려 x
	  - isolation level default로 하여 성능 저하 x
- 뿌린 머니 받기 API 개발
  - token에 해당하는 뿌리기건 중 아직 누구에게도 할당되지 않은 분배건 하나 할당
    - is_completed컬럼 flag사용하여 할당여부 확인
  - 뿌리기가 호출된 대화방과 동일한 대화방에 속한 사용자만이 받을 수 있음
    - 대화방 유효성 체크 선행
	  - 대화방id, userid모두 invalid하다면 대화방이 상위검증
  - 뿌리기 당 한 사용자는 한번만 받을 수 있음
    - 중복할당 막기위해 체크, 예외처리
  - 자신이 뿌리기한 건은 자신이 받을 수 없음
    - 본인이 뿌린요청건의 토큰으로 받기요청한 경우 예외처리
  - 뿌린건은 10분간만 유효, 뿌린지 10분이 지난 요청에 대해서는 받기 실패
  - 분배건 중 하나 받은 후 is_completed flag true 처리하는 동작 하나의 트랜잭션으로 처리
    - 선행 유저의 요청으로 가져온 distribution에 대해 is_completed flag 커밋하지 않은 상황에서 또다른 요청 올경우 Dirty read발생 가능성
	- 같은쿼리 두번 실행 아니므로 Non-Repeatable Read고려 x
	- 레코드 두번 이상 읽지 않으므로 Phantom Read 고려 x
	- @Transactional(isolation = Isolation.READ_COMMITTED)처리
- 뿌린 머니 정보 조회 API 개발
  - token에 해당하는 뿌리기 건의 현재 상태 응답값으로 내려줌
  - 응답정보 포함사항 (뿌린시각, 뿌린금액, 받기 완료된 금액, 받기 완료된 정보(\[받은 금액, 받은 사용자아이디]리스트)
  - 뿌린 건에 대한 조회는 7일간만 가능(7일이후 조회불가응답)
    - create_at만 먼저 조회하여 현재시간이랑 비교 후 조회 유효기간 지났으면 조회리스트 조회하지 않고 예외처리
  - 뿌린사람 본인만 조회가능
    - 요청받은 x_user_id와 조회한 데이터 내 뿌리기건 등록한 유저id비교, 유효하지 않으면 예외처리
  - 다른사람의 뿌리기건이나 유효하지 않은 token에 대해서 실패
  
## 데이터형식
### 1. money 뿌리기 API개발
- Request

```
http://localhost:8080/Splash
```

```
POST / HTTP/1.1
"X-USER-ID" : "muzy",
"X-ROOM-ID" : "room1"
```
```json
{
    "splashMoney" : 100000,
	"personnel" : 5
}
```
- Response
  - 성공응답
    - token을 내려줌
  - 실패응답
    - 없음
    - 모든 뿌리기에 잔액은 충분하다고 가정하므로 뿌리기 서비스가 실패할 경우는 없음
```json
{
    "token" : "#Fp"
}
```
##
### 2. 뿌리기건 받기 API 개발
- Request

```
http://localhost:8080/Distributions
```

```
GET / HTTP/1.1
"X-USER-ID" : "muzy",
"X-ROOM-ID" : "room1"
```
- Request

```json
{
    "token" : "#Fp"
}
```

- Response
  - 성공응답
    - 분배건에 대한 금액
  - 실패응답
    - 1. 뿌린 본인이 받기 요청한 경우
	- 2. 이미 받아간 사용자가 다시 받기 요청한 경우
	- 3. 요청 사용자가 대화방에 속하지 않은 경우
	- 4. 유효시간(10분)이 지난 뿌리기건에 대한 요청인 경우
```json
{
    "winMoney" : 5000
}

예외 응답
//1. 뿌린 본인이 받기 요청한 경우
{
    "code": 1001,
    "message": "뿌리기한 본인은 받을 수 없습니다."
}
//2.이미 받아간 사용자가 다시 받기 요청한 경우
{
    "code": 1002,
    "message": "이미 뿌리기를 받아간 유저입니다."
}
//3.요청 사용자가 대화방에 속하지 않은 경우
{
    "code": 1003,
    "message": "잘못된 대화방입니다."
}
//4. 유효시간(10분)이 지난 뿌리기건에 대한 요청인 경우
{
    "code": 1004,
    "message": "받을 수 있는 유효시간이 초과하였습니다."
}
```
##
### 3. 뿌리기건 조회 API 개발
- Request

```
http://localhost:8080/Splash
```

```
GET / HTTP/1.1
"X-USER-ID" : "muzy",
"X-ROOM-ID" : "room1"
```

```json
{
    "token" : "#Fp"
}
```
- Response
  - 성공응답
    - 뿌리기건의 현재 상태값
  - 실패응답
    - 1. 조회 유효기간(7일)이 지난 경우
    - 2. 뿌린 사람이 아닌 다른 사용자가 조회요청을 할 경우
```json
{
    "createdAt": "2020-11-23T16:06:49",
    "splashedMoney": 30000,
    "completedMoney": 26228,
    "userList": [
        {
            "userIdTaken": "rion6",
            "allocatedMoney": 23490
        },
        {
            "userIdTaken": "rion2",
            "allocatedMoney": 2738
        }
    ]
}

//예외응답
//1. 조회 유효기간(7일)이 지난 경우
{
    "code": 1005,
    "message": "만료된 뿌리기건 입니다."
}
//2. 뿌린 사람이 아닌 다른 사용자가 조회요청을 할 경우
{
    "code": 1006,
    "message": "뿌리기한 유저만 조회할 수 있습니다."
}
```

