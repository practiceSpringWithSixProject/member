# 회원

## 테이블

### column
1. email
1. password
1. nickname
1. sex
1. birth


### 연관 테이블
1. personality
1. point
1. feed
1. likes
1. subscribe
1. preperence

## 기능

### 로그인
1. 필수
    1. email
    1. password
### 회원가입
1. 필수
    1. email
    1. password
    1. nickname
    1. birth
1. 옵션
    1. sex
    1. personality

### 로그아웃
1. 필수
    1. email
    1. password
1. 
### 회원탈퇴

## 주의 사항
### 비밀번호가 암호화 되어야 한다.
### 비밀번호를 암호화하는 키를 레포에 노출하면 안된다.
### 비밀 번호나 아이디는 SQL문이나 JS처럼 실행 가능해선 안된다.
