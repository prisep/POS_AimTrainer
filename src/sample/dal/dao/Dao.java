package sample.dal.dao;
import java.util.List;
public interface Dao<T> {
    List<T> getAll();
    T getByUsername(String username);
    boolean insert(T item);
    boolean update(T item);
    boolean delete(int id);
}