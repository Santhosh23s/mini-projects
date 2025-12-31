package com.library.service;


import com.library.exception.InvalidOperationException;
import com.library.model.Book;
import com.library.model.Member;
import com.library.model.Transaction;
import com.library.repository.*;
import com.library.util.IdGenerator;

import java.time.LocalDate;

public class LibraryService {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final TransactionRepository transactionRepository;

    public LibraryService(BookRepository bookRepository,
                          MemberRepository memberRepository,
                          TransactionRepository transactionRepository) {
        // initialize repositories
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.transactionRepository = transactionRepository;
    }

    public void issueBook(String bookId, String memberId) {
        final Book book = bookRepository.findBookById(bookId);  // Uses existing method

        if(!book.isAvailable()) {
            throw new InvalidOperationException("Book already borrowed");
        }

        final Member member = memberRepository.findMemberById(memberId);  // Uses existing method

        if(member.getBorrowedCount() >= 10) {
            throw new InvalidOperationException("Borrow limit reached (10 books max)");
        }

        book.setAvailable(false);
        member.borrowBook(bookId);

        String transactionId = IdGenerator.generateTransactionId();
        Transaction transaction = new Transaction(transactionId, bookId, memberId);
        transactionRepository.addTransaction(transaction);

        System.out.println("Book issued successfully to " + member.getName());
    }
    public void returnBook(String bookId) {
        final Book book = bookRepository.findBookById(bookId);
        Transaction transaction = transactionRepository.findActiveTransaction(bookId);
        final Member member = memberRepository.findMemberById(transaction.getMemberId());

        book.setAvailable(true);
        member.returnBook(bookId);
        transaction.markCompleted(LocalDate.now());

        System.out.println("Book returned successfully by " + member.getName());
    }
}
