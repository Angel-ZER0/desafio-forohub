package angel_zero.desafios_aluraONE.ForoHub.modelos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "topicos")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class EntidadTopicos {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String titulo;
	@Column(unique = true)
	private String mensaje;
	private LocalDateTime fecha;
	private EstadoPublicacion estado;
	private String autor;
	private String curso;
	
	public EntidadTopicos(CreacionTopico topico) {
		this.titulo = topico.titulo();
		this.mensaje = topico.mensaje();
		this.fecha = LocalDateTime.now();
		this.estado = topico.estado();
		this.autor = topico.autor();
		this.curso = topico.curso();
	}
	
	public void actualizarTopico(ActualizarTopico actualizarTopico) {
		if (actualizarTopico.titulo() != null) {
			this.titulo = actualizarTopico.titulo();
		}
		if (actualizarTopico.mensaje() != null) {
			this.mensaje = actualizarTopico.mensaje();
		}
		if (actualizarTopico.fecha() != null) {
			this.fecha = cadenaAFecha(actualizarTopico.fecha());
		}
		if (actualizarTopico.estado() != null) {
			this.estado = actualizarTopico.estado();
		}
		if (actualizarTopico.autor() != null) {
			this.autor = actualizarTopico.autor();
		}
		if (actualizarTopico.curso() != null) {
			this.curso = actualizarTopico.curso();
		}
	}
	
	public LocalDateTime cadenaAFecha(String cadena) {
		String cadenaFecha = cadena.replaceAll("-", "/");
		DateTimeFormatter formato = new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")).toFormatter();
		LocalDateTime fecha = LocalDateTime.parse(cadenaFecha, formato);
		try {
			fecha = LocalDateTime.parse(cadenaFecha, formato);
		} catch (DateTimeParseException e) {
			System.out.println("Mensaje de error:");
			e.printStackTrace();
		}
		return fecha;
	}
	
	public void ocultarTopico() {
		this.estado = EstadoPublicacion.OCULTO;
	}
	
}
