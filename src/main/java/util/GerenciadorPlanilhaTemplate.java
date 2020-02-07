package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import api.exception.Mensagem;
import model.Arquivo;

/**
 * Classe para gerenciar template em planilhas
 * 
 * @author Paulo Hainosz
 *
 */
public class GerenciadorPlanilhaTemplate {

//	public static void main(String[] args) {
//		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("teste.xlsx");
//		try (OutputStream outputStream = new FileOutputStream("C:\\Users\\e804684\\Desktop\\testeJXLS\\jxls.xlsx")) {
//			Context context = new Context();
//			List<String> estacao = Arrays.asList("Estacao 1", "Estacao 2", "Estacao 3", "Estacao 4");
//			List<String> parametro = Arrays.asList("Parametro 1", "Parametro 2", "Parametro 3", "Parametro 4");
//			context.putVar("estacao", estacao);
//			context.putVar("parametro", parametro);
//
//			JxlsHelper.getInstance().processTemplate(inputStream, outputStream, context);
//		} catch (IOException e) {
//			throw new RuntimeException(new Mensagem("Falha na execução da planilha", Status.BAD_REQUEST).toString());
//		}
//	}

	/**
	 * Metodo que faz a troca de variaveis na planilha por itens da lista
	 * 
	 * @param list
	 * @return
	 */
	public static Arquivo gerenciar(List<String> pessoas) {
		if (pessoas.isEmpty()) {
			throw new NotFoundException(new Mensagem("Não existem pessoas armazenadas no banco", Status.NOT_FOUND).toString());
		}
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("pessoas.xlsx");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Context context = new Context();
		context.putVar("pessoas", pessoas);
		try {
			JxlsHelper.getInstance().processTemplate(inputStream, outputStream, context);
		} catch (IOException e) {
			throw new WebApplicationException(new Mensagem("Falha na execução da planilha", Status.BAD_REQUEST).toString());
		}
		Arquivo arquivo = new Arquivo("teste.xlsx", outputStream.toByteArray());
		return arquivo;
	}

}
