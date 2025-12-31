package com.library.model;

public class Book {

    private String bookId;
    private String title;
    private String author;
    private boolean available;

    public Book(String bookId, String title, String author) {
        // initialize fields
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getBookId(){
        return bookId;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public boolean isAvailable(){
        return available;
    }

    public void setBookId(String bookId){
        this.bookId = bookId;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setAvailable(boolean available){
        this.available = available;
    }

    @Override
    public String toString(){
        return "\nBook Id: "+bookId+"\n"+"Book Title: "+title+"\n"+"Book Author: "+author+"\n"+"Book Available: "+available;
    }
}
