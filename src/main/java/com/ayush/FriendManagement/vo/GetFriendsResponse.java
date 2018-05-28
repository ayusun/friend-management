package com.ayush.FriendManagement.vo;

import lombok.Data;

import java.util.List;

@Data
public class GetFriendsResponse {
    private boolean success;

    private List<String> friends;

    private int count;

    public static GetFriendsResponse createFriendResponse(List<String> friends){
        GetFriendsResponse response = new GetFriendsResponse();
        response.setSuccess(true);
        response.setFriends(friends);
        response.setCount(friends.size());
        return response;
    }
}
