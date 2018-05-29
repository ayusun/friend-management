package com.ayush.FriendManagement.entity;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="BLOCKS")
@Data
public class BlockEntity {

    @EmbeddedId
    private BlockEntityPk pk;

}