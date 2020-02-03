package service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dao.ArquivoDAO;
import dao.GenericDAO;
import enumerated.AcaoDAO;
import model.Arquivo;

@Stateless
public class ArquivoService extends GenericService<Arquivo> {

	@EJB
	private ArquivoDAO dao;

	@Override
	public GenericDAO<Arquivo> getEntidadeDAO() {
		return dao;
	}

	@Override
	public void regrasDeNegocioEntidade(Arquivo entity, AcaoDAO acaoDAO) {
		// TODO Auto-generated method stub

	}

}
