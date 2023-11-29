package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Usuario;

public class UsuarioDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Fuctura-aula-jpa");

	public void adicionarusuario(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			if (usuario.getId() == null) {
				// Se o ID for nulo, a entidade é nova e deve ser persistida
				em.persist(usuario);
			} else {
				// Se o ID não for nulo, a entidade é detached e deve ser merged
				em.merge(usuario);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public Usuario buscarusuario(Long id) {
		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Usuario.class, id);
		} finally {
			em.close();
		}
	}

	public List<Usuario> listarusuarios() {
		EntityManager em = emf.createEntityManager();

		try {
			return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
		} finally {
			em.close();
		}
	}

	public void atualizarusuario(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.merge(usuario);
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void excluirusuario(Long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			Usuario usuario = em.find(Usuario.class, id);
			if (usuario != null) {
				em.remove(usuario);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void fecharConexao() {
		if (emf != null && emf.isOpen()) {
			emf.close();
		}
	}
}
