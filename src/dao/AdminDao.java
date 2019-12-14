package dao;

import bean.Admin;

import java.util.List;

public interface AdminDao {
    List<Admin> getAllAdmins()throws DaoException;
    void addAdmins(List<Admin> admins)throws DaoException;
}