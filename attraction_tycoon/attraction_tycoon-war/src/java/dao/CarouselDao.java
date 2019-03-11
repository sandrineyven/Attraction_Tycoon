package dao;

import beans.Carousel;
import java.util.List;


public interface CarouselDao {
    void create( Carousel caroussel ) throws DAOException;
    Carousel findById( int id ) throws DAOException;
    List<Carousel> findAll() throws DAOException; 
    void delete(int id) throws DAOException; 
    void update(Carousel caroussel) throws DAOException; 
    List<Carousel> findByZone(long zone) throws DAOException;
    List<Carousel> findBySearch(String search) throws DAOException;
    List<Carousel> findBySearchAdvanced(String search, List<Integer> zones) throws DAOException;
}
