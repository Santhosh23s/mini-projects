package com.library.model;

import java.time.LocalDate;

public class Transaction {

    private String transactionId;
    private String bookId;
    private String memberId;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private boolean active;

    public Transaction(String transactionId, String bookId, String memberId) {
        this.transactionId = transactionId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = LocalDate.now();  // ✓
        this.active = true;                 // ✓
    }

    public boolean isActive() {
        return active;
    }

    public void markCompleted(LocalDate returnDate) {
        this.returnDate = returnDate;
        this.active = false;
    }


    public LocalDate getIssueDate(){
        return issueDate;
    }

    public LocalDate getReturnDate(){
        return returnDate;
    }

    public String getBookId(){
        return bookId;
    }

    public String getMemberId(){ return memberId;}

    //setters
    public void setMemberId(String memberId){
        this.memberId = memberId;
    }

    public void setTransactionId(String transactionId){
        this.transactionId = transactionId;
    }

    public void setBookId(String bookId){
        this.bookId = bookId;
    }

    public void setIssueDate(LocalDate issueDate){
        this.issueDate = issueDate;
        active = true;
    }

    public void setReturnDate(LocalDate returnDate){
        this.returnDate = returnDate;
        this.active = false;
    }

    @Override
    public String toString(){
        return "Transaction Id: "+transactionId+"\n"+"Book Id: "+bookId+"\n"+"Member Id: "+memberId+"\n"+"Issue Date: "+issueDate+"\n"+"Return Date: "+returnDate+"\n"+"Active: "+active;
    }
}
