package com.api.challenge.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.challenge.models.FamiliaModel;
import com.api.challenge.models.PersonaModel;
import com.api.challenge.models.FamiliaModel.FamiliaEnum;
import com.api.challenge.repositories.FamiliaRepository;
import com.api.challenge.repositories.PersonaRepository;

@Service
public class FamiliaService {
    @Autowired
    FamiliaRepository familiaRepository;
    @Autowired
    PersonaRepository personaRepository;

    public FamiliaModel guardarRelacion(Long id1, FamiliaEnum familiar, Long id2) {
        FamiliaModel f = new FamiliaModel();
        PersonaModel p1 = personaRepository.findById(id1);
        PersonaModel p2 = personaRepository.findById(id2);
        f.setId1(p1);
        f.setFamiliar(familiar);
        f.setId2(p2);
        return familiaRepository.save(f);
    }

    public Optional<FamiliaEnum> obtenerFPorId(Long id1, Long id2) {
        return familiaRepository.findFamiliar(id1, id2);
    }

}
