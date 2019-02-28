/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import beans.User;
import java.util.List;

public interface UserDao {
    void create( User utilisateur ) throws DAOException;
    User findByEmail( String email ) throws DAOException;
    List<User> findAll() throws DAOException;
    
}