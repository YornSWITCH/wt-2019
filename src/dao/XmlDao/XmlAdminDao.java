package dao.XmlDao;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import bean.Admin;
import dao.IssueDao;
import dao.DaoException;
import dao.AdminDao;
import dao.XmlDao.SaxParsers.AdminSaxHandler;

import java.util.List;

public class XmlAdminDao implements AdminDao {

    public static final Logger LOG = Logger.getLogger(AdminDao.class.getName());

    @Override
    public List<Admin> getAllAdmins()throws DaoException {
        List<Admin> librarians = null;
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            AdminSaxHandler handler = new AdminSaxHandler();
            reader.setContentHandler(handler);
            reader.parse("D:\\Study\\University\\5 term\\WT\\XmlToBDMigration\\src\\XmlStorage\\librarians.xml");
            reader.setFeature("http://xml.org/sax/features/validation",true);
            reader.setFeature("http://xml.org/sax/features/namespaces",true);
            reader.setFeature("http://xml.org/sax/features/string-interning",true);
            reader.setFeature("http://apache.org/xml/features/validation/schema",false);
            librarians = handler.getAdminList();
        }catch (Exception e) {
            LOG.log(Level.ERROR,e.getMessage());
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
            throw new DaoException(e);
        }
        return librarians;
    }

    @Override
    public void addAdmins(List<Admin> librarians)throws DaoException {

    }
}
