/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.User;
import static dao.DaoTools.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gigabyte
 */
public class UserDaoImpl implements UserDao {

    private static final String SQL_SELECT_WITH_EMAIL = "SELECT id_user, email, login, password FROM Utilisateur WHERE email = ?";
    private static final String SQL_INSERT = "INSERT INTO Utilisateur (email, password, login) VALUES (?, ?, ?)";
    private DAOFactory daoFactory;

    public UserDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     *
     * @param user
     * @return void
     * @throws IllegalArgumentException
     * @throws DAOException Add user to the current database
     */
    @Override
    public void create(User utilisateur) throws IllegalArgumentException, DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, utilisateur.getEmail(), utilisateur.getPassword(), utilisateur.getLogin());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if (statut == 0) {
                throw new DAOException("Echec to create user.");
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
                utilisateur.setId(valeursAutoGenerees.getLong(1));
            } else {
                throw new DAOException("Echec to create user in db.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
        }
    }

    /**
     *
     * @param string mail
     * @return User
     * @throws DAOException Add user to the current database Search an User
     * thanks to his mail adress
     */
    @Override
    public User find(String email) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User utilisateur = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_WITH_EMAIL, false, email);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (resultSet.next()) {
                utilisateur = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return utilisateur;
    }

    /*
 * map user
     */
    private static User map(ResultSet resultSet) throws SQLException {
        User utilisateur = new User();
        utilisateur.setId(resultSet.getLong("id_user"));
        utilisateur.setEmail(resultSet.getString("email"));
        utilisateur.setPassword(resultSet.getString("password"));
        utilisateur.setLogin(resultSet.getString("login"));
        return utilisateur;
    }

}
