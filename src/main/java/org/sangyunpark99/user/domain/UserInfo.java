package org.sangyunpark99.user.domain;

public class UserInfo { // VO 사용

    private final String name;
    private final String profileImageUrl;

    public UserInfo(String name, String profileImageUrl) {

        if(name == null || name.isEmpty()) { // 유효성 검사
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
