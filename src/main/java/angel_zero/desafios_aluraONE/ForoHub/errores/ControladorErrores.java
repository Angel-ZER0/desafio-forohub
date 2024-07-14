package angel_zero.desafios_aluraONE.ForoHub.errores;

import java.time.format.DateTimeParseException;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControladorErrores {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity errores404 () {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity errores400 (MethodArgumentNotValidException e) {
		var errors = e.getFieldErrors().stream().map(ErroresValidacion::new).toList();
		return ResponseEntity.badRequest().body(errors);
	}
	
	private record ErroresValidacion (String campo, String error) {
		public ErroresValidacion(FieldError error) {
			this(error.getField(), error.getDefaultMessage());
		}
	}

	@ExceptionHandler(DateTimeParseException.class)
	private ResponseEntity erroresFecha (DateTimeParseException e) {
		ErroresValidacion errorFecha = new ErroresValidacion("Formato de fecha incorrecto", "La fecha debe tener el formato 'aaaa-MM-dd hh:mm:ss'");
		return ResponseEntity.badRequest().body(errorFecha);
	}
	
}
