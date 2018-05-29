package com.ayush.test.FriendManagement.service;

import com.ayush.FriendManagement.service.BlockService;
import com.ayush.FriendManagement.service.FriendService;
import com.ayush.FriendManagement.service.SubscriptionService;
import com.ayush.FriendManagement.service.UpdateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdatesServiceTest {

    @Mock
    private BlockService blockService;

    @Mock
    private SubscriptionService subscriptionService;

    @Mock
    private FriendService friendService;

    @InjectMocks
    private UpdateService serviceUnderTest;

    @Test(expected = IllegalArgumentException.class)
    public void testNullText(){
        String text = null;
        String sender = "abc@example.com";
        serviceUnderTest.getRecipients(sender, text);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBlankText(){
        String text = "";
        String sender = "abc@example.com";
        serviceUnderTest.getRecipients(sender, text);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBlankSender(){
        String text = "HI";
        String sender = "";
        serviceUnderTest.getRecipients(sender, text);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullSender(){
        String text = "HI";
        String sender = null;
        serviceUnderTest.getRecipients(sender, text);
    }

    @Test
    public void testRecepients(){
        String sender = "abc@example.com";
        List<String> friends = Arrays.asList("friend1@example.com", "friend2@example.com", "friend3@example.com");
        List<String> subscribers = Arrays.asList("sub1@example.com", "sub2@example.com");
        List<String> blockers = Arrays.asList("friend1@example.com");

        String text = "Hello World! kate@example.com test@example.com";

        when(friendService.getFriends(sender)).thenReturn(friends);
        when(subscriptionService.getSubscriber(sender)).thenReturn(subscribers);
        when(blockService.getBlockers(sender)).thenReturn(blockers);

        List<String> acualRecepients = serviceUnderTest.getRecipients(sender, text);

        assertThat(acualRecepients.size(), is(equalTo(6)));


    }
}
