package com.ayush.FriendManagement.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class FriendEntityPk implements Serializable {

    @Column(name="friend_email_from")
    private String emailFrom;

    @Column(name="friend_email_to")
    private String emailTo;
}
