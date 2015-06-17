/**
 * Created by wesley on 2015-06-10.
 */
public class Player {
    public String summonerID ;
    public int age ;
    public String name ;
    public String nationality ;
    public double csPerMin ;
    public double goldPerMin ;
    public double kDA;
    public String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String returnID() {
        return summonerID ;
    }
    public void setID(String id) {
        summonerID = id;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int returnAge() {
        return age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String returnName() {
        return name ;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public String returnNationality() {
        return nationality ;
    }
    public void setCsPerMin(double cpg) {
        csPerMin = cpg;
    }
    public double returnCsPerGame() {
        return csPerMin ;
    }
    public void setGPM(double gpm) {goldPerMin = gpm;}
    public double returnGPM() {
        return goldPerMin ;
    }
    public void setKDA(double kda) {kDA = kda;}
    public double returnKDA() {
        return kDA ;
    }

}
