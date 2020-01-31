package util;

import java.io.InputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.output.ByteArrayOutputStream;

import api.exception.Mensagem;
import entity.Arquivo;
import entity.Pessoa;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

/**
 * Classe responsavel por ler o template e substituir com novos dados usando
 * xDocReport
 * 
 * @author Paulo Hainosz
 *
 */
public class GerenciadorDocxUsingxDocReport implements GerenciadorDocx {

	public Arquivo criar(Pessoa pessoa) {
		// pega o template a ser utilizado
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("templatexDocReport.docx");

		try {
			// carrega o inputStream com o xDocReport usando o velocity
			IXDocReport docReport = XDocReportRegistry.getRegistry().loadReport(inputStream, TemplateEngineKind.Velocity);

			IContext context = docReport.createContext();

			// faz a troca das variaveis pelos valores informados
			context.put("nome", pessoa.getNome());
			context.put("sobrenome", pessoa.getSobrenome());
			context.put("cpf", pessoa.getCpf());
			context.put("idade", pessoa.getIdade().toString());
			context.put("nascimento", pessoa.getDataNascimento().toString());

			// cria o novo arquivo
//			OutputStream outputStream = new FileOutputStream(new File("C:\\Users\\e804684\\Desktop\\resultadoxDocReport.docx"));
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			// converte para PDF
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			docReport.convert(context, options, outputStream);

			// processa o novo arquivo
			docReport.process(context, outputStream);

			return new Arquivo(pessoa.getNome() + ".pdf", outputStream.toByteArray());
		} catch (Exception e) {
			throw new WebApplicationException(new Mensagem("Falha ao criar o PDF", Status.BAD_REQUEST).toString());
		}
	}
}
