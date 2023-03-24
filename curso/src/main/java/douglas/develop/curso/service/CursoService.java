package douglas.develop.curso.service;

import douglas.develop.core.dto.CursoDTO;
import douglas.develop.core.model.Curso;
import douglas.develop.core.repository.CursoRepository;
import douglas.develop.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static douglas.develop.utils.Utils.clientUser;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CursoService {
    private final CursoRepository cursoRepository;

    public List<CursoDTO> list() throws JSONException {
        log.info("Listando todos cursos");
        Integer client = clientUser();
        return cursoRepository.findByClient(client).stream().map(x -> new CursoDTO(x)).collect(Collectors.toList());
    }

    public CursoDTO findById(Long id) throws JSONException {
        Optional<Curso> curso =
                Optional.ofNullable(cursoRepository.findByIdAndClient(id, clientUser())
                        .orElseThrow(() -> new ObjectNotFoundException("Curso não encontrado")));
        return new CursoDTO(curso.get());
    }

    public Curso save(Curso curso) throws JSONException {
        curso.setId(null);
        curso.setClient(clientUser());
        return cursoRepository.save(curso);
    }

}
