package api.exception;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;

/**
 * Classe que trata excecoes de valores invalidos no JSON
 * 
 * @author Paulo Hainosz
 *
 */
@Provider
public class JsonExceptionMapper implements ExceptionMapper<JsonMappingException> {

	@Override
	public Response toResponse(JsonMappingException e) {
		Status status = Status.NOT_ACCEPTABLE;
		List<Reference> references = e.getPath();
		String mensagem = "Propriedade " + references.get(0).getFieldName() + " inválida";

		MensagemDeErro msg = new MensagemDeErro(mensagem, status);
		return Response.status(status).entity(msg).type(MediaType.APPLICATION_JSON).build();
	}
}
