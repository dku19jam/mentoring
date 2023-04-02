package com.dku.mentoring.user.service;

import com.dku.mentoring.global.auth.JwtProvider;
import com.dku.mentoring.team.model.entity.Team;
import com.dku.mentoring.user.entity.User;
import com.dku.mentoring.user.entity.UserRole;
import com.dku.mentoring.user.entity.dto.request.RequestLoginDto;
import com.dku.mentoring.user.entity.dto.request.RequestSignUpDto;
import com.dku.mentoring.user.entity.dto.response.ResponseLoginDto;
import com.dku.mentoring.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public Long signUp(RequestSignUpDto dto) {
        if (userRepository.findByStudentId(dto.getStudentId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 학번입니다.");
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
                .build();
        user.setTeam(team);

        userRepository.save(user);
        return user.getId();
    }

    public ResponseLoginDto login(RequestLoginDto dto) {
        User user = userRepository.findByStudentId(dto.getStudentId()).orElseThrow(() ->
                new BadCredentialsException("학번이 존재하지 않습니다."));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        return ResponseLoginDto.builder()
                .accessToken(jwtProvider.createAccessToken(user.getStudentId(), user.getRoles()))
                .refreshToken(jwtProvider.createRefreshToken(user.getStudentId()))
                .studentId(user.getStudentId())
                .name(user.getName())
                .teamName(user.getTeam().getTeamName())
                .roles(user.getRoles())
                .build();
    }
}
