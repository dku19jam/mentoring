package com.dku.mentoring.user.service;


import auth.SecurityUser;
import com.dku.mentoring.user.repository.UserRepository;
import com.dku.mentoring.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAuthoritiesService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        if (userId.isBlank()) {
            //TODO implement exception
        }
        User user = userRepository.findById(Long.valueOf(userId)).orElseThrow();

        return new SecurityUser(user);
    }
}
