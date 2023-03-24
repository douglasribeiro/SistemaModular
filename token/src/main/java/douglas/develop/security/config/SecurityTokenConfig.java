package douglas.develop.security.config;

import douglas.develop.core.property.JwtConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {
    protected final JwtConfiguration jwtConfiguration;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint((req,resp,e)-> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED) )
                .and()
                .authorizeRequests()
                .antMatchers(jwtConfiguration.getLoginUrl(), "/**/swagger-ui.html").permitAll()
                .antMatchers(HttpMethod.GET, "/**/swagger-resources/**", "/**/webjars/springfox-swagger-ui/**", "/**/v2/api-docs/**").permitAll()
                .antMatchers("/auth/user/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET,"/usuario/v1/**").hasRole("DIRECTOR")
                .antMatchers(HttpMethod.POST,"/usuario/v1/**").hasRole("DIRECTOR")
                .antMatchers(HttpMethod.GET, "/curso/v1/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST,"/curso/v1/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/proprietario/v1/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST,"/proprietario/v1/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/telefone/v1/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/telefone/v1/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
        ;
    }

}
