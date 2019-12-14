package dao.XmlDao;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import bean.Issue;
import dao.IssueDao;
import dao.DaoException;
import dao.XmlDao.SaxParsers.IssueSaxHandler;

import java.util.List;

public class XmlIssueDao implements IssueDao {

    public static final Logger LOG = Logger.getLogger(IssueDao.class.getName());

    @Override
    public List<Issue> getAllIssues()throws DaoException {
        List<Issue> books = null;
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            IssueSaxHandler handler = new IssueSaxHandler();
            reader.setContentHandler(handler);
            reader.parse("D:\\Study\\University\\5 term\\WT\\XmlToBDMigration\\src\\XmlStorage\\books.xml");
            reader.setFeature("http://xml.org/sax/features/validation",true);
            reader.setFeature("http://xml.org/sax/features/namespaces",true);
            reader.setFeature("http://xml.org/sax/features/string-interning",true);
            reader.setFeature("http://apache.org/xml/features/validation/schema",false);
            books = handler.getIssuesList();
        }catch (Exception e) {
            LOG.log(Level.ERROR,e.getMessage());
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
            throw new DaoException();

        }
        return books;
    }

    @Override
    public void addIssues(List<Issue> books)throws DaoException {

    }
}
