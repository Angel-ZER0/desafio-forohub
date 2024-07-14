package angel_zero.desafios_aluraONE.ForoHub.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import angel_zero.desafios_aluraONE.ForoHub.generadorTokens.CreadorDeToken;
import angel_zero.desafios_aluraONE.ForoHub.generadorTokens.TokenGenerado;
import angel_zero.desafios_aluraONE.ForoHub.usuarios.AccesoUsuarios;
import angel_zero.desafios_aluraONE.ForoHub.usuarios.DatosRegistrarUsuario;
import angel_zero.desafios_aluraONE.ForoHub.usuarios.EntidadUsuarios;
import angel_zero.desafios_aluraONE.ForoHub.usuarios.RegistoUsuario;
import angel_zero.desafios_aluraONE.ForoHub.usuarios.RepositorioUsuarios;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class ControladorAutenticador {

	@Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
    private CreadorDeToken tokenService;
	@Autowired
    private RepositorioUsuarios repoUsuarios;

    @PostMapping
    public ResponseEntity <TokenGenerado> autenticarUsuario(@RequestBody @Valid AccesoUsuarios accesoUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(accesoUsuario.usuario(),
                accesoUsuario.contrasena());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.tokenGenerado((EntidadUsuarios) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new TokenGenerado(JWTtoken));
    }
    
    @PostMapping("/registrar")
    public ResponseEntity registrarUsuario(@RequestBody @Valid RegistoUsuario nuevoUsuario) {
    	DatosRegistrarUsuario registrarUsuario = new DatosRegistrarUsuario(nuevoUsuario);
    	EntidadUsuarios nuevo = new EntidadUsuarios(registrarUsuario);
    	repoUsuarios.save(nuevo);
    	return ResponseEntity.ok("El usuario fue registrado exitósamente");
    }
	
}
