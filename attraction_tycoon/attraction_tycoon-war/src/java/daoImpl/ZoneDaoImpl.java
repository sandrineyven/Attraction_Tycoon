package daoImpl;

import beans.Zone;
import dao.DAOException;
import dao.ZoneDao;
import static dao.DaoTools.fermeturesSilencieuses;
import static dao.DaoTools.initialisationRequetePreparee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ZoneDaoImpl implements ZoneDao {

    private static final String SQL_SELECT_WITH_ID = "SELECT id_zone, name FROM zone WHERE id_zone = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM zone";
    private final DAOFactory daoFactory;

    public ZoneDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Zone findById(int id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Zone zone = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_WITH_ID, false, id);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (resultSet.next()) {
                zone = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return zone;
    }

    @Override
    public List<Zone> findAll() throws DAOException {
       Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Zone> zones = new ArrayList<>();

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while (resultSet.next()) {
                Zone zone = map(resultSet);
                if (zone != null) {
                    zones.add(zone);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return zones;
    }

    private static Zone map(ResultSet resultSet) throws SQLException {
        Zone shop = new Zone();
        shop.setId(resultSet.getLong("id_zone"));
        shop.setName(resultSet.getString("name"));
        return shop;
    }

}
