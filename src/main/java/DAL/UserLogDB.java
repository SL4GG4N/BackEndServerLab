package DAL;

import Models.UserEntity;
import Models.UserLogEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;

/**
 * Created by Gurris on 2016-11-12.
 */
public class UserLogDB {

    public static boolean addToUserLog(UserEntity toUser, UserEntity fromUser, String log_message){
        try{
            //System.out.println("awdagsrg");
            //System.out.println("toUser: " + toUser.getUsername() + " fromUser: " + fromUser.getUsername());
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            UserLogEntity log = new UserLogEntity();
            log.setMessage(log_message);
            log.setToUserEntity(toUser);
            log.setFromUserEntity(fromUser);
            em.persist(log);
            em.flush();
            em.getTransaction().commit();
            System.out.println("log id is: " + log.getId());
            return true;
        }catch (Exception e){
            System.out.println("------UserLogDB / addToUserLog---------");
            e.printStackTrace();
            System.out.println("----------------------------------");
            return false;
        }

    }

    public static ArrayList<UserLogEntity> getUsersLogFromUId(int id){
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
            EntityManager em = emf.createEntityManager();

            Query q = em.createQuery("select log from UserLogEntity log where log.toUserEntity.id in (:id)");
            q.setParameter("id", id);

            ArrayList<UserLogEntity> users = (ArrayList<UserLogEntity>) q.getResultList();
            return users;
        }catch (Exception e){
            System.out.println("------UserLogDB / getUsersLogFromUId---------");
            e.printStackTrace();
            System.out.println("----------------------------------");
            return null;
        }
    }

}
