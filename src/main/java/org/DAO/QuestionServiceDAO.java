package org.DAO;

import org.DTO.Question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionServiceDAO implements QuestionService {

    @Override
    public int deleteQuestion(int id) {
        String query="delete from Question_info where question_id=?";
        try {
            PreparedStatement pstm=QuestionService.getConnection().prepareStatement(query);
            pstm.setInt(1,id);
            return pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public int addQuestion(Question question) {
        String query="insert into Question_info(question,option1,option2,option3,answer) values(?,?,?,?,?)";

        try {
            PreparedStatement psmt=QuestionService.getConnection().prepareStatement(query);
            psmt.setString(1,question.getQuestion());
            psmt.setString(2,question.getQption1());
            psmt.setString(3,question.getQption2());
            psmt.setString(4,question.getQption3());
            psmt.setString(5,question.getAnswer());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return 0;
    }

    @Override
    public String getQuestionById(int id) {
        String query="select question from Question_info where question_id=?";
        try {
            PreparedStatement pstmt=QuestionService.getConnection().prepareStatement(query);
            pstmt.setInt(1,id);
            ResultSet resultSet= pstmt.executeQuery();

            resultSet.next();
            String ques=resultSet.getNString(1);
            return ques;
        } catch (SQLException e) {
            System.err.println("Question Not found");
        }
        return null;
    }

    @Override
    public int updateQuestion(int id,String ques) {
        String query="Update Question_info set question=? where question_id=?";
        try {
            PreparedStatement pstmt=QuestionService.getConnection().prepareStatement(query);
            pstmt.setString(1,ques);
            pstmt.setInt(2,id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return 0;
    }

    @Override
    public int updateOption(Question question, int id) {
        String query="update Question_info set option1=?,option2=?,option3=?,Answer=? where question_id=?";
        try {
            PreparedStatement pstmt=QuestionService.getConnection().prepareStatement(query);

            pstmt.setString(1,question.getQption1());
            pstmt.setString(2,question.getQption2());
            pstmt.setString(3,question.getQption3());
            pstmt.setString(4,question.getAnswer());
            pstmt.setInt(5,id);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return 0;
    }

    @Override
    public List<Question> displayAllQues() {
        String query="select * from Question_info";
        List<Question> questionList=new ArrayList<>();
        try {
            Statement statement=QuestionService.getConnection().createStatement();
            ResultSet rs=statement.executeQuery(query);
            while (rs.next()){
                Question question=new Question(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                question.setQuestionId(rs.getInt(1));
                questionList.add(question);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return questionList;
    }

    @Override
    public String getPass() {
        String query="select * from password";
        String pass="";
        try {
          Statement statement= QuestionService.getConnection().createStatement();
          ResultSet resultSet=statement.executeQuery(query);
          resultSet.next();
          pass=resultSet.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pass;
    }

}
