package com.ohgiraffers.mapping.section01.entity;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberRegistService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberRegistService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional          // 조회가 아니기 떄문에 데이터베이스에 변경이 일어남 따라서 Transactional 추가해줌!
    public void registMember(MemberRegistDTO newMember) {

        // ============= 만약 정보 더 추가하려면 여기에 로직 만들어주게 된다. ============

        Member member = new Member(
                newMember.getMemberId(),
                newMember.getMemberPwd(),
                newMember.getMemberName(),
                newMember.getPhone(),
                newMember.getAddress(),
                newMember.getEnrollDate(),
                newMember.getMemberRole(),
                newMember.getStatus()
        );

        memberRepository.save(member);

    }

    @Transactional
    public String registMemberAndFindName(MemberRegistDTO newMember) {

        registMember(newMember);

        return memberRepository.findNameById(newMember.getMemberId());

    }
}
