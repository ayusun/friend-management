package com.ayush.FriendManagement.requestVO;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class FriendsVO {

    private List<String> friends;
}
