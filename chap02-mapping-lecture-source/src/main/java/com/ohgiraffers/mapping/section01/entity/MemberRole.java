package com.ohgiraffers.mapping.section01.entity;

// 참고. enum : Enumerated type
public enum MemberRole {
    /* 필기.
    *   Enum 타입을 사용하게 된다면 코드의 가독성 향상, type safety(타입의 안정성)을 보장할 수 있다.
        서로 연관된 상수들의 집합 클래스이다.
     */

    ROLE_MEMBER,        // 필드가 상수여서 바꿀 수 없기 때문에 대문자로 작성함 (아래로 가면서 필드의 순서가 매겨지면서 매핑되어 저장된다._ORDINAL)
    ROLE_ADMIN


}
