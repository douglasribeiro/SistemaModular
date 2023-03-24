package douglas.develop.telefone.config;

import douglas.develop.core.property.JwtConfiguration;
import douglas.develop.security.config.SecurityTokenConfig;
import douglas.develop.security.filter.JwtTokenAuthorizationFilter;
import douglas.develop.security.token.converter.TokenConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Slf4j
public class SecurityCredencialsConfig extends SecurityTokenConfig {
    private final TokenConverter tokenConverter;

    public SecurityCredencialsConfig(JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
        super(jwtConfiguration);
        this.tokenConverter = tokenConverter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterAfter(new JwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter), UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }

}
