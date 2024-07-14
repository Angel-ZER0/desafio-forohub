package angel_zero.desafios_aluraONE.ForoHub.modelos;

public enum EstadoPublicacion {
	
	ABIERTO("Abierto"),
	CERRADO("Cerrado"),
	OCULTO("Oculto");
	
private String estado;
	
	EstadoPublicacion (String estado) {
		this.estado = estado;
	}
	
	public static EstadoPublicacion fromString(String text) {
        for (EstadoPublicacion estado : EstadoPublicacion.values()) {
            if (estado.estado.equalsIgnoreCase(text)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("No se encontró estado : " + text);
	}
	
}
