
/**
 *
 * @author ejjaz
 */
public class User implements FileHandlerInterface {
    protected String userID;
    protected String username;
    protected String password;
    protected String userRole;
    
    public User(String userID, String username, String password, 
            String userRole) {
        this.userID = userID;
        this.username = username;
        this.password = password;
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
    
    public String getUserRole(){
        return userRole;
    }
    
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }
    
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    
    public boolean isValidLogin(String loginID, String loginUsername, String loginPassword) {
        return this.userID.equals(loginID) && this.username.equals(loginUsername) 
                && this.password.equals(loginPassword);
    }

    @Override
    public String toString(){
        return userID + "|" + username + "|" + password + "|" + userRole;
    }

    @Override
    public String getFileKey() {
        return userID;
    }
}
