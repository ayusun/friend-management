package com.ayush.FriendManagement.service;

import com.ayush.FriendManagement.RepeatedArgumentException;
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

    /**
     * This method created Subscription between two emails provided
     *
     * NOTE : Emails should have been verified before calling this method
     *
     * @param requestor who has requested to be subscribed
     * @param target email who requester wants to get subscribed to
     *
     * @return
     * @throws RepeatedArgumentException if both the requestor and target are same
     * @throws IllegalArgumentException if either of the email is null, or blank
     * @throws SubscriptionAlreadyExistException if subscription already exist in the database
     */
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

