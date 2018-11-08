package models;

public class Point {
    private Integer userId;
    private Integer value;

    public Point(Integer userId, Integer value) {
        this.setUserId(userId);
        this.setValue(value);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
