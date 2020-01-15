package api.endpoint;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import dao.PessoaDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("pessoas")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = "Pessoas")
public class PessoaControllerAPI {

	@Inject
	private PessoaDAO pessoaDAO;

	@Context
	private UriInfo uriInfo;

	@GET
	@ApiOperation("Lista todas as Pessoas gravadas no banco")
	public Response listar() {
		return Response.ok().entity(pessoaDAO.listar()).build();
	}
}
