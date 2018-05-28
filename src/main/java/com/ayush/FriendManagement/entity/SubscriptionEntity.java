package com.ayush.FriendManagement.entity;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="SUBCRIPTIONS")
@Data
public class SubscriptionEntity {

    @EmbeddedId
    private SubscriptionEntityPk pk;

}