/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sandrine
 */
public class UserDao {

    private final DAOFactory daoFactory;

    public UserDao(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public UserDao() {
        this.daoFactory = null;
    }

    public void find() throws RuntimeException, SQLException {

        Connection conn = null;
        try {
            Class driver_class = Class.forName("com.mysql.cj.jdbc.Driver");
            Driver driver = (Driver) driver_class.newInstance();
            DriverManager.registerDriver(driver);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String requete = "SELECT * FROM USER";
        try {
            /* Récupération d'une connexion depuis la Factory */
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "supermassive1206");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(requete);
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while (resultSet.next()) {
                System.out.println(resultSet.getString("login"));
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        //--

    }

}
