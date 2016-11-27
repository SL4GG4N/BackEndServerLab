package DAL;

import Models.UserEntity;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Gurris on 2016-11-03.
 */
public class UserDB {

    public static UserEntity findByName(String name){
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
            EntityManager em = emf.createEntityManager();
            Query q = em.createQuery("select user from UserEntity user where user.username in (:name)");
            q.setParameter("name", name);
            UserEntity user = (UserEntity) q.getSingleResult();
            return user;
        }catch (Exception e){
            System.out.println("------UserDB / findByName---------");
            e.printStackTrace();
            System.out.println("----------------------------------");
            return null;
        }
    }


    public static UserEntity findById(int id){
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
            EntityManager em = emf.createEntityManager();
            Query q = em.createQuery("select user from UserEntity user where user.id in (:id)");
            q.setParameter("id", id);
            UserEntity user = (UserEntity) q.getSingleResult();
            return user;
        }catch (Exception e){
            System.out.println("------UserDB / findById---------");
            e.printStackTrace();
            System.out.println("----------------------------------");
            return null;
        }
    }

    public static void test(){
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            UserEntity te = new UserEntity();
            //te.setNumber(9001);
            em.persist(te);
            em.getTransaction().commit();
        }catch (Exception e){
            System.out.println("------UserDB / test---------");
            e.printStackTrace();
            System.out.println("----------------------------------");
            e.printStackTrace();
        }
    }

    public static List<UserEntity> getAll(){
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
            EntityManager em = emf.createEntityManager();
            TypedQuery<UserEntity> query = em.createNamedQuery("UserEntity.findAll", UserEntity.class);
            List<UserEntity> result = query.getResultList();
            return result;
        }catch (Exception e){
            System.out.println("------UserDB / getAll---------");
            e.printStackTrace();
            System.out.println("----------------------------------");
            return null;
        }
    }

    public static List<UserEntity> findUsersByUsername(String username){
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
            EntityManager em = emf.createEntityManager();
            Query q = em.createQuery("select user from UserEntity user where user.username LIKE in (:username)");

            q.setParameter("username", username+"%");
            List<UserEntity> user = q.getResultList();

            return user;
        }catch (Exception e){
            System.out.println("------UserDB / findUsersByUsername---------");
            e.printStackTrace();
            System.out.println("----------------------------------");
            return null;
        }
    }

    public static boolean registerUser(UserEntity user){
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.flush();
            em.getTransaction().commit();
            System.out.println("HERERERERERERERERER:::::: " + user.getId());
            return true;
        }catch (Exception e){
            System.out.println("------UserDB / registerUser---------");
            e.printStackTrace();
            System.out.println("----------------------------------");
            return false;
        }
    }

}
