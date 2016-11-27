import DAL.UserDB;
import Models.UserEntity;

/**
 * Created by Eddie on 2016-11-27.
 */
public class testClass {
    public static void main(String[] args){
        UserEntity user = UserDB.findByName("test");
        System.out.println(user.getUsername() + "blablablablablaa");
    }
}
