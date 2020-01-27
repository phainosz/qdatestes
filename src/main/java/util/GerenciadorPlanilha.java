package util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.UploadedFile;

import entity.Pessoa;

public class GerenciadorPlanilha {

	private List<Pessoa> pessoas;

	public GerenciadorPlanilha() {
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> criar(UploadedFile file) throws IOException {
		pessoas = new ArrayList<>();
		// Recebe o arquivo do upload
		InputStream in = file.getInputstream();
		XSSFWorkbook workbook = new XSSFWorkbook(in);

		// pega o workbook da aba 0
		XSSFSheet sheet = workbook.getSheetAt(0);

		// seta as linhas
		List<Row> rows = (List<Row>) toList(sheet.iterator());

		// remove a linha que identifica cada coluna
		rows.remove(0);

		for (Row row : rows) {
			// seta as colunas
			List<Cell> cells = (List<Cell>) toList(row.cellIterator());

			if (!cells.isEmpty()) {
				adicionarCelulasNaEntidade(cells);
			} else {
				break;
			}
		}

		workbook.close();
		return pessoas;
	}

	/**
	 * Transforma de iterator para List
	 * 
	 * @param iterator
	 * @return
	 */
	private List<?> toList(Iterator<?> iterator) {
		return IteratorUtils.toList(iterator);
	}

	/**
	 * Metodo que atribui os valores das celulas para a entidade desejada
	 * 
	 * @param cells
	 */
	private void adicionarCelulasNaEntidade(List<Cell> cells) {
		Pessoa pessoa = new Pessoa();

		pessoas.add(pessoa);
		for (Cell cell : cells) {
			switch (cell.getColumnIndex()) {

			case 0:
				pessoa.setNome(cell.getStringCellValue());
				break;
			case 1:
				pessoa.setCpf(cell.getStringCellValue());
				break;
			case 2:
				pessoa.setSobrenome(cell.getStringCellValue());
				break;
			case 3:
				pessoa.setIdade((int) cell.getNumericCellValue());
				break;
			case 4:
				pessoa.setDataNascimento(cell.getLocalDateTimeCellValue().toLocalDate());
				break;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> criar(byte[] file) {
		pessoas = new ArrayList<>();
		// Recebe o arquivo do upload
		InputStream in = new ByteArrayInputStream(file);
		XSSFWorkbook workbook;
		try {
			workbook = new XSSFWorkbook(in);

			// pega o workbook da aba 0
			XSSFSheet sheet = workbook.getSheetAt(0);

			// seta as linhas
			List<Row> rows = (List<Row>) toList(sheet.iterator());

			// remove a linha que identifica cada coluna
			rows.remove(0);

			for (Row row : rows) {
				// seta as colunas
				List<Cell> cells = (List<Cell>) toList(row.cellIterator());

				if (!cells.isEmpty()) {
					adicionarCelulasNaEntidade(cells);
				} else {
					break;
				}
			}
			workbook.close();
		} catch (Exception e) {
			throw new WebApplicationException("Falha na leitura da planilha");
		}
		return pessoas;

	}

}
