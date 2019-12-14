package dao.DBDao;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import bean.*;
import dao.IssueDao;
import dao.DaoException;

import java.sql.*;
import java.util.List;
import java.util.TimeZone;

/**
 * Bd Issues data source manager class
 */
public class DBIssueDao implements IssueDao {

    public static final Logger LOG = Logger.getLogger(IssueDao.class.getName());

    @Override
    public List<Issue> getAllIssues()throws DaoException  {
        return null;
    }

    @Override
    public void addIssues(List<Issue> issues)throws DaoException {
        Connection con = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/wt2019?serverTimezone=" + TimeZone.getDefault().getID(),"root","z10012000z");
            System.out.println("Connected successfully");
            for(Issue issue: issues) {
                if(issue instanceof Newspaper) {
                    String selectSql = "Select * from newspapers where (id = ?)";
                    PreparedStatement selectPs = con.prepareStatement(selectSql);
                    selectPs.setInt(1,issue.getId());
                    ResultSet resSet =  selectPs.executeQuery();
                    if(!resSet.next()) {
                        String sql = "INSERT INTO newspapers(id,name,page_count,author,genre) VALUES(?,?,?,?,?)";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setInt(1, issue.getId());
                        ps.setString(2, issue.getName());
                        ps.setInt(3, issue.getPageCount());
                        ps.setString(4, issue.getAuthor());
                        ps.setString(5, ((Newspaper) issue).getGenre());
                        ps.execute();
                    }else
                    {

                    }
                }else if(issue instanceof Magazine) {
                    String selectSql = "Select * from magazines where (id = ?)";
                    PreparedStatement selectPs = con.prepareStatement(selectSql);
                    selectPs.setInt(1,issue.getId());
                    ResultSet resSet =  selectPs.executeQuery();
                    if(!resSet.next()) {
                        String sql = "INSERT INTO magazines(id,name,page_count,author,genre,type) VALUES(?,?,?,?,?,?)";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setInt(1, issue.getId());
                        ps.setString(2, issue.getName());
                        ps.setInt(3, issue.getPageCount());
                        ps.setString(4, issue.getAuthor());
                        ps.setString(5, ((Magazine) issue).getGenre());
                        ps.setString(6, ((Magazine) issue).getType());
                        ps.execute();
                    }else {

                    }
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
