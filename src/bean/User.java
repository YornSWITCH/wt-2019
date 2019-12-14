package bean;

import java.util.ArrayList;

public class User extends Identifier implements Comparable<User>
{

    private String FirstName;
    private String LastName;
    private String DateOfMembership;
    private String Address;
    private ArrayList<Issue> issues;

    public User(String firstName, String lastName, String dateOfMembership, String address, ArrayList<Issue> issues)
    {
        FirstName = firstName;
        LastName = lastName;
        DateOfMembership = dateOfMembership;
        Address = address;
        this.issues = issues;
    }

    public String getFirstName()
    {
        return FirstName;
    }

    public void setFirstName(String firstName)
    {
        FirstName = firstName;
    }

    public String getLastName()
    {
        return LastName;
    }

    public void setLastName(String lastName)
    {
        LastName = lastName;
    }

    public String getDateOfMembership()
    {
        return DateOfMembership;
    }

    public void setDateOfMembership(String dateOfMembership)
    {
        DateOfMembership = dateOfMembership;
    }

    public String getAddress()
    {
        return Address;
    }

    public void setAddress(String address)
    {
        Address = address;
    }

    public ArrayList<Issue> getIssues()
    {
        return issues;
    }

    public void setIssues(ArrayList<Issue> issues)
    {
        this.issues = issues;
    }

    @Override
    public int compareTo(User o) {
        return LastName.compareTo(o.getLastName());
    }

    @Override
    public String toString() {
        return String.format("id: %-20s  last name: %-20s  first name: %-20s date of membership: %-20s address: %-20s ", this.getId(), LastName, FirstName, DateOfMembership, Address);
    }
}