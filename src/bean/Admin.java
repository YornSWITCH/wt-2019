package bean;

public  class Admin extends Identifier implements Comparable<Admin>
{
    private String FirstName;
    private String LastName;
    private String PhoneNumber;

    public Admin(String firstName, String lastName, String phoneNumber) {
        FirstName = firstName;
        LastName = lastName;
        PhoneNumber = phoneNumber;
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

    public String getPhoneNumber()
    {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        PhoneNumber = phoneNumber;
    }

    @Override
    public int compareTo(Admin o) {
        return LastName.compareTo(o.getLastName());
    }

    @Override
    public String toString() {
        return String.format("id: %-20s  last name: %-20s  first name: %-20s phone: %-20s", this.getId(), LastName, FirstName, PhoneNumber);
    }
}