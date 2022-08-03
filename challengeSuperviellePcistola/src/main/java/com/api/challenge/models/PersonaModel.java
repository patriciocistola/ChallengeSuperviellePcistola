package com.api.challenge.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.api.challenge.utils.PaisEnum;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "persona", schema = "public")
public class PersonaModel {

	@Id
	@GeneratedValue
	@ApiParam(hidden = true)
	private Long id;
	@NotNull
	private TipoDocEnum TipoDoc;
	@NotNull
	private Long NroDoc;
	@NotNull
	private PaisEnum Pais;
	@NotNull
	private Genero Sexo;
	@NotNull
	@Min(value = 18, message = "La edad no puede ser menor de 18")
	private Integer Edad;
	@JoinColumn(name = "fk_dato_contacto", nullable = false)
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private DatosModel datosContacto;

	public enum Genero {
		Masculino, Femenino, Otro
	}

	public enum TipoDocEnum {
		DNI, CI, LE, LC
	}

	public void setDatosContacto(String mail, Long telefono) {
		if (mail != null) {
			this.datosContacto.setMail(mail);
		}
		if (telefono != null) {
			this.datosContacto.setTelefono(telefono);
		}
	}

}
