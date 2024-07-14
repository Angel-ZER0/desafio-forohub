package angel_zero.desafios_aluraONE.ForoHub.modelos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreacionTopico(@NotBlank String titulo, @NotBlank String mensaje, @NotNull EstadoPublicacion estado, @NotBlank String autor, @NotBlank String curso) {

	
	
}