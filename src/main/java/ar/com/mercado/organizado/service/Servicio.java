package ar.com.mercado.organizado.service;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ar.com.mercado.organizado.Demo1Application;
import ar.com.mercado.organizado.entity.Persona;
import ar.com.mercado.organizado.exception.ModelException;
import ar.com.mercado.organizado.repository.Repositorio;


@Service
public class Servicio {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(Servicio.class);
	
	@Autowired
	@Qualifier("repositorio")
	private Repositorio repositorio;

	
	public Persona recuperarPersona(Long id) throws ModelException {
		LOGGER.info("Comienza a procesar una cosa {}", repositorio);
		Persona p;
		try {
			p = repositorio.findByIdPersona(id);
		} catch (Exception ex) {
			LOGGER.error(" Error al procesar: {}", ex.getMessage());
			
			throw new ModelException("Error al procesar algo " + repositorio + ", error: " + ex.getMessage());
		}
		LOGGER.info("Termina de procesar una cosa {}", repositorio);
		return p;
	}
	
	public ResponseEntity<String> executePost(String uri, String body) throws ModelException {
		LOGGER.info("Inicio de llamada a servicio orquestador {}");
		ResponseEntity<String> response = null;
		try {
			RestTemplate restTemplate = this.restTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.add("Content-Type", "application/json");
			HttpEntity<String> entity = new HttpEntity<>(body, headers);
			response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

			return response;
		} catch (Exception ex) {
			LOGGER.error(" [{}] - No se pudo ejecutar POST, el orquestador devuelve: {}", ex.getMessage());
			
			throw new ModelException("Error proveniente desde el servicio " + uri + ", codigo http: " + ex.getMessage());
		}
	}
	
	public RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
				.build();

		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		return restTemplate;
	}

	public void guardarPersona(Persona p) {
		repositorio.save(p);	
	}

}
