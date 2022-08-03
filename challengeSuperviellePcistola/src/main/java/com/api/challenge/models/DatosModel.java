package com.api.challenge.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "persona_contacto", schema = "public")
public class DatosModel {
    @Id
    @GeneratedValue
    @JsonIgnore
    @ApiParam(hidden = true)
    private Long idContacto;
    @Email
    private String Mail;
    private Long Telefono;

}
