package dao;

import bean.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers()throws DaoException;
    void addUsers(List<User> users)throws DaoException;
}
