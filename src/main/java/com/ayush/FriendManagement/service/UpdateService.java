package com.ayush.FriendManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UpdateService {

    @Autowired
    private BlockService blockService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private FriendService friendService;

    private static final String pattern ="(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b" +
            "\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-zA-Z0-9]" +
            "(?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])" +
            "|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-zA-Z0-9-]*" +
            "[a-zA-Z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c" +
            "\\x0e-\\x7f])+)\\])";
    private static final Pattern emailPattern;

    static {
        emailPattern = Pattern.compile(pattern);
    }

    public List<String> getRecipients(String sender, String text) throws IllegalArgumentException{
        if(text == null || text.length() == 0 || sender == null || sender.length() == 0){
            throw new IllegalArgumentException("Parameters are not according to contact");
        }
        sender = sender.trim().toLowerCase();
        List<String> mentions = getEmailMentions(text);
        List<String> friends = friendService.getFriends(sender);
        List<String> subscribers = subscriptionService.getSubscriber(sender);
        List<String> blockers = blockService.getBlockers(sender);

        Set<String> recipients = new HashSet<>();
        recipients.addAll(friends);
        recipients.addAll(subscribers);
        recipients.addAll(mentions);

        recipients.removeAll(blockers);

        return new ArrayList<>(recipients);
    }

    /**
     * Get List of mentions from the text.
     * If no mentions are there. It returns blank Array
     * @param text
     * @return
     */
    private List<String> getEmailMentions(String text){
        Matcher matcher = emailPattern.matcher(text);
        List<String> mentions = new ArrayList<>();
        while(matcher.find()){
            mentions.add(matcher.group());
        }
        return mentions;
    }
}
