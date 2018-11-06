package Models;

public class User {
    private String username;
    private int id;
    private String email;
    private int points;

    public User() {
    }

    public User(String username, int id, String email, int points) {
        this.username = username;
        this.id = id;
        this.email = email;
        this.points = points;
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

    public void setPoints(int points) {
        this.points = points;
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

    public int getPoints() {
        return points;
    }
}
