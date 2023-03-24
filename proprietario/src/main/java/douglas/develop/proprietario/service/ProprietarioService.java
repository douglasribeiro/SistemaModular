package douglas.develop.proprietario.service;

import douglas.develop.core.dto.ProprietarioDTO;
import douglas.develop.core.dto.ProprietarioUpdateDTO;
import douglas.develop.core.model.Proprietario;
import douglas.develop.core.repository.ProprietarioRepository;
import douglas.develop.exception.ConstraintViolationException;
import douglas.develop.exception.DataIntegrityViolationException;
import douglas.develop.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static douglas.develop.utils.Utils.clientUser;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProprietarioService {
    private final ProprietarioRepository repository;

    public List<ProprietarioDTO> list() throws JSONException {
        log.info("Listando todos proprietarios");
        return  repository.findByAtivoAndClient(true, clientUser())
                .stream().map(x -> new ProprietarioDTO(x)).collect(Collectors.toList());
    }

    public ProprietarioDTO findById(Long id) throws Exception {
        log.info("listando proprietrio por id");
        return new ProprietarioDTO(repository.buscaPorId(id)
                .orElseThrow(() -> new ObjectNotFoundException("Proprietario não encontrado")));
    }

    public ProprietarioDTO findByCpfCnpj(String cpfcnpj) throws Exception {
        log.info("listando proprietrio por Cpf ou Cnpj");
        return new ProprietarioDTO(repository.findByCpfcnpj(cpfcnpj)
                .orElseThrow(() -> new ObjectNotFoundException("Proprietario não encontrado")));
    }

    public Proprietario save(Proprietario proprietario)  {
        proprietario.setId(null);
        try {
            proprietario.setClient(clientUser());
            return repository.save(proprietario);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (javax.validation.ConstraintViolationException e){
            throw new ConstraintViolationException("Existem informações em duplicidade.");
        } catch (org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Integridade do banco violada.");
        } catch (Exception e){
            log.info("Outra exception.");
        }
        return null;
    }

    public void update(Long id, ProprietarioUpdateDTO proprietarioUpdateDTO){
        try {
            Proprietario obj = new Proprietario(findById(id));
            obj.setNome(proprietarioUpdateDTO.getNome());
            obj.setDtNiver(proprietarioUpdateDTO.getDtNiver());
            obj.setEstCivil(proprietarioUpdateDTO.getEstCivil());
            obj.setSexo(proprietarioUpdateDTO.getSexo());
            obj.setNacional(proprietarioUpdateDTO.getNacional());
            obj.setNaturalidade(proprietarioUpdateDTO.getNaturalidade());
            repository.altera(obj.getNome(), obj.getDtNiver(), obj.getEstCivil(),
                    obj.getSexo(), obj.getNacional(), obj.getNaturalidade(), obj.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id){
        try {
            Proprietario obj = new Proprietario(findById(id));
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
