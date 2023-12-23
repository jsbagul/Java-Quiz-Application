package org.DAO;

import org.DTO.Question;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface QuestionService {
    public int deleteQuestion(int id) ;
    public int addQuestion(Question question);

   public String getQuestionById(int id);
   public int updateQuestion(int id, String question);
   public int updateOption(Question question, int id);
   public List<Question> displayAllQues();
   public String getPass();

    static Connection getConnection() throws SQLException {
        String url="jdbc:mysql://localhost:3306/Question_DB";
        return DriverManager.getConnection(url,"root","Redminote5@");
    }
}
