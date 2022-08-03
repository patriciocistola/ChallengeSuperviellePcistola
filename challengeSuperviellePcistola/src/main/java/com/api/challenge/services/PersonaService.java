package com.api.challenge.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.challenge.models.PersonaModel;
import com.api.challenge.models.PersonaModel.Genero;
import com.api.challenge.models.PersonaModel.TipoDocEnum;
import com.api.challenge.repositories.PersonaRepository;
import com.api.challenge.utils.PaisEnum;

@Service
@Transactional
public class PersonaService {
    @Autowired
    PersonaRepository personaRepository;

    public ArrayList<PersonaModel> obtenerPersonas() {
        return (ArrayList<PersonaModel>) personaRepository.findAll();
    }

    public PersonaModel guardarPersona(PersonaModel persona) {
        return personaRepository.save(persona);
    }

    public PersonaModel obtenerPorId(Long id) {
        return personaRepository.findById(id);
    }

    public Map<String, Object> obtenerEstadist() {
        Map<String, Object> estadisticas = new HashMap<String, Object>();
        estadisticas.put("cantidad_mujeres", personaRepository.countByGenero(Genero.Femenino));
        estadisticas.put("cantidad_hombres", personaRepository.countByGenero(Genero.Masculino));
        estadisticas.put("porcentaje_argentinos", personaRepository.porcByPais(PaisEnum.ARGENTINA));
        return estadisticas;
    }

    public void borrarPersona(Long id) {
        personaRepository.deleteById(id);
    }

    public boolean validarexistenciaPersona(@Valid PersonaModel persona) {
        TipoDocEnum tipoDoc = persona.getTipoDoc();
        Long nroDoc = persona.getNroDoc();
        PaisEnum pais = persona.getPais();
        Genero sexo = persona.getSexo();
        if (personaRepository.validarexistencia(tipoDoc, nroDoc, pais, sexo)) {
            return true;
        } else {
            return false;
        }
    }

    public PersonaModel actualizarPersona(PersonaModel persona, Long id) {
        PersonaModel p = personaRepository.findById(id);
        p.setPais(persona.getPais());
        p.setNroDoc(persona.getNroDoc());
        p.setSexo(persona.getSexo());
        p.setTipoDoc(persona.getTipoDoc());
        p.setEdad(persona.getEdad());
        if (persona.getDatosContacto() != null) {
            p.setDatosContacto(persona.getDatosContacto().getMail(), persona.getDatosContacto().getTelefono());
        }
        return personaRepository.save(p);
    }

}
