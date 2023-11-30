package com.example.bookrent.repository;

import com.example.bookrent.domain.Member;
import com.example.bookrent.domain.Rent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
    @Query(value = "SELECT r FROM Rent r WHERE r.book.bookid = :bookid")
    Rent getReferenceById(@Param("bookid") Long bookid);

    Page<Rent> findByRentUser(Pageable pageable, Member member);
}
