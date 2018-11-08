package models;

public class UserBadge {
    private User user;
    private String badge;

    public void setUser(User user) {
        this.user = user;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public User getUser() {
        return user;
    }

    public String getBadge() {
        return badge;
    }

    public UserBadge(User user, String badge) {
        this.user = user;
        this.badge = badge;
    }
}
