package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import api.exception.Mensagem;

/**
 * Classe para gerenciar template em planilhas
 * 
 * @author Paulo Hainosz
 *
 */
public class GerenciadorPlanilhaTemplate {

//	public static void main(String[] args) {
//		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("pessoas.xlsx");
//		try (OutputStream outputStream = new FileOutputStream("C:\\Users\\e804684\\Desktop\\testeJXLS\\jxls.xlsx")) {
//			Context context = new Context();
//			List<String> list = Arrays.asList("Paulo", "João", "Maria", "Carlos");
//			context.putVar("pessoas", list);
//
//			JxlsHelper.getInstance().processTemplate(inputStream, outputStream, context);
//		} catch (IOException e) {
//			throw new WebApplicationException(new Mensagem("Falha na execução da planilha", Status.BAD_REQUEST).toString());
//		}
//	}

	/**
	 * Metodo que faz a troca de variaveis na planilha por itens da lista
	 * 
	 * @param list
	 * @return
	 */
	public static byte[] trocarVariaveis(List<?> list) {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("pessoas.xlsx");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Context context = new Context();
		context.putVar("teste", list);
		try {
			JxlsHelper.getInstance().processTemplate(inputStream, outputStream, context);
		} catch (IOException e) {
			throw new WebApplicationException(new Mensagem("Falha na execução da planilha", Status.BAD_REQUEST).toString());
		}
		return outputStream.toByteArray();
	}

}
