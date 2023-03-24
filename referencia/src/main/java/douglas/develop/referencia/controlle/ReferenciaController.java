package douglas.develop.referencia.controlle;

import douglas.develop.core.model.Referencia;
import douglas.develop.referencia.service.ReferenciaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoints to manage referencia")
public class ReferenciaController {

    private final ReferenciaService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Lista todas referencias ativas", response = Referencia[].class)
    public ResponseEntity<List<Referencia>> list() throws JSONException {
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Lista referencia por id", response = Referencia[].class)
    public ResponseEntity<Referencia> listid(@PathVariable Long id) throws JSONException {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Inclusão telefones", response = Referencia[].class)
    public ResponseEntity<Void> insert(@RequestBody Referencia referencia) throws JSONException {
        Referencia obj = service.save(referencia);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PatchMapping(value = "/{id}")
    @ApiOperation(value = "Alteração referencia", response = Referencia[].class)
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Referencia referencia) throws JSONException {
        service.update(id, referencia);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Exclusão referencia", response = Referencia[].class)
    public ResponseEntity<Void> delete(@PathVariable Long id) throws JSONException {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
