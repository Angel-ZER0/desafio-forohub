package angel_zero.desafios_aluraONE.ForoHub.Controladores;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import angel_zero.desafios_aluraONE.ForoHub.modelos.ActualizarTopico;
import angel_zero.desafios_aluraONE.ForoHub.modelos.CreacionTopico;
import angel_zero.desafios_aluraONE.ForoHub.modelos.EntidadTopicos;
import angel_zero.desafios_aluraONE.ForoHub.modelos.RetornoDatosTopico;
import angel_zero.desafios_aluraONE.ForoHub.modelos.TopicoPorId;
import angel_zero.desafios_aluraONE.ForoHub.repositorios.RepositorioTopicos;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tópicos")
public class ControladorTopicos {
	
	@Autowired
	private RepositorioTopicos repoTopicos;
	/*
	@PostMapping
	public void crearTopico (@RequestBody @Valid CreacionTopico topico) {
		repoTopicos.save(new EntidadTopicos(topico));
	}
	*/
	
	@PostMapping
	public ResponseEntity <RetornoDatosTopico> crearTopico (@RequestBody @Valid CreacionTopico topico, UriComponentsBuilder uriComponentsBuilder) {
		EntidadTopicos nuevoTopico = repoTopicos.save(new EntidadTopicos(topico));
		RetornoDatosTopico retornoTopico = new RetornoDatosTopico(nuevoTopico.getId(), nuevoTopico.getTitulo(), nuevoTopico.getMensaje(), nuevoTopico.getFecha(), nuevoTopico.getEstado().toString(), nuevoTopico.getAutor(), nuevoTopico.getCurso());
		URI url = uriComponentsBuilder.path("/tópico/{id}").buildAndExpand(nuevoTopico.getId()).toUri();
		return ResponseEntity.created(url).body(retornoTopico);
	}
	
	@GetMapping
	public ResponseEntity<Page<RetornoDatosTopico>> listarTopicos (Pageable paginacion) {
		return ResponseEntity.ok(repoTopicos.encontrarTopicos(paginacion).map(RetornoDatosTopico::new));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TopicoPorId> encontrarTopicoPorId (@PathVariable Long id) {
		EntidadTopicos topicoEncontrado = repoTopicos.getReferenceById(id);
		TopicoPorId topicoAMostrar = new TopicoPorId(topicoEncontrado.getTitulo(), topicoEncontrado.getMensaje(), topicoEncontrado.getFecha().toString().replaceAll("T", " "), topicoEncontrado.getEstado().toString(), topicoEncontrado.getAutor(), topicoEncontrado.getCurso());
		return ResponseEntity.ok(topicoAMostrar);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<RetornoDatosTopico> actualizaTopico (@RequestBody @Valid ActualizarTopico actualizarTopico) {
		EntidadTopicos topicoAActualizar = repoTopicos.getReferenceById(actualizarTopico.id());
		topicoAActualizar.actualizarTopico(actualizarTopico);
		return ResponseEntity.ok(new RetornoDatosTopico(topicoAActualizar.getId(), topicoAActualizar.getTitulo(), topicoAActualizar.getMensaje(), topicoAActualizar.getFecha(), topicoAActualizar.getEstado().toString(), topicoAActualizar.getAutor(), topicoAActualizar.getCurso()));
	}
	
	@DeleteMapping("/ocultar-post/{id}")
	@Transactional
	public ResponseEntity ocultarTopico (@PathVariable Long id) {
		EntidadTopicos topicoAEliminar = repoTopicos.getReferenceById(id);
		topicoAEliminar.ocultarTopico();
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity eliminarTopico (@PathVariable Long id) {
		repoTopicos.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
