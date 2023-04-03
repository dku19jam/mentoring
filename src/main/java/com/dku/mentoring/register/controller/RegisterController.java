package com.dku.mentoring.register.controller;

import com.dku.mentoring.global.base.dto.response.ResponsePage;
import com.dku.mentoring.global.base.dto.request.ResponseIdDto;
import com.dku.mentoring.register.model.dto.list.SummarizedRegisterDto;
import com.dku.mentoring.register.model.dto.request.RegisterRequestDto;
import com.dku.mentoring.register.model.dto.response.ResponseSingleRegisterDto;
import com.dku.mentoring.register.service.RegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
@Tag(name = "등록", description = "등록 관련 API")
public class RegisterController {

    private final RegisterService registerService;

    @Operation(summary = "전체 등록 글 조회", responses = {@ApiResponse(responseCode = "200", description = "전체 등록 조회 성공")})
    @GetMapping
    public ResponsePage<SummarizedRegisterDto> getRegisters(@RequestParam(defaultValue = "1") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SummarizedRegisterDto> registers = registerService.getRegisters(pageable);
        return new ResponsePage<>(registers);
    }
    @Operation(summary = "미션 인증 글 등록", responses = {@ApiResponse(responseCode = "200", description = "등록 성공")})
    @PostMapping
    public ResponseIdDto register(Long userId, @Valid @RequestBody RegisterRequestDto registerRequestDto) {
        Long registerId = registerService.createRegister(userId, registerRequestDto);
        return new ResponseIdDto(registerId);
    }

    @Operation(summary = "사용자 등록 글 전체 조회", responses = {@ApiResponse(responseCode = "200", description = "사용자 등록 글 전체 조회 성공")})
    @GetMapping("/{userId}")
    public ResponsePage<SummarizedRegisterDto> getRegistersByUser(@PathVariable Long userId,
                                                                  @RequestParam(defaultValue = "1") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return new ResponsePage<>(registerService.getRegistersByUser(userId, pageable));
    }

    @Operation(summary = "등록 글 상세 조회", responses = {@ApiResponse(responseCode = "200", description = "등록 글 상세 조회 성공")})
    @GetMapping("/{registerId}/view")
    public ResponseSingleRegisterDto getRegister(@PathVariable Long registerId) {
        return registerService.findOne(registerId);
    }

    @Operation(summary = "등록 글 수정", responses = {@ApiResponse(responseCode = "200", description = "등록 글 수정 성공")})
    @PutMapping("/{registerId}")
    public ResponseIdDto updateRegister(@PathVariable Long registerId, @Valid Long userId, @Valid @RequestBody RegisterRequestDto registerRequestDto) {
        Long updatedRegisterId = registerService.updateRegister(registerId, userId, registerRequestDto);
        return new ResponseIdDto(updatedRegisterId);
    }

    @Operation(summary = "등록 글 삭제", responses = {@ApiResponse(responseCode = "200", description = "등록 글 삭제 성공")})
    @DeleteMapping("/{registerId}")
    public String deleteRegister(@PathVariable Long registerId, @Valid Long userId) {
        registerService.deleteRegister(registerId, userId);
        return "삭제 성공";
    }

    @Operation(summary = "등록 글 승인", responses = {@ApiResponse(responseCode = "200", description = "등록 글 승인 성공")})
    @PutMapping("/{registerId}/approve")
    public String approveRegister(@PathVariable Long registerId, @Valid Long userId) {
        registerService.approveRegister(registerId, userId);
        return "승인 성공";
    }
}
