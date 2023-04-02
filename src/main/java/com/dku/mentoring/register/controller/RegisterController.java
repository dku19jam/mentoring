package com.dku.mentoring.register.controller;

import com.dku.mentoring.base.dto.response.ResponsePage;
import com.dku.mentoring.global.ResponseIdDto;
import com.dku.mentoring.register.model.dto.list.SummarizedRegisterDto;
import com.dku.mentoring.register.model.dto.request.RegisterRequestDto;
import com.dku.mentoring.register.service.RegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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

    @Operation(summary = "전체 등록 조회", responses = {@ApiResponse(responseCode = "200", description = "전체 등록 조회 성공")})
    @GetMapping
    public ResponsePage<SummarizedRegisterDto> getRegisters(@RequestParam(defaultValue = "1") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        registerService.getRegisters(pageable);
        return new ResponsePage<>(registerService.getRegisters(pageable));
    }

    //TODO 코드 수정
    @Operation(summary = "등록", responses = {@ApiResponse(responseCode = "200", description = "등록 성공")})
    @PostMapping
    public ResponseIdDto register(Long userId, @Valid @RequestBody RegisterRequestDto registerRequestDto) {
        Long registerId = registerService.createRegister(userId,registerRequestDto);
        return new ResponseIdDto(registerId);
    }
}
