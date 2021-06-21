package sample.dal.dao;

import sample.bll.User;
import sample.dal.DatabaseManager;

import java.util.List;

public class UserDBDao implements Dao<User> {
    @Override
    public List<User> getAll() {
        return DatabaseManager.getInstance().getAllUsers();
    }

    @Override
    public User getById(int id) {
        return DatabaseManager.getInstance().getById(id);
    }

    @Override
    public boolean insert(User item) {
        return DatabaseManager.getInstance().insertUser(item);
    }

    @Override
    public boolean update(User item) {
        return DatabaseManager.getInstance().updateUser(item);
    }

    @Override
    public boolean delete(int id) {
        return DatabaseManager.getInstance().deleteUser(id);
    }
}
