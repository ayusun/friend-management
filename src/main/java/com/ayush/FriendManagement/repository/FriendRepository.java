package com.ayush.FriendManagement.repository;

import com.ayush.FriendManagement.entity.FriendEntity;
import com.ayush.FriendManagement.entity.FriendEntityPk;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FriendRepository {

    @PersistenceContext
    private EntityManager em;

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

    public List<String> getFriends(String emailId){
       return em.createQuery("select f.pk.emailTo FROM FriendEntity f WHERE f.pk.emailFrom=:email", String.class)
                .setParameter("email", emailId)
                .getResultList();
    }


}
