package douglas.develop.telefone.service;

import douglas.develop.core.model.Telefone;
import douglas.develop.core.repository.TelefoneRepository;
import douglas.develop.exception.ObjectNotFoundException;
import douglas.develop.service.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import java.util.List;

import static douglas.develop.utils.Utils.clientUser;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TelefoneService implements GenericService<Telefone> {
    private final TelefoneRepository repository;


    @Override
    public List<Telefone> list() throws JSONException {
        return repository.findByClient(clientUser());
    }

    @Override
    public Telefone findById(Long id) throws JSONException {
        log.info("listando proprietrio por id");
        Integer client = clientUser();
        Telefone obj = repository.findByIdAndClient(id,client)
                .orElseThrow(() -> new ObjectNotFoundException("Telefone não encontrado"));
        return obj;
    }

    @Override
    public Telefone save(Telefone telefone) throws JSONException {
        telefone.setId(null);
        telefone.setClient(clientUser());
        return repository.save(telefone);
    }

    @Override
    public void update(Long id, Telefone telefone) throws JSONException {
        findById(id);
        telefone.setClient(clientUser());
        repository.save(telefone);
    }

    @Override
    public void delete(Long id) {
        try {
            findById(id);
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
