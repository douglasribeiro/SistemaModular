package douglas.develop.referencia.service;

import douglas.develop.core.model.Referencia;
import douglas.develop.core.repository.ReferenciaRepository;
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
public class ReferenciaService implements GenericService<Referencia> {
    private final ReferenciaRepository repository;

    @Override
    public List<Referencia> list() throws JSONException {
        return repository.findByClient(clientUser());
    }

    @Override
    public Referencia findById(Long id) throws JSONException {
        log.info("listando referencias por id");
        Integer client = clientUser();
        Referencia obj = repository.findByIdAndClient(id,client)
                .orElseThrow(() -> new ObjectNotFoundException("Telefone não encontrado"));
        return obj;
    }

    @Override
    public Referencia save(Referencia referencia) throws JSONException {
        referencia.setId(null);
        referencia.setClient(clientUser());
        return repository.save(referencia);
    }

    @Override
    public void update(Long id, Referencia referencia) throws JSONException {
        findById(id);
        referencia.setClient(clientUser());
        repository.save(referencia);
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
