package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

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

	public static void main(String[] args) throws Exception {
		// pega o template a ser utilizado
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("templatexDocReport.docx");

		// carrega o inputStream com o xDocReport usando o velocity
		IXDocReport docReport = XDocReportRegistry.getRegistry().loadReport(inputStream, TemplateEngineKind.Velocity);

		IContext context = docReport.createContext();

		// faz a troca das variaveis pelos valores informados
		context.put("nome", "Jay Garric");
		context.put("quality", "slowest");

		// cria o novo arquivo
		OutputStream outputStream = new FileOutputStream(new File("C:\\Users\\e804684\\Desktop\\resultadoxDocReport.docx"));

		// processa o novo arquivo
		docReport.process(context, outputStream);
	}
}
