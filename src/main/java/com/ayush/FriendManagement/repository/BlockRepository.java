package com.ayush.FriendManagement.repository;

import com.ayush.FriendManagement.entity.BlockEntity;
import com.ayush.FriendManagement.entity.BlockEntityPk;
import com.ayush.FriendManagement.entity.SubscriptionEntityPk;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BlockRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * Stores the record for Block. It makes single entry into the database
     * @param email1 emaildId of Person1
     * @param email2 emailId of person 2
     */
    public void saveBlock(String email1, String email2){
        try {
            BlockEntity entity = new BlockEntity();
            BlockEntityPk pk = new BlockEntityPk();
            pk.setEmailFrom(email1);
            pk.setEmailTo(email2);
            entity.setPk(pk);


            em.persist(entity);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param email1
     * @param email2
     * @return
     */
    public BlockEntity getBlockEntity(String email1, String email2){
        BlockEntity returnable = null;
        BlockEntityPk pk= new BlockEntityPk();
        pk.setEmailFrom(email1);
        pk.setEmailTo(email2);
        try{
            returnable = em.find(BlockEntity.class, pk);
        }catch(Exception e){

        }

        return returnable;
    }


}
