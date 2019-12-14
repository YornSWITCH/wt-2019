package dao.DBDao;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import bean.Admin;
import dao.IssueDao;
import dao.DaoException;
import dao.AdminDao;

import java.sql.*;
import java.util.List;
import java.util.TimeZone;

/**
 * Bd admins data source manager class
 */
public class DBAdminDao implements AdminDao {

    public static final Logger LOG = Logger.getLogger(AdminDao.class.getName());

    @Override
    public List<Admin> getAllAdmins()throws DaoException {
        return null;
    }

    @Override
    public void addAdmins(List<Admin> admins)throws DaoException {

        Connection con = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/wt2019?serverTimezone=" + TimeZone.getDefault().getID(),"root","z10012000z");
            System.out.println("Connected successfully");
            String sql = "INSERT INTO admins(id,first_name,last_name,phone_number) VALUES(?,?,?,?)";
            for(Admin adm: admins) {
                String selectSql = "Select * from admins where (id = ?)";
                PreparedStatement selectPs = con.prepareStatement(selectSql);
                selectPs.setInt(1,adm.getId());
                ResultSet resSet =  selectPs.executeQuery();
                if(!resSet.next()) {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, adm.getId());
                    ps.setString(2, adm.getFirstName());
                    ps.setString(3, adm.getLastName());
                    ps.setString(4, adm.getPhoneNumber());
                    ps.execute();
                }
                else
                {
                    
                }
            }

        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
            LOG.log(Level.ERROR,e.getMessage());
            throw new DaoException(e);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            LOG.log(Level.ERROR,e.getMessage());
            throw new DaoException(e);
        }
        finally {
            try{
                if(con != null){
                    con.close();
                }
            }
            catch (SQLException e){
                LOG.log(Level.ERROR,e.getMessage());
                throw new DaoException(e);
            }
        }
    }
}
