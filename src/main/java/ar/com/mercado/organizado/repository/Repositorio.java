package ar.com.mercado.organizado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ar.com.mercado.organizado.entity.Persona;


@Repository ("repositorio")
@EnableJpaRepositories("ar.com.mercado.organizado.repository")
public interface Repositorio extends JpaRepository<Persona, Long>{

	public Persona findByIdPersona(Long id);
	
}
