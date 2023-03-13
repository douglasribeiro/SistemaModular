package douglas.develop.curso.controller;

import douglas.develop.core.model.Curso;
import douglas.develop.curso.service.CursoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("v1")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoints to manage curso")
public class CursoController {

    private final CursoService cursoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "List all available courses", response = Curso[].class)
    public ResponseEntity<Iterable<Curso>> list(Pageable pageable){
        return new ResponseEntity<>(cursoService.list(pageable), HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    @ApiOperation(value = "List cursoes", response = Curso.class)
    public ResponseEntity<Curso> findById(@PathVariable("id") Long id) throws Exception{
        return ResponseEntity.ok().body(cursoService.findById(id));
    }

}
