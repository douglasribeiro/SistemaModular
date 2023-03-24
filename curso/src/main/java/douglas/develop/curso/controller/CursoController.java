package douglas.develop.curso.controller;

import douglas.develop.core.dto.CursoDTO;
import douglas.develop.core.model.Curso;
import douglas.develop.curso.service.CursoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoints to manage curso")
public class CursoController {

    Logger logger = LoggerFactory.getLogger(CursoController.class);
    private final CursoService cursoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "List all available courses", response = Curso[].class)
    public ResponseEntity<List<CursoDTO>> list() throws JSONException {
        return new ResponseEntity<>(cursoService.list(), HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    @ApiOperation(value = "List cursoes", response = Curso.class)
    public ResponseEntity<CursoDTO> findById(@PathVariable("id") Long id) throws Exception{
        return ResponseEntity.ok().body(cursoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> salva(@RequestBody Curso curso) throws JSONException {
        logger.info("inicio Post");
        Curso obj = cursoService.save(curso);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
