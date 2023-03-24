package douglas.develop.usuario.controller;

import douglas.develop.core.model.ApplicationUser;
import douglas.develop.core.model.Curso;
import douglas.develop.usuario.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoints to manage usuarios")
public class UsuarioController {

    Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    private final UsuarioService usuarioService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "List all available courses", response = Curso[].class)
    public ResponseEntity<List<ApplicationUser>> list() throws JSONException {

        logger.info("Serviço lista usuarios...");

        return ResponseEntity.ok(usuarioService.listAll());
    }

    @PostMapping
    public ResponseEntity<Void> salva(@RequestBody @Valid ApplicationUser applicationUser) throws JSONException {

        logger.info("Serviço novo usuario...");

        usuarioService.save(applicationUser);

        return ResponseEntity.noContent().build();
    }
}
