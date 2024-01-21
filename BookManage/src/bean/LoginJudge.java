package bean;

public class LoginJudge {
    private  Integer Id;
    private  String Name;
    private  Boolean Judge;

    public LoginJudge(Integer id, String name, Boolean judge) {
        Id = id;
        Name = name;
        Judge = judge;
    }

    @Override
    public String toString() {
        return "LoginJudge{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Judge=" + Judge +
                '}';
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Boolean getJudge() {
        return Judge;
    }

    public void setJudge(Boolean judge) {
        Judge = judge;
    }
}
