/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.User;

/**
 *
 * @author Sandrine
 */
public class UserDao {

    private final DAOFactory daoFactory;

    UserDao(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public void create(User user) throws RuntimeException {
        //REQUETE SQL INSERT INTO
    }

}
