/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Staff;
import java.util.List;

/**
 *
 * @author Sandrine
 */
public interface StaffDao {
    void create( Staff staff ) throws DAOException;
    Staff findById( int id ) throws DAOException;
    List<Staff> findAll() throws DAOException; 
    void delete(int id) throws DAOException; 
    void update(Staff staff) throws DAOException; 
}
