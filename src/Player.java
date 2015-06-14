/**
 * Created by wesley on 2015-06-10.
 */
public class Player {
    public static String summonerID ;
    public static int age ;
    public static String name ;
    public static String nationality ;
    public static float csPerMin ;
    public static float goldPerMin ;
    public static float kDA;
    public static String role;

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        Player.role = role;
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
    public void setCsPerMin(float cpg) {
        csPerMin = cpg;
    }
    public float returnCsPerGame() {
        return csPerMin ;
    }
    public void setGPM(float gpm) {goldPerMin = gpm;}
    public float returnGPM() {
        return goldPerMin ;
    }
    public void setKDA(float kda) {kDA = kda;}
    public float returnKDA() {
        return kDA ;
    }

}
