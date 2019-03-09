package dao;

import beans.Shop;
import java.util.List;


public interface ShopDao {
    void create( Shop shop ) throws DAOException;
    Shop findById( int id ) throws DAOException;
    List<Shop> findAll() throws DAOException; 
    void delete(int id) throws DAOException; 
    void update(Shop shop) throws DAOException; 
    List<Shop> findByZone(long zone) throws DAOException;
    List<Shop> findBySearch(String search) throws DAOException;
    List<Shop> findBySearchAdvanced(String search, List<Integer> zones) throws DAOException;
}
