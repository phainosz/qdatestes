package api.endpoint;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.PessoaDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Pessoas")
@Path("pessoas")
@Produces(MediaType.APPLICATION_JSON)
public class PessoaResource {

	@Inject
	private PessoaDAO pessoaDAO;

	@GET
	@ApiOperation(value = "Lista todas as Pessoas gravadas na base de dados", produces = MediaType.APPLICATION_JSON)
	public Response listar() {
		return Response.ok().entity(pessoaDAO.listar()).build();
	}
}
