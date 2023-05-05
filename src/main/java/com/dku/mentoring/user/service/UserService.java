package com.dku.mentoring.user.service;

import com.dku.mentoring.global.auth.JwtProvider;
import com.dku.mentoring.team.exception.TeamNotFoundException;
import com.dku.mentoring.team.model.entity.Team;
import com.dku.mentoring.team.repository.TeamRepository;
import com.dku.mentoring.user.entity.User;
import com.dku.mentoring.user.entity.UserRole;
import com.dku.mentoring.user.entity.dto.request.RequestChangePasswordDto;
import com.dku.mentoring.user.entity.dto.request.RequestLoginDto;
import com.dku.mentoring.user.entity.dto.request.RequestSignUpDto;
import com.dku.mentoring.user.entity.dto.response.ResponseLoginDto;
import com.dku.mentoring.user.exception.UserNotFoundException;
import com.dku.mentoring.user.repository.UserRepository;
import com.dku.mentoring.user.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserRoleRepository roleRepository;

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

        if(teamRepository.findByTeamName(dto.getTeamName()).isPresent()){
            throw new TeamNotFoundException("이미 존재하는 팀입니다.");
        }
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
        UserRole role = roleRepository.findByUserId(user.getId()).orElseThrow(() ->
                new UserNotFoundException("권한이 존재하지 않습니다."));

        return ResponseLoginDto.builder()
                .accessToken(jwtProvider.createAccessToken(user.getStudentId(), getRole(user)))
                .refreshToken(jwtProvider.createRefreshToken(user.getStudentId()))
                .studentId(user.getStudentId())
                .name(user.getName())
                .teamName(user.getTeam().getTeamName())
                .role(role.getRolename())
                .build();
    }

    @Transactional
    public void changePassword(RequestChangePasswordDto dto, HttpServletRequest request){
        String token = jwtProvider.resolveToken(request);
        String studentId = jwtProvider.getStudentId(token);
        User user = userRepository.findByStudentId(studentId).orElseThrow(IllegalAccessError::new);

        checkPresentPassword(user, dto.getPresentPassword());
        checkSamePassword(user.getPassword(), dto.getNewPassword());
        checkConfirmPassword(dto.getNewPassword(), dto.getNewPasswordConfirm());

        String newPassword = passwordEncoder.encode(dto.getNewPassword());
        user.changePassword(newPassword);
    }

    private void checkPresentPassword(User user, String presentPassword) {
        if(!passwordEncoder.matches(presentPassword, user.getPassword())){
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }
    }

    private void checkSamePassword(String presentPassword, String newPassword) {
        if(passwordEncoder.matches(newPassword, presentPassword)){
            throw new IllegalArgumentException("현재 비밀번호와 새로운 비밀번호가 일치합니다.");
        }
    }

    private void checkConfirmPassword(String newPassword, String newPasswordConfirm) {
        if(!newPassword.equals(newPasswordConfirm)){
            throw new IllegalArgumentException("새로운 비밀번호가 일치하지 않습니다.");
        }
    }

    private List<String> getRole(User user) {
        List<String> role = new ArrayList<>();
        List<UserRole> roles = user.getRoles();
        for (UserRole userRole : roles) {
            role.add(userRole.getRolename());
        }

        return role;
    }

    @Transactional
    public void resetPassword(String studentId, HttpServletRequest request) {
        String token = jwtProvider.resolveToken(request);
        String AdminStudentId = jwtProvider.getStudentId(token);
        User admin = userRepository.findByStudentId(AdminStudentId).orElseThrow(IllegalAccessError::new);

        if(admin.getRoles().stream().noneMatch(role -> role.getRolename().equals("ROLE_ADMIN"))) {
            throw new IllegalArgumentException("관리자가 아니라 권한이 없습니다.");
        }

        User user = userRepository.findByStudentId(studentId).orElseThrow(UserNotFoundException::new);
        user.changePassword(passwordEncoder.encode("12345678!"));
    }
}
