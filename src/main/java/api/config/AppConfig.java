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
		conf.setTitle("Projeto APIEUD API");
		conf.setDescription("Projeto para upload e download em rest. Upload de planilha excel padrão, gravar o conteúdo no banco");
		conf.setVersion("1.0.0");
		conf.setHost("localhost:8080");
		conf.setBasePath("/apieud/api");
		conf.setSchemes(new String[] { "http" });
		conf.setResourcePackage("api.endpoint");
		conf.setScan(true);
	}
}
