package angel_zero.desafios_aluraONE.ForoHub.generadorTokens;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import angel_zero.desafios_aluraONE.ForoHub.usuarios.EntidadUsuarios;

@Service
public class CreadorDeToken {

	@Value("${api.security.secret}")
	private String secretoAPI;
	@Value("${api.issuer}")
	private String issuer;
	private LocalDateTime tiempo = LocalDateTime.now().plusDays(1);
	Date fechaExpiracion = Date.from(tiempo.atZone(ZoneId.systemDefault()).toInstant());
	
	public String tokenGenerado(EntidadUsuarios usuario) {
		
		try {
			Algorithm algorithm = Algorithm.HMAC256(secretoAPI);
			return JWT.create().withIssuer(issuer).withSubject(usuario.getUsuario()).withClaim("id", usuario.getId()).withExpiresAt(fechaExpiracion).sign(algorithm);
		} catch (JWTCreationException exception) {
			throw new RuntimeException();
		}
		
	}
	
	public String getNombreUsuario(String token) {
		
		if (token == null) {
			
			throw new RuntimeException();
			
		}
		
		DecodedJWT verificador = null;
		
		try {
		
			Algorithm algorithm = Algorithm.HMAC256(secretoAPI);
			verificador = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
			verificador.getSubject();
			
		} catch (JWTVerificationException exception) {
			
			System.out.println(exception.toString());
			
		}
		
		if (verificador.getSubject() == null) {
			throw new RuntimeException(); 
		}
		
		return verificador.getSubject();
		
	}
	
}
