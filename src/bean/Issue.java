package bean;

public abstract class Issue extends Identifier implements Comparable<Issue>
{
    private String Name;
    private int PageCount;
    private String Author;

    public Issue(String name, int pageCount, String author)
    {
        Name = name;
        PageCount = pageCount;
        Author = author;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public int getPageCount()
    {
        return PageCount;
    }

    public void setPageCount(int pageCount)
    {
        PageCount = pageCount;
    }

    public String getAuthor()
    {
        return Author;
    }

    public void setAuthor(String author)
    {
        Author = author;
    }

    @Override
    public int compareTo(Issue o) {
        return Name.compareTo(o.getName());
    }
}
