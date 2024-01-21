package bean;

public class User {
    private  String userId;
    private  String name;
    private  String password;

    private Integer age;
    private  Integer gender;

    public User() {
    }

    public User( String name, String password, Integer age, Integer gender) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
