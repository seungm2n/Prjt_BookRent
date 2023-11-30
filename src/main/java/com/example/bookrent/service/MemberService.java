package com.example.bookrent.service;

import com.example.bookrent.NotFoundException;
import com.example.bookrent.domain.Member;
import com.example.bookrent.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    final private MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void create(String username, String password) {
        Member member = new Member();

        member.setUsername(username);
        member.setPassword(passwordEncoder.encode(password));

        this.memberRepository.save(member);
    }

    public boolean authenticateUser(String username, String password) {
        Optional<Member> siteUserOptional = memberRepository.findByUsername(username);
        if (siteUserOptional.isPresent()) {
            Member siteUser = siteUserOptional.get();
            return passwordEncoder.matches(password, siteUser.getPassword());
        }
        return false;
    }

    public Member getUser(String username) {
        Optional<Member> memberOptional = memberRepository.findByUsername(username);
        if (memberOptional.isPresent()) {
            return memberOptional.get();
        } else {
            throw new NotFoundException("Not Found");
        }
    }
}
