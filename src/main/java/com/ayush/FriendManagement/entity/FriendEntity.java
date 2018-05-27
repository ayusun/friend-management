package com.ayush.FriendManagement.entity;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="FRIEND")
@Data
public class FriendEntity {

    @EmbeddedId
    private FriendEntityPk pk;

}
