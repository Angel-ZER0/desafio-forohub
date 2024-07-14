package angel_zero.desafios_aluraONE.ForoHub.modelos;

import java.time.LocalDateTime;

public record RetornoDatosTopico(Long id, String titulo, String mensaje, LocalDateTime fecha, String estado, String autor, String curso) {
	
	public RetornoDatosTopico(EntidadTopicos topico) {
		this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha(), topico.getAutor(), topico.getCurso(), topico.getEstado().toString());
	}
	
}
