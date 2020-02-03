package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import dao.ArquivoDAO;
import model.Arquivo;
import model.Pessoa;
import service.PessoaService;
import util.GerenciadorPlanilha;
import util.JSFUtils;

@Named
@ViewScoped
public class ArquivoBean implements Serializable {
	private static final long serialVersionUID = -2252594811686258706L;

	private Arquivo arquivo;

	@Inject
	private ArquivoDAO arquivoDAO;

	@Inject
	private PessoaService pessoaService;

	private List<Pessoa> dados;

	public ArquivoBean() {
		this.arquivo = new Arquivo();
		this.dados = new ArrayList<>();
	}

	public void gravar() {
		Arquivo gravado = arquivoDAO.inserir(arquivo);
		for (Pessoa pessoa : dados) {
			pessoaService.inserir(pessoa);
			System.out.println("Pessoa: " + pessoa);
		}
		if (gravado != null) {
			dados.clear();
			arquivo = new Arquivo();
			JSFUtils.addMensagem("Arquivo e conteúdo gravado com SUCESSO!!!!!!!!!!!");
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
			this.arquivo = new Arquivo(file.getFileName(), file.getContents(), file.getContentType());
		}
	}

	/**
	 * Faz a copia dos arquivos da planilha para a entidade que sera persistida
	 * 
	 * @param file
	 */
	private void copiarDadosExcelParaEntidade(UploadedFile file) {
		GerenciadorPlanilha gerenciador = new GerenciadorPlanilha();
		this.dados = gerenciador.criar(file.getContents());
	}

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}
}
