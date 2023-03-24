package douglas.develop.proprietario.controller;

import douglas.develop.core.dto.ProprietarioDTO;
import douglas.develop.core.dto.ProprietarioUpdateDTO;
import douglas.develop.core.model.Proprietario;
import douglas.develop.proprietario.service.ProprietarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("v1")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoints to manage prprietario")
public class ProprietarioController {

    private final ProprietarioService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Lista todos proprietarios ativos", response = Proprietario[].class)
    public ResponseEntity<Iterable<ProprietarioDTO>> list(Pageable pageable) throws JSONException {
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @GetMapping(value="/id/{id}")
    @ApiOperation(value = "Lista proprietario pelo id", response = Proprietario[].class)
    public ResponseEntity<ProprietarioDTO> findById(@PathVariable("id") Long id) throws Exception{
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping(value="/cpfcnpj/{cpfcnpj}")
    @ApiOperation(value = "Lista proprietario pelo cpf ou cnpj", response = Proprietario[].class)
    public ResponseEntity<ProprietarioDTO> findByCpfCnpj(@PathVariable("cpfcnpj") String cpfcnpj) throws Exception{
        return ResponseEntity.ok().body(service.findByCpfCnpj(cpfcnpj));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid Proprietario proprietario) throws JSONException {
        Proprietario obj = service.save(proprietario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody ProprietarioUpdateDTO proprietarioUpdateDTO){
        log.info("Alteração do registro de prorietario. "+ id);
        try {
            service.update(id,proprietarioUpdateDTO);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            return ResponseEntity.ok(false);
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        log.info("Exclusão do registro de prorietario. "+ id);
        try {
            service.delete(id);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            return ResponseEntity.ok(false);
        }


    }


}
