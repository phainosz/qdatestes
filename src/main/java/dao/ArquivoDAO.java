package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.NotFoundException;

import model.Arquivo;

@Stateless
public class ArquivoDAO extends GenericDAO<Arquivo> {

	@Override
	public List<Arquivo> listar() {
		return getEntityManager().createQuery("select a from Arquivo a", Arquivo.class).getResultList();
	}

	/**
	 * Busca um arquivo pelo nome indicado no parametro de pesquisa
	 * 
	 * @param nome
	 * @return
	 */
	public Arquivo buscar(String nome) {
		try {
			return getEntityManager().createQuery("select a from Arquivo a where a.nome LIKE :p1", Arquivo.class).setParameter("p1", "%" + nome + "%").getSingleResult();
		} catch (Exception e) {
			throw new NotFoundException("Arquivo " + nome + ", não encontrado");
		}

	}

}
