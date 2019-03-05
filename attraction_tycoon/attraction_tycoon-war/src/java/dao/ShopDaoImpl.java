/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Shop;
import static dao.DaoTools.fermeturesSilencieuses;
import static dao.DaoTools.initialisationRequetePreparee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sandrine
 */
public class ShopDaoImpl implements ShopDao{

 private static final String SQL_SELECT_WITH_NAME = "SELECT id_shop, email, login, password FROM shop WHERE name = ?";
    private static final String SQL_INSERT = "INSERT INTO shop (name, type, id_zone) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM shop";
    private final DAOFactory daoFactory;

    public ShopDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     *
     * @param shop
     * @throws IllegalArgumentException
     * @throws DAOException Add shop to the current database
     */
    @Override
    public void create(Shop shop) throws IllegalArgumentException, DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, shop.getName(), shop.getType(),shop.getZone());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if (statut == 0) {
                throw new DAOException("Echec to create shop.");
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
                shop.setId(valeursAutoGenerees.getLong(1));
            } else {
                throw new DAOException("Echec to create shop in db.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
        }
    }

    /**
     *
     * @param name
     * @return Shop
     * @throws DAOException Add shop to the current database Search an Shop
     * thanks to his mail adress
     */
    @Override
    public Shop findByName(String name) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Shop shop = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_WITH_NAME, false, name);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (resultSet.next()) {
                shop = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return shop;
    }
    
 @Override
    public List<Shop> findAll() throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Shop> shops = new ArrayList<>();

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            
            while (resultSet.next()) {
               Shop shop = map(resultSet);
               if(shop != null){
               shops.add(shop);
               }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return shops;
    }


    private static Shop map(ResultSet resultSet) throws SQLException {
        Shop shop = new Shop();
        shop.setId(resultSet.getLong("id_shop"));
        shop.setName(resultSet.getString("name"));
        shop.setType(resultSet.getString("type"));
        shop.setZone(resultSet.getInt("id_zone"));
        return shop;
    }
}
