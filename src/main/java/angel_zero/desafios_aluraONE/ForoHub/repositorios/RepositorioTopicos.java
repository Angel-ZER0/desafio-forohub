package angel_zero.desafios_aluraONE.ForoHub.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import angel_zero.desafios_aluraONE.ForoHub.modelos.EntidadTopicos;

@Repository
public interface RepositorioTopicos extends JpaRepository <EntidadTopicos, Long>{

	@Query("select t from EntidadTopicos t where estado != 2")
	Page <EntidadTopicos> encontrarTopicos(Pageable paginacion);
	
}
