package view;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Usuario;

public class Teste {

    public static void main(String[] args) {
        // Crie o EntityManagerFactory com base nas configurações definidas no persistence.xml
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Fuctura-aula-jpa");

        // Crie um EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Inicie a transação
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            // Execute as operações do banco de dados aqui

            // Exemplo: Adicione uma entidade ao banco de dados
            Usuario usuario = new Usuario();
            usuario.setLogin("Exemplo");
            entityManager.persist(usuario);

            // Commit a transação se tudo ocorrer bem
            transaction.commit();
        } catch (Exception e) {
            // Se ocorrer um erro, faça rollback da transação
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Trate o erro conforme necessário
        } finally {
            // Feche o EntityManager quando terminar
            entityManager.close();
        }

        // Feche o EntityManagerFactory quando a aplicação encerrar
        entityManagerFactory.close();
    }
}
