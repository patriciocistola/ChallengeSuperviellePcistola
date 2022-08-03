package com.api.challenge.controllers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.challenge.models.FamiliaModel;
import com.api.challenge.models.PersonaModel;
import com.api.challenge.models.FamiliaModel.FamiliaEnum;
import com.api.challenge.services.FamiliaService;
import com.api.challenge.services.PersonaService;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaService personaService;

    @Autowired
    FamiliaService familiaService;

    @ApiOperation("Retorna todas las personas")
    @GetMapping()
    public ArrayList<PersonaModel> obtenerPersonas() {
        return personaService.obtenerPersonas();
    }

    @ApiOperation("Retorna una persona por su Id")
    @GetMapping("/{id}")
    public PersonaModel obtenerPersonaPorId(@ApiParam(value = "Ingrese un id de persona") @PathVariable("id") Long id) {
        return this.personaService.obtenerPorId(id);
    }

    @ApiOperation("Crea un objeto de tipo persona")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Verifique los datos ingresados: Edad: no puede ser menor a 18, mail: mail@mail.com"),
            @ApiResponse(responseCode = "500", description = "Debe agregar al menos un dato de contacto") })
    @PostMapping()
    public ResponseEntity<?> postPersona(@Valid @RequestBody PersonaModel persona) {
        if (personaService.validarexistenciaPersona(persona)) {
            return ResponseEntity.badRequest().body("Lapersona que desea insertar ya existe en nuestra base de datos");
        } else {
            this.personaService.guardarPersona(persona);
            return ResponseEntity.ok().body("Se inserto correctamente la persona con id : " + persona.getId());
        }
    }

    @ApiOperation("Actualiza un objeto de tipo persona")
    @PutMapping("/{id}")
    PersonaModel putPersona(@RequestBody PersonaModel persona, @PathVariable Long id) {
        return personaService.actualizarPersona(persona, id);
    }

    @ApiOperation("Elimina un objeto de tipo persona")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delActualizacion(@PathVariable(value = "id") Long id) {
        this.personaService.borrarPersona(id);
        return ResponseEntity.ok().body("Se elimino correctamente la persona con id : " + id);
    }

    @ApiOperation("Retorna las cifras totalizadoras de cantidad de mujeres, cantidad de hombres, porcentaje de argentinos sobre el total")
    @GetMapping("/estadisticas")
    public Map<String, Object> obtenerEstadisticas() {
        return this.personaService.obtenerEstadist();
    }

    @ApiOperation("Crea una relacion familiar entre dos personas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Debe asignar un id de persona existente") })
    @PostMapping("/relaciones/{id1}/{familiar}/{id2}")
    public FamiliaModel postRelacion(@PathVariable("id1") Long id1,
            @PathVariable("familiar") FamiliaEnum familiar,
            @PathVariable("id2") Long id2) {
        return this.familiaService.guardarRelacion(id1, familiar, id2);
    }

    @ApiOperation("Retorna una relacion entre dos personas")
    @GetMapping("/{id1}/{id2}")
    public Optional<FamiliaEnum> obtenerFamiliaPorId(@PathVariable("id1") Long id1,
            @PathVariable("id2") Long id2) {
        return this.familiaService.obtenerFPorId(id1, id2);
    }

}
