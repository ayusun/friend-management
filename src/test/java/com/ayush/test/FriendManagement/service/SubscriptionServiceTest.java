package com.ayush.test.FriendManagement.service;

import com.ayush.FriendManagement.RepeatedArgumentException;
import com.ayush.FriendManagement.exceptions.FriendAlreadyExistException;
import com.ayush.FriendManagement.exceptions.SubscriptionAlreadyExistException;
import com.ayush.FriendManagement.repository.FriendRepository;
import com.ayush.FriendManagement.repository.SubscriptionRepository;
import com.ayush.FriendManagement.service.FriendService;
import com.ayush.FriendManagement.service.SubscriptionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class SubscriptionServiceTest {

    @Mock
    private SubscriptionRepository subscriptionDao;

    @InjectMocks
    private SubscriptionService serviceUnderTest;

    @Test(expected = RepeatedArgumentException.class)
    public void testCreateSubscriptionWithTwoSameNames(){
        String email1 = "abc@example.com";
        String email2 = "ABC@example.com";
        serviceUnderTest.createSubscription(email1, email2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateSubscriptionWithNullEmail(){
        serviceUnderTest.createSubscription(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateSubscriptionWithBlankTargetEmail(){
        serviceUnderTest.createSubscription("abc@example.com", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateSubscriptionWithRequestorEmail(){
        serviceUnderTest.createSubscription("abc@example.com", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateSubscriptionWithTargetEmail(){
        serviceUnderTest.createSubscription(null,"abc@example.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateSubscriptionWithBlankRequestEmail(){
        serviceUnderTest.createSubscription("","abc@example.com");
    }

    @Test
    public void createSubscriptionWithValidData(){
        doNothing().when(subscriptionDao).saveSubscription(anyString(), anyString());
        String email1 = "abc@example.com";
        String email2 = "def@example.com";
        boolean response = serviceUnderTest.createSubscription(email1, email2);
        assertThat(response, is(equalTo(true)));
    }

    @Test(expected = SubscriptionAlreadyExistException.class)
    public void createSubscriptionWithValidDataAlreadyExisting(){
        doThrow(DataIntegrityViolationException.class).when(subscriptionDao).saveSubscription(anyString(), anyString());
        String email1 = "abc@example.com";
        String email2 = "def@example.com";
        boolean response = serviceUnderTest.createSubscription(email1, email2);
    }

}
