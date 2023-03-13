package douglas.develop.auth.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jwt.SignedJWT;
import douglas.develop.core.model.ApplicationUser;
import douglas.develop.core.property.JwtConfiguration;
import douglas.develop.security.token.creator.TokenCreator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static java.util.Collections.emptyList;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtConfiguration jwtConfiguration;

    private final TokenCreator tokenCreator;

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        log.info("tentando autenticação....");
        ApplicationUser applicationUser = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);

        if(applicationUser == null)
            throw  new UsernameNotFoundException("erro na autenticação");

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(applicationUser.getUsername(), applicationUser.getPassword(),emptyList());
        usernamePasswordAuthenticationToken.setDetails(applicationUser);

        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    }

    @Override
    @SneakyThrows
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        log.info("Usuario autenticado com sucesso!");

        SignedJWT signedJWT = tokenCreator.createSignedJWT(authResult);

        String encrypyToken = tokenCreator.encryptToken(signedJWT);

        response.addHeader("Access-Control-Expose-Headers", "XSRF-TOKEN, "+jwtConfiguration.getHeader().getName());

        response.addHeader(jwtConfiguration.getHeader().getName(), jwtConfiguration.getHeader().getPrefix() + encrypyToken);
    }

//    @SneakyThrows
//    private SignedJWT createSignedJWT(Authentication auth){
//        log.info("Criando JWT");
//        ApplicationUser applicationUser = (ApplicationUser) auth.getPrincipal();
//        JWTClaimsSet jwtClaimsSet = createJWTClaimSet(auth, applicationUser);
//        KeyPair rsaKeys = generateKeyPair();
//        log.info("Criando JWK apartir de RSA Keys");
//        JWK jwk = new RSAKey.Builder((RSAPublicKey) rsaKeys.getPublic()).keyID(UUID.randomUUID().toString()).build();
//        SignedJWT signedJWT =  new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256)
//                .jwk(jwk)
//                .type(JOSEObjectType.JWT)
//                .build(), jwtClaimsSet);
//        log.info("token assinado.");
//        RSASSASigner signer =  new RSASSASigner(rsaKeys.getPrivate());
//        signedJWT.sign(signer);
//
//        log.info("Serialized token '{}", signedJWT.serialize());
//        return  signedJWT;
//    }

//    private JWTClaimsSet createJWTClaimSet(Authentication auth, ApplicationUser applicationUser){
//        log.info("Criação JwtClainSet");
//        return  new JWTClaimsSet.Builder()
//                .subject(applicationUser.getUsername())
//                .claim("authorities", auth.getAuthorities()
//                        .stream()
//                        .map(GrantedAuthority::getAuthority)
//                        .collect(toList()))
//                .issuer("http://douglas.develop")
//                .issueTime(new Date())
//                .expirationTime(new Date(System.currentTimeMillis() + (jwtConfiguration.getExpiration() * 1000)))
//                .build();
//    }

//    @SneakyThrows
//    private KeyPair generateKeyPair(){
//        log.info("Gerando RSA 2048 bits Keys");
//        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
//        generator.initialize(2048);
//        return generator.generateKeyPair();
//    }

//    private String encryptToken(SignedJWT signedJWT) throws JOSEException {
//        log.info("Token criptografado");
//
//        DirectEncrypter directEncrypter = new DirectEncrypter(jwtConfiguration.getPrivateKey().getBytes());
//
//        JWEObject jweObject = new JWEObject(new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256)
//                .contentType("JWT")
//                .build(), new Payload(signedJWT));
//        jweObject.encrypt(directEncrypter);
//        log.info("token criptografado");
//
//        return  jweObject.serialize();
//    }
}
