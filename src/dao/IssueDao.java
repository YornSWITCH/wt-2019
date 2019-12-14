package dao;

import bean.Issue;

import java.util.List;

public interface IssueDao {
    List<Issue> getAllIssues() throws DaoException;
    void addIssues(List<Issue> issues)throws DaoException;
}
