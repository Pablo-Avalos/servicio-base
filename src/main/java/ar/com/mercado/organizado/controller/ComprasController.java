package ar.com.mercado.organizado.controller;

import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ar.com.mercado.organizado.api.model.MetaData;
import ar.com.mercado.organizado.api.model.ResponseApiCompras;
import ar.com.mercado.organizado.api.model.ResponseCompra;
import ar.com.mercado.organizado.entity.Persona;
import ar.com.mercado.organizado.model.Parametros;
import ar.com.mercado.organizado.service.Servicio;

@RestController
@RequestMapping("/v1/mercado/organizado")
public class ComprasController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprasController.class);
	
	@Autowired
	private Servicio servicio;
	
	@CrossOrigin(origins = "*", methods = { RequestMethod.POST })
	@PostMapping(value = "/guardar")
    public ResponseEntity<ResponseApiCompras> create( @RequestBody Parametros body, HttpServletRequest httpServletRequest) {
	
		LOGGER.info("Inicio de llamada a servicio guardar compra.");
		
		HttpStatus httpStatus = HttpStatus.OK;
		ResponseApiCompras responseApi = new ResponseApiCompras();
		ResponseCompra r = new ResponseCompra();
		
		try {
			
			MetaData metaData2 = createMetaDataWithMethodAndOperationFrom2(httpServletRequest);

			Persona p = new Persona();
			
			//guarda la persona
			servicio.guardarPersona(p);
			servicio.recuperarPersona(1L);
			
		    responseApi.setMeta(metaData2);
		    r.guardada(true);
		    responseApi.setData(r);
		    responseApi.setErrors(Collections.emptyList());
			
		} catch (Exception e ) {
			LOGGER.error("No se pudo guardar compra: {}" + e.getMessage());
			r.guardada(false);
			responseApi.setData(r);		
		}

		LOGGER.info("Fin de la llamada a guardar compra.");
		return new ResponseEntity<ResponseApiCompras>(responseApi, httpStatus);
	}
	
	private MetaData createMetaDataWithMethodAndOperationFrom2(HttpServletRequest httpServletRequest) {
		MetaData newMetaData = new MetaData();
		newMetaData.setMethod(httpServletRequest.getMethod());
		newMetaData.setOperation(httpServletRequest.getRequestURI());
		return newMetaData;
	}

}