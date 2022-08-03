package com.api.challenge.repositories;

import com.api.challenge.models.PersonaModel;
import com.api.challenge.models.PersonaModel.Genero;
import com.api.challenge.models.PersonaModel.TipoDocEnum;
import com.api.challenge.utils.PaisEnum;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends CrudRepository<PersonaModel, Id> {

    PersonaModel findById(Long id);

    @Query("select count(*) from PersonaModel p where p.Sexo=:sexo")
    String countByGenero(Genero sexo);

    @Query(nativeQuery = true, value = "select((select count(*) from public.persona where pais=7)*100)/(select count(pais) from public.persona)")
    String porcByPais(PaisEnum pais);

    void deleteById(Long id);

    @Query("select case when count(p)> 0 then true else false end from PersonaModel p where p.Sexo=:sexo and p.NroDoc=:nroDoc and p.TipoDoc=:tipoDoc and p.Pais=:pais")
    boolean validarexistencia(TipoDocEnum tipoDoc, Long nroDoc, PaisEnum pais, Genero sexo);

}
