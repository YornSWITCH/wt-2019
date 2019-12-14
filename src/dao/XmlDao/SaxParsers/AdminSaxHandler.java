package dao.XmlDao.SaxParsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import bean.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminSaxHandler extends DefaultHandler {
    private List<Admin> admins = new ArrayList<Admin>();
    private Admin admin;
    private StringBuilder text;

    public List<Admin> getAdminList()
    {
        return admins;
    }

    public void startDocument()
    {

    }

    public void endDocument()
    {

    }

    public void startElement(String uri, String localName, String qName, Attributes attributes){
        text = new StringBuilder();
        if(qName.equals("Admin"))
        {
            admin = new Admin("","","");
        }
    }

    public void characters(char[] buffer, int start, int length){
        text.append(buffer,start,length);
    }

    public void  endElement(String uri,String localName,String qName)
    {
        AdminTagName tagName = AdminTagName.valueOf(qName.toUpperCase().replace("-","_"));
        switch(tagName){
            case ID:
                admin.setId(Integer.parseInt(text.toString()));
                break;
            case PHONENUMBER:
                admin.setPhoneNumber(text.toString());
                break;
            case LASTNAME:
                admin.setLastName(text.toString());
                break;
            case FIRSTNAME:
                admin.setFirstName(text.toString());
                break;
            case ADMIN:
                admins.add(admin);
                admin = null;
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