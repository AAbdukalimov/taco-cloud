package sia.tacocloud.configs.security.jwt;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sia.tacocloud.configs.security.UserDetailsServiceImpl;
import sia.tacocloud.utility.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static org.springframework.util.StringUtils.hasText;

@Component
@AllArgsConstructor
@Profile("jwt")
public class JwtFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION = "Authorization";
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        if (Objects.nonNull(token) && JwtUtils.validateToken(token)) {
            String userName = JwtUtils.getLogin(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith(JwtUtils.BEARER)) {
            return bearer.substring(JwtUtils.BEARER.length());
        }
        return null;
    }

}
