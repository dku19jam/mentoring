package com.dku.mentoring.user.service;


import com.dku.mentoring.global.auth.SecurityUser;
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

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String studentId) throws UsernameNotFoundException {
        if (studentId.isBlank()) {
            //TODO implement exception
        }
        User user = userRepository.findByStudentId(studentId).orElseThrow(
                () -> new UsernameNotFoundException("해당 학번이 존재하지 않습니다."));


        return new SecurityUser(user);
    }
}
