package com.example.bookrent.service;

import com.example.bookrent.domain.Book;
import com.example.bookrent.domain.Member;
import com.example.bookrent.domain.Rent;
import com.example.bookrent.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentService {

    final private RentRepository rentRepository;

    public void createRent(Book book, Member member) {
        Rent rent = new Rent();

        rent.setBook(book);
        rent.setRentUser(member);
        rent.setRentDate(LocalDate.now());
        rent.setState(true);

        this.rentRepository.save(rent);
    }

    public Rent returnBook(Long bookid) {
        System.out.println("RentService.returnBook");
        Rent rent = this.rentRepository.getReferenceById(bookid);
        rent.setState(false);
        this.rentRepository.save(rent);

        return rent;
    }

    public Page<Rent> getMyList(int page, Member member) {
        List<Sort.Order> sorts= new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page,20,Sort.by(sorts));
        return this.rentRepository.findByRentUser(pageable,member);
    }
}
