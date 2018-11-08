package models;

import models.Point;

public class User {
    private String username;
    private int id;
    private String email;
    private Point point;

    public User() {
    }

    public User(String username, int id, String email, Point point) {
        this.username = username;
        this.id = id;
        this.email = email;
        this.setPoint(point);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
