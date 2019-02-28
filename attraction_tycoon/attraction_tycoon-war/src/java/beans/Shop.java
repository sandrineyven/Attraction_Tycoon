package beans;

import java.io.Serializable;

/**
 *
 * @author Sandrine
 */
public class Shop implements Serializable {
    
    private Long id;
    
    private String name;
    
    private String type;
    
   // private Zone zone;

    public Shop() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public Zone getZone() {
//        return zone;
//    }
//
//    public void setZone(Zone zone) {
//        this.zone = zone;
//    }

    
}
