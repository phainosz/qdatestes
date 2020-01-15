package api.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Classe generica para mapear exceptions de qualquer tipo que sejam lancadas no
 * servico rest.
 * 
 * @author Paulo Hainosz
 *
 */
//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
//		MensagensDeErro msg = new MensagensDeErro(ex.getMessage(), "500", "INTERNAL_SERVER_ERROR");
		Status status = Status.INTERNAL_SERVER_ERROR;
		MensagemDeErro msg = new MensagemDeErro(ex.getMessage(), status);
		return Response.status(status).entity(msg).type(MediaType.APPLICATION_JSON).build();
	}
}
