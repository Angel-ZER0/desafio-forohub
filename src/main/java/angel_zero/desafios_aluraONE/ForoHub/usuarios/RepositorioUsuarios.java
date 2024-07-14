package angel_zero.desafios_aluraONE.ForoHub.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface RepositorioUsuarios extends JpaRepository <EntidadUsuarios, Long>{

	UserDetails findByUsuario(String username);
	
	@Query("select u from EntidadUsuarios u where u.usuario = :usuario")
	EntidadUsuarios encontrarUsuario(String usuario);
}
