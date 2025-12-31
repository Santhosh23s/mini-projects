package com.library.model;

import java.util.ArrayList;
import java.util.List;

public class Member {

    private String memberId;
    private String name;
    private final List<String> borrowedBookIds;
    private int borrowLimit;

    public Member(String memberId, String name) {
        // initialize fields
        borrowedBookIds = new ArrayList<>();
        this.memberId = memberId;
        this.name = name;
        this.borrowLimit = 0;
    }

    public String getMemberId(){
        return memberId;
    }

    public  String getName(){
        return name;
    }

    public int getBorrowedCount(){
        return borrowedBookIds.size();
    }


    public List<String> getBorrowedBookIds(){
        return new ArrayList<>(borrowedBookIds);
    }

    public void returnBook(String bookId){
        borrowedBookIds.remove(bookId);
    }
    public void setMemberId(String memberId){
        this.memberId = memberId;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setBorrowLimit(int limit){
        this.borrowLimit = limit;
    }

    public void borrowBook(String ids){
        borrowedBookIds.add(ids);
    }

    @Override
    public String toString(){
        return "\nMember Id: "+memberId+"\n"+"Member Name: "+name+"\n"+"Member Limit: "+borrowLimit;
    }
}
