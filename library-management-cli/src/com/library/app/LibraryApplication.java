package com.library.app;

import com.library.cli.Menu;
import com.library.repository.*;
import com.library.service.*;

public class LibraryApplication {

    public void start() {
        // Create repositories
        BookRepository bookRepo = new BookRepository();
        MemberRepository memberRepo = new MemberRepository();
        TransactionRepository transactionRepo = new TransactionRepository();

        // Create services
        BookService bookService = new BookService(bookRepo);
        MemberService memberService = new MemberService(memberRepo);
        LibraryService libraryService = new LibraryService(bookRepo, memberRepo, transactionRepo);

        // Create and start menu
        Menu menu = new Menu(bookService, memberService, libraryService);
        menu.start();
    }
}