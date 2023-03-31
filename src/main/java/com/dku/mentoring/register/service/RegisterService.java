package com.dku.mentoring.register.service;

import com.dku.mentoring.register.model.dto.RegisterDto;
import com.dku.mentoring.register.model.entity.Register;
import com.dku.mentoring.register.repository.RegisterRepository;
import com.dku.mentoring.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class RegisterService<E extends Register> {
    protected final RegisterRepository<E> registerRepository;
    protected final UserRepository userRepository;
    protected final FileUploadService fileUploadService;

    //TODO implement exception

    @Transactional(readOnly = true)
    public Page<RegisterDto> list(Specification<E> spec, Pageable pageable, int bodySize) {
        Page<E> result = list(spec, pageable);
        return result.map((register) -> makeListDto(bodySize, register));
    }

    @Transactional(readOnly = true)
    public <T> Page<T> list(Specification<E> spec, Pageable pageable, int bodySize,
                            RegisterResultMapper<T, RegisterDto, E> mapper) {
        Page<E> result = list(spec, pageable);

        return result.map((register) -> {
            RegisterDto dto = makeListDto(bodySize, register);
            return mapper.map(dto, register);
        });
    }

    private Page<E> list(Specification<E> spec, Pageable pageable) {
        if (spec == null) {
            spec = Specification.where(null);
        }
        return registerRepository.findAll(spec, pageable);
    }

    private RegisterDto makeListDto(int bodySize, E register) {
        return new RegisterDto(fileUploadService.getBaseURL(), register);
    }

    @FunctionalInterface
    public interface RegisterResultMapper<T, D, E extends Register> {
        T map(D dto, E register);
    }

}
