package api.exception;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

/**
 * Classe que trata excecoes de atributos de entidades nao existentes
 *
 */
@Provider
public class UnrecognizedPropertyExceptionMapper implements ExceptionMapper<UnrecognizedPropertyException> {

	@Override
	public Response toResponse(UnrecognizedPropertyException e) {
		// alterar e usar esse aqui apenas e.getPropertyName();
		return buildResponse(buildMessage(e));
	}

	private MensagemDeErro buildMessage(UnrecognizedPropertyException e) {
		List<Reference> references = e.getPath();
		String wrongFieldMessage = "Verifique se os campos estão corretos";
		if (CollectionUtils.isNotEmpty(references)) {
			wrongFieldMessage = "Verifique se o campo '" + references.get(0).getFieldName() + "' está correto";
		}
		Collection<Object> propertyIds = e.getKnownPropertyIds();
		if (CollectionUtils.isNotEmpty(propertyIds)) {
			wrongFieldMessage += " dentre as seguintes opções: ".concat(propertyIds.toString());
		}
		wrongFieldMessage += ".";
		return new MensagemDeErro(wrongFieldMessage, Status.BAD_REQUEST);
	}

	private static Response buildResponse(MensagemDeErro mensagem) {
		return Response.status(Status.fromStatusCode(mensagem.getStatus())).type(MediaType.APPLICATION_JSON).entity(mensagem).build();
	}

}
