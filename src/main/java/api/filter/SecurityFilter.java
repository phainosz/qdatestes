package api.filter;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import api.exception.Mensagem;

//@Provider
public class SecurityFilter implements ContainerRequestFilter {

	// header enviado pelo cliente
	private final String AUTHORIZATION_HEADER = "Authorization";
	// a request envia: Authorization como key e value Basic user:password usando
	// encode
	private final String AUTHORIZATION_HEADER_BASIC = "Basic ";

	@Override
	public void filter(ContainerRequestContext context) throws IOException {

		// pode usar isso para bloquear pelo @Path que for desejado
//		context.getUriInfo().getPath().contains("caminho a ser testado");

		// pega o header com nome de Authorization
		List<String> headers = context.getHeaders().get(AUTHORIZATION_HEADER);
		// testa se o header contem algo
		if (headers != null && headers.size() > 0) {
			// Authorization retorna apenas um valor por isso usado direto como o indice 0
			String authToken = headers.get(0);
			// aqui tira o (Basic ) do valor recebido
			authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_BASIC, "");
			// usa um decoder para o usuario e senha enviado
			String decoded = new String(Base64.getDecoder().decode(authToken));
			// quebra a string pelo :
			StringTokenizer tokenizer = new StringTokenizer(decoded, ":");
			// pega o primeiro, que eh o user
			String user = tokenizer.nextToken();
			// pega o segundo, que eh a senha
			String password = tokenizer.nextToken();
			// aqui falta implementar uma busca no banco
			if ("user".equals(user) && "password".equals(password)) {
				return;
			}
		}
		// cria a resposta caso o usuario n seja autorizado
		Response response = Response.status(Status.UNAUTHORIZED).entity(new Mensagem("Usuário não autorizado", Status.UNAUTHORIZED)).build();

		// para a sequencia da API e retorna a response criada
		context.abortWith(response);
	}

}
