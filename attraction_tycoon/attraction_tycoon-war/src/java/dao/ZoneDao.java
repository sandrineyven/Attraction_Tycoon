/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Zone;
import java.util.List;

/**
 *
 * @author Sandrine
 */
public interface ZoneDao {
    
    Zone findById( int id ) throws DAOException;
    List<Zone> findAll() throws DAOException; 
}
