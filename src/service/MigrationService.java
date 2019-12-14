package service;

import bean.Issue;
import bean.Admin;
import bean.User;
import dao.DBDao.DBIssueDao;
import dao.DBDao.DBAdminDao;
import dao.DBDao.DBUserDao;
import dao.DaoException;
import dao.XmlDao.XmlIssueDao;
import dao.XmlDao.XmlAdminDao;
import dao.XmlDao.XmlUserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

import org.apache.log4j.*;

public class MigrationService {

    public static final Logger LOG = Logger.getLogger( MigrationService.class.getName());

    public static void MigrateAll() throws ServiceException
    {
        try {

            LOG.log(Level.INFO,"Start admins parsing");
            XmlAdminDao adminDao = new XmlAdminDao();
            var admins = adminDao.getAllAdmins();
            DBAdminDao dbLibDao = new DBAdminDao();
            dbLibDao.addAdmins(admins);
            LOG.log(Level.INFO,"End admins parsing");

            LOG.log(Level.INFO,"Begin users parse");
            XmlUserDao userDao = new XmlUserDao();
            var users = userDao.getAllUsers();
            DBUserDao dbUserDao = new DBUserDao();
            dbUserDao.addUsers(users);
            LOG.log(Level.INFO,"End users parsing");

            LOG.log(Level.INFO,"Begin issues parsing");
            XmlIssueDao issueDao = new XmlIssueDao();
            var issues = issueDao.getAllIssues();
            DBIssueDao dbIssueDao = new DBIssueDao();
            dbIssueDao.addIssues(issues);
            LOG.log(Level.INFO,"End issues parsing");
        }
        catch (DaoException e)
        {
            LOG.log(Level.ERROR,e.getMessage());
            throw new ServiceException(e);
        }
    }
}

