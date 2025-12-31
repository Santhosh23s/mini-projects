package com.library.repository;

import com.library.exception.BookNotFoundException;
import com.library.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private List<Book> books;

    public BookRepository() {
        // initialize storage
        books = new ArrayList<>();

    }

    public void addBook(Book book) {
        // store book
        books.add(book);
        System.out.println("The Book added successful");
    }

    public Book findBookById(String bookId) {
        for(Book b:books){
            if(b.getBookId().equals(bookId)){
                return b;
            }
        }
        throw new BookNotFoundException("\nBook Not found");
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
}
