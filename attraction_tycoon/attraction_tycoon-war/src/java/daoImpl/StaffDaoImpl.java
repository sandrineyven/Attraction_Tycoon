/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImpl;

import beans.Staff;
import dao.DAOException;
import dao.StaffDao;
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
public class StaffDaoImpl implements StaffDao {

    private static final String SQL_SELECT_ALL = "SELECT * FROM staff";
    private static final String SQL_DELETE = "DELETE FROM staff WHERE id_staff = ?";
    private static final String SQL_UPDATE = "UPDATE staff SET name = ?, type = ?, salary = ?, labor_hours = ? WHERE id_staff = ?";
    private static final String SQL_SELECT_WITH_ID = "SELECT id_staff, id_user, name, type, salary, labor_hours,status FROM staff WHERE id_staff = ?";
    private static final String SQL_SELECT_WITH_SEARCH = "SELECT * FROM staff WHERE name LIKE ";
    private static final String SQL_INSERT = "INSERT INTO staff (id_user, name, type, salary, labor_hours, status) VALUES (?, ?, ?, ?, ?, ?)";
    private final DAOFactory daoFactory;

    public StaffDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(Staff staff) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, staff.getId_user(), staff.getName(), staff.getType(), staff.getSalary(), staff.getHours(), staff.getStatus());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if (statut == 0) {
                throw new DAOException("Echec to create staff.");
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
                staff.setId(valeursAutoGenerees.getLong(1));
            } else {
                throw new DAOException("Echec to create staff in db.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
        }
    }

    @Override
    public Staff findById(int id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Staff staff = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_WITH_ID, false, id);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (resultSet.next()) {
                staff = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return staff;
    }

    @Override
    public List<Staff> findAll() throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Staff> staffs = new ArrayList<>();

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */

            while (resultSet.next()) {
                Staff staff = map(resultSet);
                if (staff != null) {
                    staffs.add(staff);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return staffs;
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
                throw new DAOException("Echec to delete staff.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
        }
    }

    @Override
    public void update(Staff staff) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE, true, staff.getName(), staff.getType(), staff.getSalary(), staff.getHours(), staff.getId());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Echec to update staff.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
        }
    }

    private static Staff map(ResultSet resultSet) throws SQLException {
        Staff staff = new Staff();
        staff.setId(resultSet.getLong("id_staff"));
        staff.setId_user(resultSet.getLong("id_user"));
        staff.setName(resultSet.getString("name"));
        staff.setType(resultSet.getString("type"));
        staff.setSalary(resultSet.getDouble("salary"));
        staff.setHours(resultSet.getInt("labor_hours"));
        staff.setStatus(resultSet.getString("status"));
        return staff;
    }

    @Override
    public List<Staff> findBySearch(String search) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Staff> staffs = new ArrayList<>();

        String query = SQL_SELECT_WITH_SEARCH + "'%" + search + "%' OR type LIKE " + "'%" + search + "%'";
        System.out.println(query);
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, query, false);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Staff staff = map(resultSet);
                if (staff != null) {
                    staffs.add(staff);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return staffs;
    }
}
