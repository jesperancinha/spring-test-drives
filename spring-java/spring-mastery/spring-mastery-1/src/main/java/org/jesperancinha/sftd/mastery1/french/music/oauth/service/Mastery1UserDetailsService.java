package org.jesperancinha.sftd.mastery1.french.music.oauth.service;

import org.jesperancinha.sftd.mastery1.french.music.oauth.domain.Mastery1User;
import org.jesperancinha.sftd.mastery1.french.music.oauth.repository.Mastery1UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class Mastery1UserDetailsService implements UserDetailsService {

    private final Mastery1UserRepository mastery1UserRepository;

    public Mastery1UserDetailsService(Mastery1UserRepository mastery1UserRepository) {
        this.mastery1UserRepository = mastery1UserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final Optional<Mastery1User> userEntity = mastery1UserRepository.findById(username);

        if (userEntity.isPresent()) {
            final Mastery1User mastery1User = userEntity.get();
            return createUserDetails(mastery1User);
        }

        return null;
    }

    private org.springframework.security.core.userdetails.User createUserDetails(Mastery1User mastery1User) {
        return new org.springframework.security.core.userdetails.User(mastery1User.getEmail(), mastery1User.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(mastery1User.getRole())));
    }

}