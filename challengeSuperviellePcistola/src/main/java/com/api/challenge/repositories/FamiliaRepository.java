package com.api.challenge.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.challenge.models.FamiliaModel;
import com.api.challenge.models.FamiliaModel.FamiliaEnum;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@Repository
public interface FamiliaRepository extends CrudRepository<FamiliaModel, Id> {

    @Query(nativeQuery = true, value = "select familiar from public.persona_familia f where f.fk_id1=:id1 and f.fk_id2=:id2")
    Optional<FamiliaEnum> findFamiliar(Long id1, Long id2);

}