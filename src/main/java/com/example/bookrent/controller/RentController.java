package com.example.bookrent.controller;

import com.example.bookrent.domain.Book;
import com.example.bookrent.domain.Member;
import com.example.bookrent.domain.Rent;
import com.example.bookrent.service.BookService;
import com.example.bookrent.service.MemberService;
import com.example.bookrent.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class RentController {

    // 의존성 주입
    final private RentService rentService;
    final private BookService bookService;
    final private MemberService memberService;

    @GetMapping("/rent/{bookid}")
    public String rentBook(@PathVariable("bookid") Long bookid, Model m){

        Book book = this.bookService.getBook(bookid);

        Member member = this.memberService.getUser(book.getTitle());

        this.rentService.createRent(book, member);

        return "redirect:/";
    }

    @GetMapping("/rent/return/{bookid}")
    public String returnBook(@PathVariable("bookid") Long bookid){
        Rent rent = this.rentService.returnBook(bookid);

        // Rent 엔터티에서 Book 엔터티를 얻어옵니다.
        Book book = rent.getBook();

        // Book 엔터티를 업데이트합니다.
        this.bookService.returnBook(book);

        return "redirect:/";
    }


}

