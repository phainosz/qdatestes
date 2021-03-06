package service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dao.GenericDAO;
import dao.PessoaDAO;
import enumerated.AcaoDAO;
import model.Pessoa;

@Stateless
public class PessoaService extends GenericService<Pessoa> {

	@EJB
	private PessoaDAO dao;

	@Override
	public GenericDAO<Pessoa> getEntidadeDAO() {
		return this.dao;
	}

	@Override
	public void regrasDeNegocioEntidade(Pessoa entity, AcaoDAO acaoDAO) {
		// TODO Auto-generated method stub

	}

}
