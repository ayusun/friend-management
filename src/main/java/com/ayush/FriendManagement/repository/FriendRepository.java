package com.ayush.FriendManagement.repository;

import com.ayush.FriendManagement.entity.FriendEntity;
import com.ayush.FriendManagement.entity.FriendEntityPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class FriendRepository {

    @PersistenceContext
    private EntityManager em;

    public void saveFriendShip(String from, String to){
        FriendEntity entity1 = new FriendEntity();
        FriendEntityPk pk1 = new FriendEntityPk();
    }


}
