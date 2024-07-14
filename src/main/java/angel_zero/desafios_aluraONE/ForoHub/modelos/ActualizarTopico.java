package angel_zero.desafios_aluraONE.ForoHub.modelos;

import jakarta.validation.constraints.NotNull;

public record ActualizarTopico(@NotNull Long id, String titulo, String mensaje, String fecha, EstadoPublicacion estado, String autor, String curso) {

}
