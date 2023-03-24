package douglas.develop.usuario.service;

import douglas.develop.core.model.ApplicationUser;
import douglas.develop.core.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioService {

    private final ApplicationUserRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<ApplicationUser> listAll(){
        log.info("Listando todos Usuarios cadastrados");
        return repository.findAll();
    }

    public ApplicationUser save(ApplicationUser applicationUser){
        log.info("Salvando registro de usuario");
        applicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
        return repository.save(applicationUser);
    }

}
