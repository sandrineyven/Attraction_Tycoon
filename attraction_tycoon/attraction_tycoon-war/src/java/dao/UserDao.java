/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import beans.User;

public interface UserDao {
    void create( User utilisateur ) throws DAOException;
    User find( String email ) throws DAOException;
}