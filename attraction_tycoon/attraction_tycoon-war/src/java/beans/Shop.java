package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Shop implements Serializable {
    
    private Long id;
    
    private String name;
    
    private String type;
    
    private int zone;
    
    List<Staff> staffs = new ArrayList();

    public Shop() {}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}
    
    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    public List<Staff> getStaffs() {return staffs;}

    public void setStaffs(List<Staff> staffs) {this.staffs = staffs;}

    public int getZone() {return zone;}

    public void setZone(int zone) {this.zone = zone;}
    
}
