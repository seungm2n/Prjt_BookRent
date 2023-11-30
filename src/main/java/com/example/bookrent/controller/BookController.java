package com.example.bookrent.controller;

import com.example.bookrent.domain.Book;
import com.example.bookrent.service.BookService;
import com.example.bookrent.service.MemberService;
import com.example.bookrent.service.RentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class BookController {

    // 의존성 주입
    final private BookService bookService;
    final private MemberService memberService;
    final private RentService rentService;

    @GetMapping("/modify/{bookid}")
    public String modifyBook(BookRegForm bookRegForm, @PathVariable("bookid") Long bookid) {
        Book book = this.bookService.getModifyBook(bookid);
        bookRegForm.setAuthor(book.getAuthor());
        bookRegForm.setTitle(book.getTitle());

        return "bookReg";
    }

    @PostMapping("/modify/{bookid}")
    public String modifyBookInfo(@Valid BookRegForm bookRegForm, @PathVariable("bookid") Long bookid, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) { return "bookReg"; }
        Book book= this.bookService.getModifyBook(bookid);
        this.bookService.modify(book, bookRegForm.getTitle(), bookRegForm.getAuthor());

        return "redirect:/";
    }

    @PostMapping("/regbook")
    public String regBook(@Valid BookRegForm bookRegForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
                return "bookReg";
        }

        this.bookService.create(bookRegForm);
        return "redirect:/";
    }

    @GetMapping("/regbook")
    public String regBook(BookRegForm bookRegForm) {
        return "bookreg";
    }

    @GetMapping("/")
    public String index(Model m, @RequestParam(value = "page", defaultValue = "0")int page) {
        Page<Book> pagination = this.bookService.getList(page);
        m.addAttribute("pagination",pagination);

        return "index";
    }

}
