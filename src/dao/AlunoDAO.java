package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.Aluno;

public class AlunoDAO {

    private EntityManagerFactory emf = null;
    private EntityManager em = null;

    public void iniciar() {
        emf = Persistence.createEntityManagerFactory("EscolaPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    public void encerrar() {
        em.close();
    }

    public void inserir(Aluno aluno) {
        em.persist(aluno);
        em.getTransaction().commit();
        System.out.println("Aluno Cadastrado com Sucesso!");
    }

    public Aluno consultarId(Long id) {
        Aluno aluno;
        aluno = em.find(Aluno.class, id);
        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return aluno;
        }
        return aluno;
    }
    
    public void atualizarPorID(Long id, Aluno aluno) {
        Aluno consulta = consultarId(id);
        
        if (consulta == null) {
            em.persist(aluno);
            System.out.println("Aluno Cadastrado com Sucesso!");
        } else {
            consulta.setNome(aluno.getNome());
            consulta.setCurso(aluno.getCurso());
            em.merge(consulta);
            System.out.println("Aluno Atualizado com Sucesso!");
        }
        em.getTransaction().commit();
    }

    /*public void atualizar(Aluno aluno) {
        if (aluno.getID() == null) {
            em.persist(aluno);
            System.out.println("Aluno Cadastrado com Sucesso!");
        } else {
            em.merge(aluno);
            System.out.println("Aluno Atualizado com Sucesso!");
        }
        em.getTransaction().commit();
    }*/

    public void remover(Long id) {
        Aluno aluno;
        aluno = consultarId(id);
        if (aluno == null) {
        } else {
            em.remove(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno Removido com Sucesso!");
        }
    }

    public Aluno consultarPorJPQL(Long id) {
        Query busca = em.createQuery("SELECT a FROM Aluno a WHERE a.ID = :id");
        busca.setParameter("id", id);
        Aluno aluno = (Aluno) busca.getSingleResult();
        return aluno;
    }
}
