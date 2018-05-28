package com.ayush.FriendManagement.service;

import com.ayush.FriendManagement.RepeatedArgumentException;
import com.ayush.FriendManagement.exceptions.FriendAlreadyExistException;
import com.ayush.FriendManagement.exceptions.SubscriptionAlreadyExistException;
import com.ayush.FriendManagement.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SubscriptionService {

    private static final int FRIEND_EMAIL_LENGTH = 2;
    @Autowired
    private SubscriptionRepository subscriptionDao;

    public boolean createSubscription(final String requestor, final String target) throws RepeatedArgumentException, IllegalArgumentException {

        if(requestor == null || target == null || requestor.length() == 0 || target.length() == 0){
            throw new IllegalArgumentException("Parameters are not according to contract");
        }

        String email1Processed = requestor.toLowerCase().trim();
        String email2Processed = target.toLowerCase().trim();

        if (email1Processed.equals(email2Processed)) {
            throw new RepeatedArgumentException(requestor, target);
        }

        try {
            subscriptionDao.saveSubscription(email1Processed, email2Processed);
        } catch (DataIntegrityViolationException e) {
            throw new SubscriptionAlreadyExistException();
        }
        return true;
    }
}

