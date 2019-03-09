package beans;

import java.io.Serializable;
import java.util.List;

public class Zone implements Serializable {
    
    private long id;
    
    private String name;
    
    private List<Shop> shopList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
