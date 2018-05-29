package com.ayush.FriendManagement.repository;

import com.ayush.FriendManagement.entity.FriendEntity;
import com.ayush.FriendManagement.entity.FriendEntityPk;
import com.ayush.FriendManagement.entity.SubscriptionEntity;
import com.ayush.FriendManagement.entity.SubscriptionEntityPk;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SubscriptionRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * Stores the record for Subscription. It makes single entry into the database
     * @param email1 emaildId of Person1
     * @param email2 emailId of person 2
     */
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

    public List<String> getSubscribers(String email){
        List<String> subscribers= new ArrayList<>();
        List<SubscriptionEntity> subscribersEntityList = em.createQuery("SELECT sub FROM SubscriptionEntity sub WHERE sub.pk.emailTo=:email", SubscriptionEntity.class)
                .setParameter("email", email)
                .getResultList();
        subscribers = subscribersEntityList.stream().map(sub -> sub.getPk().getEmailFrom()).collect(Collectors.toList());
        return subscribers;
    }


}
