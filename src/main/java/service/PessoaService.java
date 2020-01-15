package service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dao.GenericDAO;
import dao.PessoaDAO;
import entities.Pessoa;
import enums.Acao;

@Stateless
public class PessoaService extends GenericService<Pessoa> {

	@EJB
	private PessoaDAO dao;

	@Override
	public GenericDAO<Pessoa> getEntidadeDAO() {
		return this.dao;
	}

	@Override
	public void regrasDeNegocioEntidade(Pessoa entity, Acao acao) {
		// TODO Auto-generated method stub

	}

}
