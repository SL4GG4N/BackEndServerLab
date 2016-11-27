package Models;

import javax.persistence.*;

/**
 * Created by Eddie on 2016-11-27.
 */
@Entity
@Table(name = "user_log", schema = "ServerUtveckling", catalog = "")
public class UserLogEntity {
    private int id;
    private UserEntity userToEntity;
    private UserEntity userFromEntity;
    private String message;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "From_User", referencedColumnName = "Id")
    public UserEntity getFromUserEntity(){
        return userFromEntity;
    }
    public void setFromUserEntity(UserEntity userEntity){
        this.userFromEntity = userEntity;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "To_User", referencedColumnName = "Id")
    public UserEntity getToUserEntity(){
        return userToEntity;
    }
    public void setToUserEntity(UserEntity userEntity){
        this.userToEntity = userEntity;
    }

    @Basic
    @Column(name = "Message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLogEntity that = (UserLogEntity) o;

        if (id != that.id) return false;
        if (userFromEntity != that.userFromEntity) return false;
        if (userToEntity != that.userToEntity) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
