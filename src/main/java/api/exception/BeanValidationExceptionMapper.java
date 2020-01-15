package api.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path.Node;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Classe que trata excecoes do tipo bean validation
 * 
 * @author Paulo Hainosz
 *
 */
@Provider
public class BeanValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException e) {
		Status status = Status.NOT_ACCEPTABLE;
		MensagemDeErro msg = new MensagemDeErro(getValidacoes(e), status);
		return Response.status(status).entity(msg).type(MediaType.APPLICATION_JSON).build();
	}

	private String getValidacoes(ConstraintViolationException constraintViolation) {
		StringBuilder builder = new StringBuilder();
		for (ConstraintViolation<?> cv : constraintViolation.getConstraintViolations()) {
			String field = "";
			for (Node node : cv.getPropertyPath()) {
				field = node.getName();
			}
			builder.append("Erro: '" + field + "' " + cv.getMessage() + "\n");
		}
		return builder.toString();
	}

}
