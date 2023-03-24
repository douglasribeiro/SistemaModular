package douglas.develop.telefone.controlle;

import douglas.develop.core.model.Telefone;
import douglas.develop.telefone.service.TelefoneService;
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
@Api(value = "Endpoints to manage prprietario")
public class TelefoneController {

    private final TelefoneService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Lista todos telefones ativos", response = Telefone[].class)
    public ResponseEntity<List<Telefone>> list() throws JSONException {
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Lista telefones por id", response = Telefone[].class)
    public ResponseEntity<Telefone> listid(@PathVariable Long id) throws JSONException {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Inclusão telefones", response = Telefone[].class)
    public ResponseEntity<Void> insert(@RequestBody Telefone telefone) throws JSONException {
        Telefone obj = service.save(telefone);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PatchMapping(value = "/{id}")
    @ApiOperation(value = "Alteração telefones", response = Telefone[].class)
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Telefone telefone) throws JSONException {
        service.update(id, telefone);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Exclusão telefones", response = Telefone[].class)
    public ResponseEntity<Void> delete(@PathVariable Long id) throws JSONException {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
