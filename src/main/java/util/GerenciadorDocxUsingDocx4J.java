package util;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.docx4j.Docx4J;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

import api.exception.Mensagem;
import model.Arquivo;
import model.Pessoa;

/**
 * Classe responsavel por ler o template e substituir com novos dados usando
 * Docx4J Segundo informacoes, Docx4J precisa do office instalado
 * 
 * @author Paulo Hainosz
 *
 */
public class GerenciadorDocxUsingDocx4J implements GerenciadorDocx {

	public Arquivo criar(Pessoa pessoa) {

		// pega o arquivo da pasta de resources
		InputStream templateInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("templateDocx4J.docx");

		// carrega o arquivo
		try {
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);

			MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

			VariablePrepare.prepare(wordMLPackage);

			// define o nome das variaveis a serem trocadas e seus valores
			HashMap<String, String> variaveis = new HashMap<>();
			variaveis.put("nome", pessoa.getNome());
			variaveis.put("sobrenome", pessoa.getSobrenome());
			variaveis.put("cpf", pessoa.getCpf());
			variaveis.put("idade", pessoa.getIdade().toString());
			variaveis.put("nascimento", pessoa.getDataNascimento().toString());

			// faz a troca das variaveis do template pelos valores desejados
			documentPart.variableReplace(variaveis);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			Docx4J.toPDF(wordMLPackage, outputStream);

			wordMLPackage.save(outputStream);

			byte[] byteArray = outputStream.toByteArray();

			// grava o novo arquivo
			try (FileOutputStream fos = new FileOutputStream("C:\\Users\\e804684\\Desktop\\resultadoDocx4J.docx")) {
				fos.write(byteArray);
			}

			return new Arquivo(pessoa.getNome() + ".docx", byteArray);
		} catch (Exception e) {
			throw new WebApplicationException(new Mensagem("Falha ao gerar PDF", Status.BAD_REQUEST).toString());
		}

	}
}
