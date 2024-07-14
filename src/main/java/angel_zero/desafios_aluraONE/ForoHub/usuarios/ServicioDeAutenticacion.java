package angel_zero.desafios_aluraONE.ForoHub.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicioDeAutenticacion implements UserDetailsService{

	@Autowired
	private RepositorioUsuarios repoUsuarios;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return repoUsuarios.findByUsuario(username);
		
	}
	
	
	
}
