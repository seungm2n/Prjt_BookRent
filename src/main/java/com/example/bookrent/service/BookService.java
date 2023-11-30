package com.example.bookrent.service;

import com.example.bookrent.NotFoundException;
import com.example.bookrent.controller.BookRegForm;
import com.example.bookrent.domain.Book;
import com.example.bookrent.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    final private BookRepository bookRepository;
    public Page<Book> getList(int page) {
        List<Sort.Order> list = new ArrayList<>();
        list.add(Sort.Order.desc("bookid"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(list));

        return this.bookRepository.findAll(pageable);
    }

    public void create(BookRegForm bookRegForm) {
        Book book = new Book();
        book.setTitle(bookRegForm.getTitle());
        book.setAuthor(bookRegForm.getAuthor());
        book.setState(true);
        book.setRegDate(LocalDate.now());
        this.bookRepository.save(book);
    }

    public Book getModifyBook(Long bookid) {
        Optional<Book> book = this.bookRepository.findById(bookid);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new NotFoundException("Not Found.");
        }
    }

    public void modify(Book book, String Title, String Author) {
        book.setTitle(Title);
        book.setAuthor(Author);
        this.bookRepository.save(book);
    }

    public Book getBook(Long bookid) {
        Book book = this.bookRepository.getReferenceById(bookid);
        book.setState(false);
        this.bookRepository.save(book);

        Optional<Book> oBook = this.bookRepository.findById(bookid);
        if (oBook.isPresent()) {
            return oBook.get();
        } else {
            throw new NotFoundException("Not Found");
        }
    }

    public void returnBook(Book book) {
        System.out.println("book = " + book.getBookid());
        // book = this.bookRepository.findById();
        book.setState(false);
        this.bookRepository.save(book);
    }
}
