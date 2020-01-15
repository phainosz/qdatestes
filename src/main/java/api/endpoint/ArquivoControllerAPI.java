package api.endpoint;

import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.poi.util.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import dao.ArquivoDAO;
import entities.Arquivo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Arquivos")
@Path("arquivos")
@Produces(MediaType.APPLICATION_JSON)
public class ArquivoControllerAPI {

	@Inject
	private ArquivoDAO arquivoDAO;

	@Context
	private UriInfo uriInfo;

	@GET
	@Produces("*/*")
	@Path("{arquivo}")
	@ApiOperation("Fazer o download de um arquivo a partir do nome informado pela URI")
	public Response downloadArquivo(@PathParam("arquivo") String nome) {
		Arquivo encontrado = arquivoDAO.buscar(nome);
		if (encontrado != null) {
			return Response.ok(encontrado.getFile()).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encontrado.getNome() + "\"").build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@POST
	@ApiOperation("Inserir um arquivo e fazer o upload para a base de dados")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadArquivo(MultipartFormDataInput input) {
		Arquivo file = new Arquivo();
		List<InputPart> inputParts = input.getFormDataMap().get("file");
		for (InputPart part : inputParts) {
			try {
				file.setFile(IOUtils.toByteArray(part.getBody(InputStream.class, null)));
			} catch (Exception e) {
				throw new WebApplicationException("Falha no download");
			}
			String nomeCompletoComTipo = criarNomeFile(part.getHeaders());
			String[] split = nomeCompletoComTipo.split(Pattern.quote("."));
			file.setNome(nomeCompletoComTipo);
			file.setTipo(split[split.length - 1]);
		}

		Arquivo inserir = arquivoDAO.inserir(file);
		return Response.created(criarUri(inserir)).build();

		// funciona
//		if (arquivo != null) {
//			Arquivo inserir = arquivoDAO.inserir(arquivo);
//			return Response.created(criarUri(inserir)).build();
//		}
//		return Response.status(Status.BAD_REQUEST).build();
	}

	/**
	 * Recupera o nome do arquivo sendo enviado
	 * 
	 * @param headers
	 * @return
	 */
	private String criarNomeFile(MultivaluedMap<String, String> headers) {
		String[] contentDisposition = headers.getFirst("Content-Disposition").split(";");

		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");

				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}

	private URI criarUri(Arquivo inserir) {
		return uriInfo.getBaseUriBuilder().path(ArquivoControllerAPI.class).path(inserir.getId().toString()).build();
	}
}
