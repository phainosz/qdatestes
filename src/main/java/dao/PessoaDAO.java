package dao;

import java.util.List;

import javax.ejb.Stateless;

import entity.Pessoa;

@Stateless
public class PessoaDAO extends GenericDAO<Pessoa> {

	@Override
	public List<Pessoa> listar() {
		return getEntityManager().createQuery("select p from Pessoa p", Pessoa.class).getResultList();
	}

}
