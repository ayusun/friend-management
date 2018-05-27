package com.ayush.FriendManagement.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;

@Embeddable
@Data
public class FriendEntityPk {

    @Column(name="friend_email_from")
    private String emailFrom;

    @Column(name="friend_email_to")
    private String emailTo;
}
