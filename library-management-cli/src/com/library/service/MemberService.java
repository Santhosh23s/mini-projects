package com.library.service;

import com.library.exception.InvalidOperationException;
import com.library.model.Member;
import com.library.repository.MemberRepository;
import com.library.util.IdGenerator;

public class MemberService {

    private final MemberRepository memberRepository;
    private static final int MAX_BORROW_LIMIT = 10;


    public MemberService(MemberRepository memberRepository) {
        // initialize repository
        this.memberRepository = memberRepository;
    }

    public void registerMember(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidOperationException("Name cannot be empty");
        }
        String memberId = IdGenerator.generateMemberId();
        memberRepository.addMember(new Member(memberId, name));
    }

    public Member getMemberById(String memberId) {
        return memberRepository.findMemberById(memberId);
    }

    public void listAllMembers() {
        // list members
        System.out.println("All Members in Service:");
        for(Member m: memberRepository.getMembers()){
            System.out.println(m);
        }
    }

    public boolean canMemberBorrow(String memberId) {
        Member m = memberRepository.findMemberById(memberId);
        if (m == null) throw new InvalidOperationException("Member not found!");
        return m.getBorrowedCount() < MAX_BORROW_LIMIT;
    }

    public boolean isMemberAvailable(String memberId){
        try {
            memberRepository.findMemberById(memberId);
            return true;
        } catch (InvalidOperationException e) {
            return false;
        }
    }
}
