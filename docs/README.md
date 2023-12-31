# 기능 구현 목록

## Model

### EventCalendar (12월 이벤트 달력)

- [x] 방문 날짜로 달력을 생성한다
    - [x] 방문 날짜가 12월 날짜가 아니면 에러 발생한다
- [x] 방문 날짜가 특별한 날인지 확인한다
- [x] 방문 날짜가 평일인지 확인한다
- [x] 방문 날짜가 주말인지 확인한다
- [x] 방문 날짜가 크리스마스 전인지 확인한다

### Menu (메뉴) -> ENUM

- [x] 메뉴 이름(UK), 메뉴 가격, 메뉴 종류로 메뉴를 정의한다 (예시. ICECREAM(아이스크림, 5000, DESERT))
- [x] 메뉴 이름으로 메뉴가 존재하는지 확인한다
- [x] 메뉴 이름으로 메뉴 종류를 찾는다
- [x] 메뉴 종류로 메뉴 목록을 찾는다

### MenuType (메뉴 종류)

- [x] 메뉴 종류 이름(UK)으로 메뉴 종류를 정의한다 (예시. DESERT(디저트))

### Order (주문)

- [x] 메뉴(PK), 개수로 주문을 생성한다
    - [x] 메뉴 개수 1 이상의 숫자 아니면 예외 처리 (M)
    - [x] 없는 메뉴면 예외 처리

### Orders (주문서)

- [x] 빈 주문 목록으로 주문서를 생성한다
- [x] 주문을 주문 목록에 추가한다
    - [x] 메뉴 이름
        - [x] 중복 메뉴 입력하면 예외 처리 (M)
        - [x] 음료만 주문하면 예외 처리 (M)
    - [x] 메뉴 개수
        - [x] 총 메뉴 개수 20개 초과하면 예외 처리 (M)
- [x] 주문 목록을 찾는다 (주문 결과를 생성한다)
- [ ] 메뉴 종류로 주문 개수를 찾는다
- [x] 주문 목록의 메뉴 가격 합게를 계산한다 (할인 전 총주문 금액을 계산한다)

### DiscountEvent (할인 이벤트) -> ENUM

- [x] 이벤트 이름(UK), 할인 금액 계산 기능로 할인 이벤트를 정의한다 (예시. CHRISTMAS_DAY(크리스마스 디데이 할인, 5000 반환))

### GiftEvent (증정 이벤트) -> ENUM

- [x] 증정 메뉴(UK), 증정 개수, 증정 적용 여부 확인 기능으로 증정 이벤트를 정의한다
- [x] 총주문 금액에 따라 적용 가능한 증정 이벤트를 찾는다

### BadgeEvent (배지 이벤트) -> ENUM

- [x] 배지 이름(UK), 배지 적용 여부 확인 기능으로 배지 이벤트를 정의한다
- [x] 총혜택 금액에 따라 적용 가능한 배지 이벤트를 찾는다

### EventPlanner (이벤트 플래너)

- [x] 주문 목록에 이벤트를 적용한다

## View

### InputView

- [x] 방문 날짜를 입력 받는다
    - [x] 숫자가 아니면 에러 발생한다
- [x] 주문할 메뉴와 개수를 입력 받는다
    - [x] 메뉴 형식이 예시와 다르면 에러 발생한다

### OutputView

- [x] 에러 메시지를 출력한다
- [x] 프로그램 시작 메시지를 출력한다
- [x] 이벤트 혜택 시작 메시지를 출력한다
- [x] 주문 내역을 출력한다
    - [x] 주문 메뉴를 출력한다 (메뉴와 개수)
    - [x] 할인 전 총주문 금액을 출력한다 (메뉴 가격 합한 거)
- [x] 이벤트 적용 결과를 출력한다
    - [x] 증정 메뉴를 출력한다
    - [x] 혜택 내역을 출력한다
    - [x] 총혜택 금액을 출력한다
    - [x] 할인 후 예상 결제 금액을 출력한다
    - [x] 12월 이벤트 배지 내용을 출력한다

## EventPlannerController

- [x] 에러를 관리한다
- [x] 실행한다
    - [x] 준비한다
    - [x] 주문을 받는다
    - [x] 이벤트를 적용한다

# 부록

## 기능 요구사항 분석

- 기획
    - 이벤트 계획
        - 크리스마스 디데이 할인 : 1일에 1000원으로 시작해서 25일까지 매일 100원씩 증가해 할인 (25일은 3400원 할인)
        - 평일 할인 : 일~목에 디저트 메뉴를 1개당 2023원 할인
        - 주말 할인 : 금, 토에 메인 메뉴를 1개당 2023원 할인
        - 특별 할인 : 특별한 날짜에 총주문 금액에서 1000원 할인
        - 증정 이벤트 : 할인 전 총주문 금액이 12만원 이상일 때, 샴페인 1개 증정 (25000원)
    - 이벤트 특이사항
        - 이벤트는 중복 적용 가능
        - 이벤트 기간은 2023.12.1 ~ 2023.12.31 (크리스마스 디데이 할인은 제외)
        - 총혜택 금액에 따라 이벤트 배지 부여 (5천원 이상 별, 1만원 이상 트리, 2만원 이상 산타)
        - 총주문 금액 1만원 이상부터 이벤트 적용
        - 음료만 주문 시, 주문할 수 없음
        - 메뉴는 한 번에 최대 20개까지만 주문할 수 있음
- 개발 요구사항
    - 요약 : 고객들이 식당에 방문할 날짜와 메뉴 선택 -> 이벤트 플래너가 이벤트 적용 결과 알려줌
    - 예상 방문 날짜 입력 받기
    - 주문할 메뉴와 개수 입력 받기
    - 이벤트 적용 결과 알려주기
        - 주문 메뉴 : 출력 순서 자유, 메뉴 이름과 개수
        - 할인 전 총주문 금액
        - 증정 메뉴 : 없으면 "없음" 출력
        - 혜택 내역 : 없으면 "없음 출력", 할인과 증정 내역 + 각각의 혜택 금액
        - 총혜택 금액
        - 할인 후 예상 결제 금액 : 할인 전 총 주문 금액 - 총할인 금액 (총혜택 금액 - 증정 메뉴 가격)
        - 12월 이벤트 배지 내용 : 없으면 "없음" 출력, 총혜택 금액에 따라 결정
- 예외 처리
    - 예상 방문 날짜 입력
        - 숫자 아니면 예외 처리 (V)
        - 1~31 사이 숫자 아니면 예외 처리 (M)
    - 주문할 메뉴와 개수 입력
        - 메뉴 형식이 예시(문자열-숫자,)와 다르면 예외 처리 (V)
        - 메뉴 이름
            - 없는 메뉴면 예외 처리 (M)
            - 중복 메뉴 입력하면 예외 처리 (M)
            - 음료만 주문하면 예외 처리 (M)
        - 메뉴 개수
            - 각 메뉴 개수 1 이상의 숫자 아니면 예외 처리 (M)
            - 총 메뉴 개수 20개 초과하면 예외 처리 (M)

## 프로그래밍 요구사항 분석

- 공통
    - 클린 : indent 2까지 허용, 3항 연산자 금지, 메서드는 15라인 이하로 한 가지 일, else 금지, switch/case 금지
    - 에러 : 에러 메시지 출력 후 다시 입력 받기 -> 모든 에러 메시지는 "[ERROR]"로 시작
    - 테스트 : 도메인 로직(UI 제외)에 단위 테스트 구현, 기능 목록 동작을 테스트 코드로 확인
    - 분리 : 핵심 로직과 UI 로직 분리, InputView/OutputView 구현
        - 라이브러리 : Colsole.readLine() 사용
- 필수 테스트
    - 전부 입력 받고, 타이틀 출력
    - 26일에 타파스-1,제로콜라-1은 총주문금액이 10000원 안 넘으므로 혜택 내역 없음 출력
    - 숫자 아닌 날짜 예외 처리 -> [ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.
    - 숫자 아닌 메뉴 개수 예외 처리 -> [ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.

## 스토리텔링식 설계

- 이야기
- 협력 : 요청과 응답
- 책임 : 하는 것(요청, 계산)과 아는 것
- 역할 : 책임의 집합(재사용성)

## 메모

- 용어
    - (할인 전) 총주문 금액 : 메뉴 시킨 거 가격 합 -> 출력
    - 할인 금액 : 메뉴에서 가격 할인 받은 거
    - 총혜택 금액 : 할인 금액의 합계 + 증정 메뉴의 가격 -> 배지 결정, 출력
    - 할인 후 예상 결제 금액 : 총주문 금액 - 할인 금액 == 총주문 금액 - 총 혜택 금액 + 증정 메뉴의 가격 -> 출력
- 플로우
    - 예상 방문 날짜, 주문 메뉴와 개수 입력 받음
    - 이벤트를 적용해서 혜택을 계산함
    - 주문 내역을 먼저 보여줌
        - 메뉴와 개수 보여줌
        - 메뉴 가격 합한 거 -> (할인 전) 총주문 금액 보여줌
    - 혜택을 보여줌
        - 증정 메뉴와 개수 보여줌
        - 할인과 증정 내용과 가격 보여줌
        - 할인과 증정 내용의 가격 합한 거 -> 총혜택 금액 보여줌
        - 총주문 금액에서 할인 내용의 가격 뺀 거 -> 할인 후 예상 결제 금액 보여줌
        - 총혜택 금액에 따라 결정된 배지 이름 보여줌
- 관계
    - (놉) 메뉴가 주문서에 기록되다 -> 메뉴 : 주문서는 다:다임 -> 메뉴와 주문서 사이에 주문 메뉴 테이블 추가
    - (수용) 메뉴가 주문서에 기록되다 -> 메뉴 : 주문서는 현재는 다:1임 -> 주문 메뉴 테이블을 주문서에 포함시킴
    - 메뉴:메뉴종류는 다:1임 -> 메뉴에 메뉴종류를 포함시킴
    - save(추가한다), update(수정한다), find(찾는다), delete(제거한다)
    - exists/check(존재하는지 확인한다), sum(더한다), generate(결과를 만든다), calculate(계산한다)
    - 주문에 이벤트를 적용한다 -> 주문 : 이벤트는 다:다임 -> 주문과 이벤트 사이에 이벤트적용 테이블 추가