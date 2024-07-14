package angel_zero.desafios_aluraONE.ForoHub.seguridad;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import angel_zero.desafios_aluraONE.ForoHub.generadorTokens.CreadorDeToken;
import angel_zero.desafios_aluraONE.ForoHub.usuarios.RepositorioUsuarios;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FiltroDeSeguridad extends OncePerRequestFilter {
	
	@Autowired
	private CreadorDeToken tokenService;
	@Autowired
	private RepositorioUsuarios repoUsuarios;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if (authHeader != null) {
			
			String token = authHeader.replace("Bearer ", "");
			var nombreUsuario = tokenService.getNombreUsuario(token);
			
			if (nombreUsuario != null) {
				
				var usuario = repoUsuarios.findByUsuario(nombreUsuario);
				var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			}
			
		}
		
		filterChain.doFilter(request, response);
		
	}

}
