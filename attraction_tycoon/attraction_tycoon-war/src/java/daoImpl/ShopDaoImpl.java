/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImpl;

import beans.Shop;
import dao.DAOException;
import dao.ShopDao;
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
public class ShopDaoImpl implements ShopDao {

    private static final String SQL_SELECT_WITH_ID = "SELECT id_shop, name, type, id_zone FROM shop WHERE id_shop = ?";
    private static final String SQL_INSERT = "INSERT INTO shop (name, type, id_zone) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM shop";
    private static final String SQL_SELECT_WITH_SEARCH = "SELECT id_shop, name, type, id_zone FROM shop WHERE name LIKE ";
    private static final String SQL_DELETE = "DELETE FROM shop WHERE id_shop = ?";
    private static final String SQL_UPDATE = "UPDATE shop SET name = ?, type = ?, id_zone = ? WHERE id_shop = ?";
     private static final String SQL_SELECT_WITH_ZONE = "SELECT id_shop, name, type, id_zone FROM shop WHERE id_zone = ?";
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
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, shop.getName(), shop.getType(), shop.getZone());
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
     * @param id
     * @return Shop
     * @throws DAOException Add shop to the current database Search an Shop
     * thanks to his mail adress
     */
    @Override
    public Shop findById(int id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Shop shop = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_WITH_ID, false, id);
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
                if (shop != null) {
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

    @Override
    public void delete(int id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_DELETE, true, id);
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Echec to delete shop.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
        }

    }

    /**
     *
     * @param shop
     * @throws IllegalArgumentException
     * @throws DAOException Add shop to the current database
     */
    @Override
    public void update(Shop shop) throws IllegalArgumentException, DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE, false, shop.getName(), shop.getType(), shop.getZone(), shop.getId());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Echec to update shop.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
        }
    }

 
    @Override
    public List<Shop> findByZone(long zone) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Shop> shops = new ArrayList<>();
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_WITH_ZONE, false, zone);
            resultSet = preparedStatement.executeQuery();

            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while (resultSet.next()) {
                Shop shop = map(resultSet);
                
                if (shop != null) {
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

    @Override
    public List<Shop> findBySearch(String search) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Shop> shops = new ArrayList<>();
        
        String query = SQL_SELECT_WITH_SEARCH + "'" + search + "%' OR type LIKE " + "'" + search + "%'";
        System.out.println(query);
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, query, false);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Shop shop = map(resultSet);
                if (shop != null) {
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
}
