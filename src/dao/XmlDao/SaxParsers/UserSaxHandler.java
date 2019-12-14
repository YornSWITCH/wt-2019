package dao.XmlDao.SaxParsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import bean.*;

import java.util.ArrayList;
import java.util.List;

public class UserSaxHandler extends DefaultHandler {
    private List<Issue> issues = new ArrayList<Issue>();
    private Issue issue;
    private List<User> users = new ArrayList<User>();
    private User user;
    private StringBuilder text;

    public List<User> getUsersList()
    {
        return users;
    }

    public void startDocument()
    {

    }

    public void endDocument()
    {

    }

    public void startElement(String uri, String localName, String qName, Attributes attributes){
        text = new StringBuilder();
        if(qName.equals("User"))
        {
            user = new User("","","","",null);
        }
        if(qName.equals("bk:Magazine"))
        {
            issue = new Magazine("",0,"","","");
        }
        if(qName.equals("bk:Newspaper"))
        {
            issue = new Newspaper("",0,"","");
        }
    }

    public void characters(char[] buffer, int start, int length){
        text.append(buffer,start,length);
    }

    public void  endElement(String uri,String localName,String qName)
    {
        if(issue != null) {
            IssuesTagName tagName = IssuesTagName.valueOf(qName.toUpperCase().replace("BK:", ""));
            switch (tagName) {
                case ID:
                    issue.setId(Integer.parseInt(text.toString()));
                    break;
                case NAME:
                    issue.setName(text.toString());
                    break;
                case TYPE:
                    ((Magazine) issue).setType(text.toString());
                    break;
                case ISSUES:
                    user.setIssues(new ArrayList<>(issues));
                    issues = new ArrayList<Issue>();
                    break;
                case GENRE:
                    if (issue instanceof Newspaper)
                        ((Newspaper) issue).setGenre(text.toString());
                    if (issue instanceof Magazine)
                        ((Magazine) issue).setGenre(text.toString());
                    break;
                case AUTHOR:
                    issue.setAuthor(text.toString());
                    break;
                case PAGECOUNT:
                    issue.setPageCount(Integer.parseInt(text.toString()));
                    break;
                case MAGAZINE:
                case NEWSPAPER:
                    issues.add(issue);
                    issue = null;
                    break;

            }
        }
        else {
            UserTagName userTagName = UserTagName.valueOf(qName.toUpperCase().replace("-", "_"));
            switch (userTagName) {
                case ISSUES:
                    user.setIssues(new ArrayList<>(issues));
                    issues = new ArrayList<Issue>();
                    break;
                case FIRSTNAME:
                    user.setFirstName(text.toString());
                    break;
                case LASTNAME:
                    user.setLastName(text.toString());
                    break;
                case ID:
                    user.setId(Integer.parseInt(text.toString()));
                    break;
                case USER:
                    users.add(user);
                    user = null;
                    break;
                case USERS:
                    break;
                case ADDRESS:
                    user.setAddress(text.toString());
                    break;
                case DATEOFMEMBERSHIP:
                    user.setDateOfMembership(text.toString());
                    break;

            }
        }
    }

    public void warning(SAXParseException exception)
    {

    }

    public void error(SAXParseException exception){

    }

    public void fatalError(SAXParseException exception){

    }
}