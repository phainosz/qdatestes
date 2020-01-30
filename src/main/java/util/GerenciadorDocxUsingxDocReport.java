package util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import entity.Arquivo;
import entity.Pessoa;
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
public class GerenciadorDocxUsingxDocReport {

	private Pessoa pessoa;

	public GerenciadorDocxUsingxDocReport(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Arquivo criar() throws Exception {
		// pega o template a ser utilizado
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("templatexDocReport.docx");

		// carrega o inputStream com o xDocReport usando o velocity
		IXDocReport docReport = XDocReportRegistry.getRegistry().loadReport(inputStream, TemplateEngineKind.Velocity);

		IContext context = docReport.createContext();

		// faz a troca das variaveis pelos valores informados
		context.put("nome", this.pessoa.getNome());
		context.put("sobrenome", this.pessoa.getSobrenome());
		context.put("cpf", this.pessoa.getCpf());
		context.put("idade", this.pessoa.getIdade().toString());
		context.put("nascimento", this.pessoa.getDataNascimento().toString());

		// cria o novo arquivo
		OutputStream outputStream = new FileOutputStream(new File("C:\\Users\\e804684\\Desktop\\resultadoxDocReport.docx"));
		byte[] file = new byte[1024];
		outputStream.write(file);
		// processa o novo arquivo
		docReport.process(context, outputStream);

		return new Arquivo(this.pessoa.getNome() + ".docx", file);
	}
}
