package com.ayush.test.FriendManagement.service;

import com.ayush.FriendManagement.RepeatedFriendNameException;
import com.ayush.FriendManagement.exceptions.FriendAlreadyExistException;
import com.ayush.FriendManagement.repository.FriendRepository;
import com.ayush.FriendManagement.service.FriendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;


@RunWith(MockitoJUnitRunner.class)
public class FriendServiceTest {

    @Mock
    private FriendRepository friendDao;

    @InjectMocks
    private FriendService serviceUnderTest;

    @Test(expected = RepeatedFriendNameException.class)
    public void testCreateFriendShipWithTwoSameNames(){
        String email1 = "abc@example.com";
        String email2 = "ABC@example.com";
        serviceUnderTest.createFriendShip(Arrays.asList(email1, email2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFriendShipWithNullEmail(){
        serviceUnderTest.createFriendShip(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFriendShipWithOneEmail(){
        serviceUnderTest.createFriendShip(Arrays.asList("abc@example.com"));
    }

    @Test
    public void testCreateFriendShipWithValidData(){
        doNothing().when(friendDao).saveFriendShip(anyString(), anyString());
        String email1 = "abc@example.com";
        String email2 = "def@example.com";
        boolean response = serviceUnderTest.createFriendShip(Arrays.asList(email1, email2));
        assertThat(response, is(equalTo(true)));
    }

    @Test(expected = FriendAlreadyExistException.class)
    public void testCreateFriendShipWithValidDataAlreadyExist(){
        doThrow(DataIntegrityViolationException.class).when(friendDao).saveFriendShip(anyString(), anyString());
        String email1 = "abc@example.com";
        String email2 = "def@example.com";
        boolean response = serviceUnderTest.createFriendShip(Arrays.asList(email1, email2));
    }
}
