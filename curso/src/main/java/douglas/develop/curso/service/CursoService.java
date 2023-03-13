package douglas.develop.curso.service;

import douglas.develop.core.model.Curso;
import douglas.develop.core.repository.CursoRepository;
import douglas.develop.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CursoService {
    private final CursoRepository cursoRepository;

    public Iterable<Curso> list(Pageable pageable){
        log.info("Listando todos cursos");
        Curso curso = new Curso();
        String te = curso.getTitulo();
        return cursoRepository.findAll(pageable);
    }

    public Curso findById(Long id) {
        Optional<Curso> curso =
                Optional.ofNullable(cursoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Curso não encontrado")));
        return curso.get();
    }

}
