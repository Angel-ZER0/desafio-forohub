package angel_zero.desafios_aluraONE.ForoHub.usuarios;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Getter;

@Getter
public class DatosRegistrarUsuario {
	
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private String correo;
	private String usuario;
	private String contrasena;

	public DatosRegistrarUsuario (RegistoUsuario nuevoUsuario) {
		this.correo = nuevoUsuario.correo();
		this.usuario = nuevoUsuario.usuario();
		this.contrasena = passwordEncoder.encode(nuevoUsuario.contrasena());
	}

}
