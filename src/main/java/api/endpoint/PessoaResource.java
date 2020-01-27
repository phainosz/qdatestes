package api.endpoint;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import dao.PessoaDAO;
import entity.Pessoa;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Pessoas")
@Path("pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaResource {

	@Inject
	private PessoaDAO pessoaDAO;

	@Context
	private UriInfo uriInfo;

	@ApiOperation("Lista todas as Pessoas gravadas na base de dados")
	@GET
	public Response listar() {
		return Response.ok().entity(pessoaDAO.listar()).build();
	}

	@ApiOperation("Inserir Pessoas na base de dados")
	@POST
	public Response inserir(Pessoa pessoa) {
		Pessoa inserida = pessoaDAO.inserir(pessoa);
		return Response.created(criarUri(inserida)).entity(inserida).build();
	}

	/**
	 * Cria URI de acordo com o id e a classe
	 * 
	 * @param inserir
	 * @return
	 */
	private URI criarUri(Pessoa inserida) {
		return uriInfo.getBaseUriBuilder().path(ArquivoResource.class).path(inserida.getId().toString()).build();
	}
}
