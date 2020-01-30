package api.endpoint;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.PessoaDAO;
import entity.Arquivo;
import entity.Pessoa;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import util.GerenciadorDocxUsingxDocReport;

@Path("relatorios")
@Api("Relatorios")
@Produces(MediaType.APPLICATION_JSON)
public class ReportResource {

	@Inject
	private PessoaDAO pessoaDAO;

	@GET
	@Path("pessoas/{id}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@ApiOperation(value = "Gera relatorios de pessoas pelo id buscado", produces = MediaType.APPLICATION_OCTET_STREAM)
	public Response report(@ApiParam(name = "id", required = true) @PathParam("id") Long id) throws Exception {
		Pessoa pessoaEncontrada = pessoaDAO.find(id);
		Arquivo arquivo = new Arquivo();

		// usando o xDocReport
		GerenciadorDocxUsingxDocReport xdocRepost = new GerenciadorDocxUsingxDocReport(pessoaEncontrada);
		arquivo = xdocRepost.criar();

		// usando docx4J
//		GerenciadorDocxUsingDocx4J docx4J = new GerenciadorDocxUsingDocx4J(pessoaEncontrada);
//		arquivo = docx4J.criar();

		return Response.ok(arquivo.getFile()).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo.getNome() + "\"").build();
	}

}
