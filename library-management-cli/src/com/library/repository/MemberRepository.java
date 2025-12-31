package com.library.repository;

import com.library.exception.InvalidOperationException;
import com.library.model.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {

    private final List<Member> members;

    public MemberRepository() {
        // initialize storage
        members = new ArrayList<>();
    }

    public void addMember(Member member) {
        // store member
        members.add(member);
        System.out.println("Member added successful");
    }

    public Member findMemberById(String memberId) {
        for(Member m: members){
            if(m.getMemberId().equals(memberId)){
                return m;
            }
        }
        throw new InvalidOperationException("No member Found at this id: "+memberId);
    }

    public List<Member> getMembers(){
        return new ArrayList<>(members);
    }

}
