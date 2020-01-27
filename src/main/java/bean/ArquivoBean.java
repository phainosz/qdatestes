package bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import entity.Arquivo;
import entity.Pessoa;
import service.ArquivoService;
import service.PessoaService;
import util.GerenciadorPlanilha;
import util.JSFUtils;

@Named
@ViewScoped
public class ArquivoBean implements Serializable {
	private static final long serialVersionUID = -2252594811686258706L;

	private Arquivo arquivo;

	@Inject
	private ArquivoService arquivoService;

	@Inject
	private PessoaService pessoaService;

	private List<Pessoa> dados;

	public ArquivoBean() {
		this.arquivo = new Arquivo();
		this.dados = new ArrayList<>();
	}

	public void gravar() {
//		Arquivo inserido = arquivoService.inserir(this.arquivo);
//		if (inserido != null) {
//			JSFUtils.addMessage("Arquivo Inserido no banco");
//			this.arquivo = new Arquivo();
//		}

		for (Pessoa pessoa : dados) {
			pessoaService.inserir(pessoa);
			System.out.println("Pessoa: " + pessoa);
		}

	}

	/**
	 * Metodo que recebe um arquivo para upload
	 * 
	 * @param event
	 */
	public void fileUpload(FileUploadEvent event) {
		if (event.getFile() != null) {
			UploadedFile file = event.getFile();
			copiarDadosExcelParaEntidade(file);
			// this.arquivo = new Arquivo(file.getFileName(), file.getContents(),
			// file.getContentType(), file.getSize());
		}
	}

	/**
	 * Faz a copia dos arquivos da planilha para a entidade que sera persistida
	 * 
	 * @param file
	 */
	private void copiarDadosExcelParaEntidade(UploadedFile file) {
		GerenciadorPlanilha gerenciador = new GerenciadorPlanilha();
		try {
			this.dados = gerenciador.criar(file);
		} catch (IOException e) {
			JSFUtils.addWarnMessage(e.getMessage());
		}

	}

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}
}
