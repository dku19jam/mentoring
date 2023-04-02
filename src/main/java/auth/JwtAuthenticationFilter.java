package auth;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String token = jwtProvider.resolveToken(request);

        try {
            if (token != null && jwtProvider.validateJwtToken(token)) {
                Authentication authentication = jwtProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            chain.doFilter(request, response);
        } catch (JwtException e) {
//            HttpServletResponse errorResponse = (HttpServletResponse) response;
//            errorResponse.setStatus(401);
//            errorResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            errorResponse.setCharacterEncoding("UTF-8");
//            ErrorResponse exceptionDto = ErrorResponse.from(ErrorCode.TOKEN_NOT_VALIDATED);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            String exceptionMessage = objectMapper.writeValueAsString(exceptionDto);
//
//            errorResponse.getWriter().write(exceptionMessage);
        }

    }
}