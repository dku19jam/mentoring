package com.dku.mentoring.register.controller;

import com.dku.mentoring.global.auth.JwtProvider;
import com.dku.mentoring.global.auth.SecurityUser;
import com.dku.mentoring.global.base.dto.response.ResponsePage;
import com.dku.mentoring.global.base.dto.request.ResponseIdDto;
import com.dku.mentoring.register.model.dto.list.SummarizedRegisterDto;
import com.dku.mentoring.register.model.dto.request.RegisterRequestDto;
import com.dku.mentoring.register.model.dto.response.SingleRegisterResponseDto;
import com.dku.mentoring.register.service.RegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
@Tag(name = "등록", description = "등록 관련 API")
public class RegisterController {

    private final RegisterService registerService;
    private final JwtProvider jwtProvider;

    @Operation(summary = "전체 등록 글 조회", responses = {@ApiResponse(responseCode = "200", description = "전체 등록 조회 성공")})
    @GetMapping
    public ResponsePage<SummarizedRegisterDto> getRegisters(@RequestParam(defaultValue = "1") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SummarizedRegisterDto> registers = registerService.getRegisters(pageable);
        return new ResponsePage<>(registers);
    }

    @Operation(summary = "사용자 등록 글 전체 조회", responses = {@ApiResponse(responseCode = "200", description = "사용자 등록 글 전체 조회 성공")})
    @GetMapping("/{userId}")
    public ResponsePage<SummarizedRegisterDto> getRegistersByUser(HttpServletRequest request,
                                                                  @RequestParam(defaultValue = "1") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return new ResponsePage<>(registerService.getRegistersByUser(request, pageable));
    }

    @Operation(summary = "등록 글 상세 조회", responses = {@ApiResponse(responseCode = "200", description = "등록 글 상세 조회 성공")})
    @GetMapping("/{registerId}/view")
    public SingleRegisterResponseDto getRegister(@PathVariable Long registerId) {
        return registerService.findOne(registerId);
    }

    @Operation(summary = "등록 글 수정", responses = {@ApiResponse(responseCode = "200", description = "등록 글 수정 성공")})
    @PutMapping("/{registerId}")
    public ResponseIdDto updateRegister(@PathVariable Long registerId, HttpServletRequest request, @Valid @RequestBody RegisterRequestDto registerRequestDto) {
        Long updatedRegisterId = registerService.updateRegister(registerId, request, registerRequestDto);
        return new ResponseIdDto(updatedRegisterId);
    }

    @Operation(summary = "등록 글 삭제", responses = {@ApiResponse(responseCode = "200", description = "등록 글 삭제 성공")})
    @DeleteMapping("/{registerId}")
    public String deleteRegister(@PathVariable Long registerId, HttpServletRequest request) {

        registerService.deleteRegister(registerId, request);
        return "삭제 성공";
    }

    @Operation(summary = "등록 글 승인", responses = {@ApiResponse(responseCode = "200", description = "등록 글 승인 성공")})
    @PutMapping("/{registerId}/approve")
    public String approveRegister(@PathVariable Long registerId, HttpServletRequest request) {

        registerService.approveRegister(registerId, request);
        return "승인 성공";
    }

    //TODO userId, missionId 등등 따로 있는 애들 dto에 넣고 modelAttribute로 변경 MultiPartFile도 함께 받아서 이미지 저장 로직에 태우기
    @Operation(summary = "미션 인증 글 등록", description = "미션 인증 글 등록")
    @PostMapping(value = "/{missionId}/register")
    public ResponseIdDto register(@Valid Long userId, @PathVariable Long missionId, @Valid @RequestBody RegisterRequestDto dto) {
        Long registerId = registerService.createRegister(userId, missionId, dto);
        return new ResponseIdDto(registerId);
    }
}
