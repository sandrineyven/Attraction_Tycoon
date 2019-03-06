/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Shop;
import java.util.List;

/**
 *
 * @author Sandrine
 */
public interface ShopDao {
    void create( Shop shop ) throws DAOException;
    Shop findById( int id ) throws DAOException;
    List<Shop> findAll() throws DAOException; 
    void delete(int id) throws DAOException; 
    void update(Shop shop) throws DAOException; 
}
