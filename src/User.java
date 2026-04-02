
/**
 *
 * @author ejjaz
 */
public class User {
    protected String userID;
    protected String username;
    protected String password;
    protected String userGender;
    protected String userRole;
    
    public User(String userID, String username, String password, 
            String userGender, String userRole) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.userGender = userGender;
        this.userRole = userRole;
    }
    
    public String getUserID(){
        return userID;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getUserGender(){
        return userGender;
    }
    
    public String getUserRole(){
        return userRole;
    }
    
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }
    
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    
    public boolean isValidLogin(String loginUsername, String loginPassword) {
        return this.username.equals(loginUsername) 
                && this.password.equals(loginPassword);
    }

    public String toString(){
        return userID + "|" + username + "|" + password + "|" + userGender 
                + "|" + userRole;
    }
}
