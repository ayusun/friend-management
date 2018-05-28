package com.ayush.FriendManagement.service;

import com.ayush.FriendManagement.RepeatedFriendNameException;
import com.ayush.FriendManagement.exceptions.FriendAlreadyExistException;
import com.ayush.FriendManagement.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FriendService {

    private static final int FRIEND_EMAIL_LENGTH = 2;
    @Autowired
    private FriendRepository friendDao;

    public boolean createFriendShip(final List<String> emails) throws RepeatedFriendNameException, IllegalArgumentException {
        if(emails == null || emails.size() != FRIEND_EMAIL_LENGTH){
            throw new IllegalArgumentException("Parameters are not according to contract");
        }
        String email1Processed = emails.get(0).toLowerCase().trim();
        String email2Processed = emails.get(1).toLowerCase().trim();

        if(email1Processed.equals(email2Processed)){
            throw new RepeatedFriendNameException(emails.get(0), emails.get(1));
        }

        try{
            friendDao.saveFriendShip(email1Processed, email2Processed);
        } catch(DataIntegrityViolationException e){
            throw new FriendAlreadyExistException();
        }
        return true;
    }

    public List<String> getFriends(String requestEmail){
        if(requestEmail == null || requestEmail.length() == 0){
            throw new IllegalArgumentException("Email Cannot be empty");
        }
        return friendDao.getFriends(requestEmail);

    }
}
