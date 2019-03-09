package beans;

import java.io.Serializable;


public class User implements Serializable{
  
    private String password;
    private String login;
    private String email;
    private Long id;
    
    public User(){};

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}    
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}    
}
