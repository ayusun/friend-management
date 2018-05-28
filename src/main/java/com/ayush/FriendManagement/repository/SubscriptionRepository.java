package com.ayush.FriendManagement.repository;

import com.ayush.FriendManagement.entity.FriendEntity;
import com.ayush.FriendManagement.entity.FriendEntityPk;
import com.ayush.FriendManagement.entity.SubscriptionEntity;
import com.ayush.FriendManagement.entity.SubscriptionEntityPk;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SubscriptionRepository {

    @PersistenceContext
    private EntityManager em;

    public void saveSubscription(String email1, String email2){
        try {
            SubscriptionEntity entity = new SubscriptionEntity();
            SubscriptionEntityPk pk = new SubscriptionEntityPk();
            pk.setEmailFrom(email1);
            pk.setEmailTo(email2);
            entity.setPk(pk);


            em.persist(entity);
        } catch(Exception e){
            e.printStackTrace();
        }
    }


}
