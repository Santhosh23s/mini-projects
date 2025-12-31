package com.library.service;

import com.library.exception.BookNotFoundException;
import com.library.exception.InvalidOperationException;
import com.library.model.Book;
import com.library.repository.BookRepository;

public class BookService {

    private final BookRepository bookRepository;
    private String newBookId;

    public BookService(BookRepository bookRepository) {
        // initialize repository
        this.bookRepository = bookRepository;
    }

    public void addNewBook(String bookId, String title, String author) {
        bookRepository.addBook(new Book(bookId, title, author));
    }

    public Book getBookById(String bookId) {
        for (Book b: bookRepository.getAllBooks()){
            if(b.getBookId().equals(bookId))
                return b;
        }
        throw new BookNotFoundException("\nBook not found at this bookId: "+bookId);
    }

    public void listAllBooks() {
        // list books
        System.out.println("Book in Repository:");
        for (Book b: bookRepository.getAllBooks()){
            System.out.println(b);
        }
    }

    public boolean isBookAvailable(String bookId) {
        for(Book b: bookRepository.getAllBooks()){
            if(b.getBookId().equals(bookId)){
                return b.isAvailable();
            }
        }
        throw new InvalidOperationException("No Book Available for this book id: "+bookId);
    }
}
