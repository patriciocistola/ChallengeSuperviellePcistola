package com.api.challenge.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "personaFamilia", schema = "public")
public class FamiliaModel {

    @Id
    @GeneratedValue
    @ApiParam(hidden = true)
    @JsonIgnore
    private Long id;
    @JoinColumn(name = "fk_id1", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PersonaModel id1;
    @JoinColumn(name = "fk_id2", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PersonaModel id2;
    private FamiliaEnum Familiar;

    public enum FamiliaEnum {
        Padre, Madre, Hermana, Hermano, Tio, Tia, Primo, Prima
    }

}
