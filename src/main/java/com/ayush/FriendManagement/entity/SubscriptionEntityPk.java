package com.ayush.FriendManagement.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class SubscriptionEntityPk implements Serializable {

    @Column(name="email_from")
    private String emailFrom;

    @Column(name="email_to")
    private String emailTo;
}
