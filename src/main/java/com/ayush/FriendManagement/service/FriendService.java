package com.ayush.FriendManagement.service;

import com.ayush.FriendManagement.RepeatedArgumentException;
import com.ayush.FriendManagement.exceptions.FriendAlreadyExistException;
import com.ayush.FriendManagement.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Service to Manage All the transactions related to friends
 *
 * @author Ayush
 */
@Service
@Transactional
public class FriendService {

    private static final int FRIEND_EMAIL_LENGTH = 2;
    @Autowired
    private FriendRepository friendDao;

    /**
     * This method created friendship between two emails provided
     *
     * NOTE : Emails should have been verified before calling this method
     *
     * @param emails List of emails. Length of List should exactly be 2
     * @return
     * @throws RepeatedArgumentException if both the item in the list are same
     * @throws IllegalArgumentException if either of the email is null, or blank
     * @throws FriendAlreadyExistException if friendship already exist in the database
     */
    public boolean createFriendShip(final List<String> emails) throws RepeatedArgumentException, IllegalArgumentException, FriendAlreadyExistException {
        if(emails == null || emails.size() != FRIEND_EMAIL_LENGTH){
            throw new IllegalArgumentException("Parameters are not according to contract");
        }
        String email1Processed = emails.get(0).toLowerCase().trim();
        String email2Processed = emails.get(1).toLowerCase().trim();

        if(email1Processed.equals(email2Processed)){
            throw new RepeatedArgumentException(emails.get(0), emails.get(1));
        }

        try{
            friendDao.saveFriendShip(email1Processed, email2Processed);
        } catch(DataIntegrityViolationException e){
            throw new FriendAlreadyExistException();
        }
        return true;
    }

    /**
     * Return the list of friends for a particular email
     *
     * NOTE : Email should have been verified before calling this method
     *
     * @param requestEmail the email of the person whose friends are required
     * @return List of email(Strings) or Blank List if there are no friends
     */
    public List<String> getFriends(String requestEmail){
        if(requestEmail == null || requestEmail.length() == 0){
            throw new IllegalArgumentException("Email Cannot be empty");
        }
        return friendDao.getFriends(requestEmail);
    }

    /**
     * Return the list of mutual friends between two people
     *
     * NOTE : Email should have been verified before calling this method
     *
     * @param emails the email of the person whose friends are required
     * @return List of email(Strings) or Blank List if there are no mutual friends
     */
    public List<String> getMutualFriends(List<String> emails){
        if(emails == null || emails.size() != FRIEND_EMAIL_LENGTH){
            throw new IllegalArgumentException("Parameters are not according to contract");
        }
        String email1Processed = emails.get(0).toLowerCase().trim();
        String email2Processed = emails.get(1).toLowerCase().trim();

        if(email1Processed.equals(email2Processed)){
            throw new RepeatedArgumentException(emails.get(0), emails.get(1));
        }

        return friendDao.getMutualFriends(email1Processed, email2Processed);
    }
}
