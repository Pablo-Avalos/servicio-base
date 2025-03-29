package ar.com.mercado.organizado.repository;

/*

import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ar.com.bancogalicia.paas.servicios.gdms.entity.Documento;




@Repository ("repositorio")
@EnableJpaRepositories("ar.com.bancogalicia.paas.gestion.documental.documento.gd.repository")
public interface Repositorio extends JpaRepository<Documento,Long>{

	@Query("SELECT d.filenetId FROM Documento d where d.id= :id")
	String obtenerIdFilenet(Long id);
	
	@Query(value="{call SP_ID_Documento(:solicitud,:tipoDoc,:fechaDoc)}",nativeQuery=true)
	public Documento guardarDocumento(@Param("solicitud") Long solicitud, @Param("tipoDoc") String tipoDoc, @Param("fechaDoc") Date fechaDoc);

	@Query(value="{call SP_ID_Documento_UPD_Filenet(:id,:filenetId)}",nativeQuery=true)
	Documento guardarIdFilenet(@Param("id")Long id, @Param("filenetId") String filenetId);
	
	@Query(value="{call SP_ID_Documento_UPD_PUA(:id,:puaId)}",nativeQuery=true)
	Documento guardarIdPua(@Param("id")Long id, @Param("puaId") Long puaId);

}


*/
