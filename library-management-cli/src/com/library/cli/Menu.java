package com.library.cli;

import com.library.service.*;
import java.util.Scanner;

public class Menu {
    private final BookService bookService;
    private final MemberService memberService;
    private final LibraryService libraryService;
    private final Scanner scanner;

    // Constructor receives services (dependency injection)
    public Menu(BookService bookService,
                MemberService memberService,
                LibraryService libraryService) {
        this.bookService = bookService;
        this.memberService = memberService;
        this.libraryService = libraryService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            showMenu();
            int choice = getChoice();
            handleChoice(choice);
        }
    }

    private void showMenu() {
        System.out.println("\n=== LIBRARY MANAGEMENT ===");
        System.out.println("1. Add Book");
        System.out.println("2. Register Member");
        System.out.println("3. Issue Book");
        System.out.println("4. Return Book");
        System.out.println("5. View All Books");
        System.out.println("6. View All Members");
        System.out.println("7. Exit");
        System.out.print("Choice: ");
    }

    private int getChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1: addBook(); break;
            case 2: registerMember(); break;
            case 3: issueBook(); break;
            case 4: returnBook(); break;
            case 5: bookService.listAllBooks(); break;
            case 6: memberService.listAllMembers(); break;
            case 7: System.exit(0); break;
            default: System.out.println("Invalid choice");
        }
    }

    private void addBook() {
        System.out.print("Book ID: ");
        String id = scanner.nextLine();
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();

        bookService.addNewBook(id, title, author);
    }

    private void registerMember() {
        System.out.print("Member Name: ");
        String name = scanner.nextLine();

        memberService.registerMember(name);
    }

    private void issueBook() {
        System.out.print("Book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine();

        libraryService.issueBook(bookId, memberId);
    }

    private void returnBook() {
        System.out.print("Book ID: ");
        String bookId = scanner.nextLine();

        libraryService.returnBook(bookId);
    }
}