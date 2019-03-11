package daoImpl;

import beans.Carousel;
import dao.DAOException;
import static dao.DaoTools.fermeturesSilencieuses;
import static dao.DaoTools.initialisationRequetePreparee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.CarouselDao;
import dao.CarouselDao;

public class CarouselDaoImpl implements CarouselDao {

    private static final String SQL_SELECT_WITH_ID = "SELECT * FROM carousel WHERE id_carousel = ?";
    private static final String SQL_INSERT = "INSERT INTO carousel (name, type, id_zone, duration, capacity_max, state, is_open ) VALUES (?, ?, ?, ?, ?, ?,?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM carousel";
    private static final String SQL_SELECT_WITH_SEARCH = "SELECT * FROM carousel WHERE name LIKE ";
    private static final String SQL_DELETE = "DELETE FROM carousel WHERE id_carousel = ?";
    private static final String SQL_UPDATE = "UPDATE carousel SET name = ?, type = ?, id_zone = ?, duration = ?, capacity_max = ?, state = ?, is_open = ? WHERE id_carousel = ?";
    private static final String SQL_SELECT_WITH_ZONE = "SELECT * FROM carousel WHERE id_zone = ?";
    private final DAOFactory daoFactory;

    public CarouselDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     *
     * @param carousel
     * @throws IllegalArgumentException
     * @throws DAOException Add caroussel to the current database
     */
    @Override
    public void create(Carousel carousel) throws IllegalArgumentException, DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, carousel.getName(), carousel.getType(), carousel.getZone(), carousel.getDuration(), carousel.getCapacity(), carousel.getState(), carousel.isIs_open());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if (statut == 0) {
                throw new DAOException("Echec to create caroussel.");
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
                carousel.setId(valeursAutoGenerees.getLong(1));
            } else {
                throw new DAOException("Echec to create caroussel in db.");
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
     * @return Caroussel
     * @throws DAOException Add caroussel to the current database Search an Caroussel
     * thanks to his mail adress
     */
    @Override
    public Carousel findById(int id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Carousel caroussel = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_WITH_ID, false, id);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (resultSet.next()) {
                caroussel = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return caroussel;
    }

    @Override
    public List<Carousel> findAll() throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Carousel> caroussels = new ArrayList<>();

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */

            while (resultSet.next()) {
                Carousel caroussel = map(resultSet);
                if (caroussel != null) {
                    caroussels.add(caroussel);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return caroussels;
    }

    private static Carousel map(ResultSet resultSet) throws SQLException {
        Carousel caroussel = new Carousel();
        caroussel.setId(resultSet.getLong("id_carousel"));
        caroussel.setName(resultSet.getString("name"));
        caroussel.setType(resultSet.getString("type"));
        caroussel.setZone(resultSet.getInt("id_zone"));
        caroussel.setDuration(resultSet.getTime("duration").getSeconds());
        caroussel.setCapacity(resultSet.getInt("capacity_max"));
        caroussel.setState(resultSet.getString("state"));
        caroussel.setIs_open(true);
        return caroussel;
    }

    @Override
    public void delete(int id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_DELETE, true, id);
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Echec to delete caroussel.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion);
        }

    }

    /**
     *
     * @param carousel
     * @throws IllegalArgumentException
     * @throws DAOException Add caroussel to the current database
     */
    @Override
    public void update(Carousel carousel) throws IllegalArgumentException, DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE, false, carousel.getName(), carousel.getType(), carousel.getZone(), carousel.getDuration(), carousel.getCapacity(), carousel.getState(), carousel.isIs_open(), carousel.getId());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Echec to update caroussel.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion);
        }
    }

    @Override
    public List<Carousel> findByZone(long zone) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Carousel> caroussels = new ArrayList<>();
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_WITH_ZONE, false, zone);
            resultSet = preparedStatement.executeQuery();

            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while (resultSet.next()) {
                Carousel caroussel = map(resultSet);

                if (caroussel != null) {
                    caroussels.add(caroussel);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return caroussels;
    }

    @Override
    public List<Carousel> findBySearch(String search) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Carousel> caroussels = new ArrayList<>();

        String query = SQL_SELECT_WITH_SEARCH + "'%" + search + "%' OR type LIKE " + "'%" + search + "%'";
        System.out.println(query);
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, query, false);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Carousel caroussel = map(resultSet);
                if (caroussel != null) {
                    caroussels.add(caroussel);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return caroussels;
    }

    @Override
    public List<Carousel> findBySearchAdvanced(String search, List<Integer> zones) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Carousel> caroussels = new ArrayList<>();

        String query = SQL_SELECT_ALL + " WHERE ";
        if (!search.trim().isEmpty()) {
            query += "(name LIKE " + "'%" + search + "%' OR type LIKE " + "'%" + search + "%')";
            if (!zones.isEmpty()) {
                query += " AND ";
            }
        }
        if (!zones.isEmpty()) {
            query += "id_zone = " + zones.get(0);
            if (zones.size() > 1) {
                for (int i = 1; i < zones.size(); i++) {
                    query += " OR id_zone = " + zones.get(i);
                }
            }
        }

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, query, false);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Carousel caroussel = map(resultSet);
                if (caroussel != null) {
                    caroussels.add(caroussel);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return caroussels;
    }
}
