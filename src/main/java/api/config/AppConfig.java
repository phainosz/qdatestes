package api.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;

/**
 * Configuracao de caminho inicia para a API REST
 * 
 * @author E804684
 *
 */
@ApplicationPath("api")
public class AppConfig extends Application {

	public AppConfig() {
		BeanConfig conf = new BeanConfig();
		conf.setTitle("API de testes para o QDA");
		conf.setDescription("Projeto para upload e download de arquivos em rest. Upload de planilha excel em formato padrao para salvar os dados no banco. Realizar relatorios em PDF");
		conf.setVersion("1.0.0");
		conf.setHost("localhost:8080");
		conf.setBasePath("/qdatestes/api");
		conf.setSchemes(new String[] { "http" });
		conf.setResourcePackage("api.endpoint");
		// pretty print pode causar perca de performance para identar o codigo
		conf.setPrettyPrint(true);
		conf.setScan(true);
	}
}
