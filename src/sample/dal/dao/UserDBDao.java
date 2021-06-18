package sample.dal.dao;

import sample.dal.DatabaseManager;

import java.util.List;

public class UserDBDao implements dao{
    @Override
    public List getAll() {
        return DatabaseManager.getInstance().getAllUsers();
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public boolean insert(Object item) {
        return false;
    }

    @Override
    public boolean update(Object item) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
