package dao.XmlDao.SaxParsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import bean.*;

import java.util.ArrayList;
import java.util.List;

public class IssueSaxHandler extends DefaultHandler {
    private List<Issue> issues = new ArrayList<Issue>();
    private Issue issue;
    private StringBuilder text;

    public List<Issue> getIssuesList()
    {
        return issues;
    }

    public void startDocument()
    {

    }

    public void endDocument()
    {

    }

    public void startElement(String uri, String localName, String qName, Attributes attributes){
        text = new StringBuilder();
        if(qName.equals("Magazine"))
        {
            issue = new Magazine("",0,"","","");
        }
        if(qName.equals("Newspaper"))
        {
            issue = new Newspaper("",0,"","");
        }
    }

    public void characters(char[] buffer, int start, int length){
        text.append(buffer,start,length);
    }

    public void  endElement(String uri,String localName,String qName)
    {
        IssuesTagName tagName = IssuesTagName.valueOf(qName.toUpperCase().replace("-","_"));
        switch(tagName){
            case ID:
                issue.setId(Integer.parseInt(text.toString()));
                break;
            case NAME:
                issue.setName(text.toString());
                break;
            case TYPE:
                ((Magazine)issue).setType(text.toString());
                break;
            case ISSUES:
                break;
            case GENRE:
                if(issue instanceof Newspaper)
                    ((Newspaper)issue).setGenre(text.toString());
                if(issue instanceof Magazine)
                    ((Magazine)issue).setGenre(text.toString());
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

    public void warning(SAXParseException exception)
    {

    }

    public void error(SAXParseException exception){

    }

    public void fatalError(SAXParseException exception){

    }
}