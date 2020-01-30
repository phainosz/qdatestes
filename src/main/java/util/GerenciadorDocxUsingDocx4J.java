package util;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;

import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

import entity.Arquivo;
import entity.Pessoa;

/**
 * Classe responsavel por ler o template e substituir com novos dados usando
 * Docx4J Segundo informacoes, Docx4J precisa do office instalado
 * 
 * @author Paulo Hainosz
 *
 */
public class GerenciadorDocxUsingDocx4J {

	private Pessoa pessoa;

	public GerenciadorDocxUsingDocx4J(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Arquivo criar() throws Exception {

		// pega o arquivo da pasta de resources
		InputStream templateInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("templateDocx4J.docx");

		// carrega o arquivo
		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);

		MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

		VariablePrepare.prepare(wordMLPackage);

		// define o nome das variaveis a serem trocadas e seus valores
		HashMap<String, String> variaveis = new HashMap<>();
		variaveis.put("nome", this.pessoa.getNome());
		variaveis.put("sobrenome", this.pessoa.getSobrenome());
		variaveis.put("cpf", this.pessoa.getCpf());
		variaveis.put("idade", this.pessoa.getIdade().toString());
		variaveis.put("nascimento", this.pessoa.getDataNascimento().toString());

		// faz a troca das variaveis do template pelos valores desejados
		documentPart.variableReplace(variaveis);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		wordMLPackage.save(outputStream);

		byte[] byteArray = outputStream.toByteArray();

		// grava o novo arquivo
		try (FileOutputStream fos = new FileOutputStream("C:\\Users\\e804684\\Desktop\\resultadoDocx4J.docx")) {
			fos.write(byteArray);
		}
		return new Arquivo(pessoa.getNome() + ".docx", byteArray);
	}
}
