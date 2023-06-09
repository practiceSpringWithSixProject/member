# 개요

1. 문제정의
    - 회원 프로필 관리 기능 스펙을 정리하기 위한 문서입니다.
2. 목적 및 배경
    - 회원 프로필 관리를 통해 회원들이 서비스를 보다 편리하게 이용할 수 있습니다.
3. 주요 사용자
    - 가입한 유저
4. User Story / User JourneyMap
    - SNS를 이용하는 사용자는 계정의 커스터마이징을 통해 다른 이용자와 차별화를 가지고 싶어합니다.
    - 개인 정보의 노출 정도를 정하고 싶어합니다.
5. 사용자 가치
    - 회원들이 본인의 프로필을 관리 및 꾸밀 수 있어 서비스 이용에 편의성과 재미를 재공합니다.
6. 개발 원칙
    - 실제 서비스에 적용 전 기능 구현에 중점을 둡니다.

# 기능 목록

- 프로필 관리
- 휴면계정 등록
- 팔로우/언팔로우
- 타임라인

## 자세한 설명

### [기능1] 프로필 관리

- 프로필 사진등록을 할 수 있습니다
- 프로필 관리를 통해 다음의 항목들을 수정할 수 있습니다
    - 프로필 공개/비공개 전환
    - 닉네임
    - 프로필 사진
    - 관심사 설정 (음악, 텍스트, 사진 등)
    - 상태 메시지
    - 휴면 상태를 관리 할 수 있습니다 → 휴면상태: 로그인 불가능 및 프로필 비공개 전환

### [기능2] 팔로우/언팔로우/차단

- 타인의 계정을 팔로우/언팔로우 할 수있습니다
    - 팔로우를 한 계정의 게시물이 저장된 위치를 확인할 수 있습니다.
    - 팔로우한 회원의 게시물이 저장된 위치 조회 범위는 전체 서비스 제공 지역입니다.
    - 게시물을 픽업하는 것은 저장된 위치에 가야 가능합니다.

- 타인의 계정에 대한 차단을 할 수 있습니다
    - 차단된 계정은 서로의 게시물과 프로필 정보를 조회할 수 없게 됩니다.

### [기능3] 타임라인

- 본인이 올린 게시물들을 타임라인에 따라 리스트로 볼 수 있습니다.
    - 해당 타임라인 카드들을 개별로 삭제 할 수 있습니다.

# 마일스톤

- 1차: 프로필 관리 기능 추가
- 2차: 휴면계정 등록 기능 추가
- 3차: 팔로우/언팔로우 기능 추가
- 4차: 타임라인 기능 추가

# FAQ

- Q: 회원 프로필 관리 기능이란 무엇인가요?
    - A: 회원 프로필 관리 기능은 회원들이 자신의 프로필을 관리할 수 있는 기능입니다.
- Q: 회원 프로필 관리 기능을 추가하면 어떤 이점이 있나요?
    - A: SNS서비스의 특성상 회원 본인의 identity를 관리 할 수 있음으로 서비스 가치를 갖습니다.
