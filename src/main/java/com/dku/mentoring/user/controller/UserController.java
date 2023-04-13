package com.dku.mentoring.user.controller;

import com.dku.mentoring.user.entity.dto.request.RequestLoginDto;
import com.dku.mentoring.user.entity.dto.request.RequestSignUpDto;
import com.dku.mentoring.user.entity.dto.response.ResponseLoginDto;
import com.dku.mentoring.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "사용자", description = "사용자 인증 및 정보 관련 api")
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
    public ResponseLoginDto login(@RequestBody RequestLoginDto dto) {
        return userService.login(dto);
    }
}
