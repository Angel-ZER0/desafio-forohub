package angel_zero.desafios_aluraONE.ForoHub.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistoUsuario(@Email @NotBlank String correo, @NotBlank String usuario, @NotBlank String contrasena) {

}
