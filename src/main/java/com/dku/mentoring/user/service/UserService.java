package com.dku.mentoring.user.service;

import com.dku.mentoring.global.auth.JwtProvider;
import com.dku.mentoring.team.model.entity.Team;
import com.dku.mentoring.user.entity.User;
import com.dku.mentoring.user.entity.UserRole;
import com.dku.mentoring.user.entity.dto.request.RequestLoginDto;
import com.dku.mentoring.user.entity.dto.request.RequestSignUpDto;
import com.dku.mentoring.user.entity.dto.response.ResponseLoginDto;
import com.dku.mentoring.user.exception.UserNotFoundException;
import com.dku.mentoring.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public Long signUp(RequestSignUpDto dto) {
        if (userRepository.findByStudentId(dto.getStudentId()).isPresent()) {
            throw new UserNotFoundException("이미 존재하는 학번입니다.");
        }
        User user = User.builder()
                .studentId(dto.getStudentId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .build();

        user.addRole(Collections.singletonList(UserRole.builder().rolename("ROLE_USER").build()));

        Team team = Team.builder()
                .user(user)
                .teamName(dto.getTeamName())
                .mentee(dto.getMenteeName())
                .build();
        user.setTeam(team);

        userRepository.save(user);
        return user.getId();
    }

    public ResponseLoginDto login(RequestLoginDto dto) {
        User user = userRepository.findByStudentId(dto.getStudentId()).orElseThrow(() ->
                new UserNotFoundException("학번이 존재하지 않습니다."));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new UserNotFoundException("비밀번호가 일치하지 않습니다.");
        }
        return ResponseLoginDto.builder()
                .accessToken(jwtProvider.createAccessToken(user.getStudentId(), getRole(user)))
                .refreshToken(jwtProvider.createRefreshToken(user.getStudentId()))
                .studentId(user.getStudentId())
                .name(user.getName())
                .teamName(user.getTeam().getTeamName())
                .build();
    }

    private List<String> getRole(User user) {
        List<String> role = new ArrayList<>();
        List<UserRole> roles = user.getRoles();
        for (UserRole userRole : roles) {
            role.add(userRole.getRolename());
        }

        return role;
    }
}
