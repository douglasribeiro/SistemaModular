package douglas.develop.proprietario.service;

import douglas.develop.core.model.Proprietario;
import douglas.develop.core.repository.ProprietarioRepository;
import douglas.develop.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProprietarioService {
    private final ProprietarioRepository repository;

    public Iterable<Proprietario> list(Pageable pageable){
        log.info("Listando todos cursos");
        return repository.buscaSomenteAtivos(true);
    }

    public Proprietario findById(Long id) throws Exception {
        log.info("listando proprietrio por id");
        return repository.buscaPorId(id).orElseThrow(() -> new ObjectNotFoundException("Proprietario não encontrado"));
    }

    public Proprietario findByCpfCnpj(String cpfcnpj) throws Exception {
        log.info("listando proprietrio por Cpf ou Cnpj");
        return repository.findByCpfcnpj(cpfcnpj).orElseThrow(() -> new ObjectNotFoundException("Proprietario não encontrado"));
    }

    public Proprietario save(Proprietario proprietario){
        proprietario.setId(null);
        return repository.save(proprietario);
    }
}
