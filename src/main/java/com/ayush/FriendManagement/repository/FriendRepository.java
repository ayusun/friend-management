package com.ayush.FriendManagement.repository;

import com.ayush.FriendManagement.entity.FriendEntity;
import com.ayush.FriendManagement.entity.FriendEntityPk;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * This Repository deals with the Friend Table
 *
 * @author Ayush
 */
@Repository
public class FriendRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * Stores the record for friendship. It makes two entry to represent bi-directional friendship
     * @param email1 emaildId of Person1
     * @param email2 emailId of person 2
     */
    public void saveFriendShip(String email1, String email2){
        try {
            FriendEntity entity1 = new FriendEntity();
            FriendEntityPk pk1 = new FriendEntityPk();
            pk1.setEmailFrom(email1);
            pk1.setEmailTo(email2);
            entity1.setPk(pk1);

            FriendEntity entity2 = new FriendEntity();
            FriendEntityPk pk2 = new FriendEntityPk();
            pk2.setEmailFrom(email2);
            pk2.setEmailTo(email1);
            entity2.setPk(pk2);

            em.persist(entity1);
            em.persist(entity2);
        } catch(Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Get List of Friends for a email Id. This might return blank list if there are no records
     * @param emailId
     * @return
     */
    public List<String> getFriends(String emailId){
       return em.createQuery("select f.pk.emailTo FROM FriendEntity f WHERE f.pk.emailFrom=:email", String.class)
                .setParameter("email", emailId)
                .getResultList();
    }

    /**
     * Get List of Mutual Friends given two email id
     * @param email1  Email Id of first person
     * @param email2  EmailId of second Person
     * @return
     */
    public List<String> getMutualFriends(String email1, String email2){
        return (List<String>)em.createNativeQuery("(select friend_email_to from friend where friend_email_from=:email1) INTERSECT" +
                "(select friend_email_to from friend where friend_email_from=:email2)")
                .setParameter("email1", email1)
                .setParameter("email2", email2)
                .getResultList();
    }


}
