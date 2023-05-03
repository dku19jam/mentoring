package com.dku.mentoring.user.controller;

import com.dku.mentoring.user.entity.dto.request.RequestChangePasswordDto;
import com.dku.mentoring.user.entity.dto.request.RequestLoginDto;
import com.dku.mentoring.user.entity.dto.request.RequestResetPasswordDto;
import com.dku.mentoring.user.entity.dto.request.RequestSignUpDto;
import com.dku.mentoring.user.entity.dto.response.ResponseLoginDto;
import com.dku.mentoring.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "회원가입을 합니다.")
    @PostMapping("/signup")
    public String signup(@RequestBody RequestSignUpDto dto) {
        userService.signUp(dto);
        return "회원가입이 완료되었습니다.";
    }

    @Operation(summary = "로그인", description = "로그인을 합니다.")
    @PostMapping("/login")
    public ResponseLoginDto login(@Valid @RequestBody RequestLoginDto dto) {
        return userService.login(dto);
    }

    @Operation(summary = "비밀번호 변경", description = "비밀번호를 변경합니다.")
    @PutMapping("/change/password")
    public String changePassword(@Valid @RequestBody RequestChangePasswordDto dto, HttpServletRequest request) {
        userService.changePassword(dto, request);
        return "비밀번호 변경 완료";
    }

    @Operation(summary = "비밀번호 초기화(관리자)", description = "비밀번호를 초기화합니다.")
    @PutMapping("/reset/password")
    public String resetPassword(@RequestBody RequestResetPasswordDto dto, HttpServletRequest request) {
        userService.resetPassword(dto.getStudentId(), request);
        return "비밀번호 초기화 완료";
    }
}
