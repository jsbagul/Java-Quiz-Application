package org.application;


import org.DAO.QuestionService;
import org.DAO.QuestionServiceDAO;
import org.DTO.Question;

import java.util.List;
import java.util.Scanner;

public class MainApp
{
    static Scanner scanner=new Scanner(System.in);
    static QuestionService questionService=new QuestionServiceDAO();
    public static void main( String[] args )
    {
        System.out.println("===========================================================");
        System.out.println( "Select Mode of Operation" );
        System.out.println("1.Add Question");
        System.out.println("2. Remove Question");
        System.out.println("3 Update Question");
        System.out.println("4. Display All Question");
        System.out.println("5 Display Question by Id");
        System.out.println("6. Take a Test");
        System.out.println("7. Exit");
        int ch=scanner.nextInt();

        if (ch<5){
            System.out.println("Enter Password: ");

            String actualpass=questionService.getPass();
            String password=scanner.next();
            if (!password.equals(actualpass)){
                System.out.println("Wrong Password");
                System.exit(1);
            }
        }
        
        switch (ch){
            case 1:
                    addQuestion();
                    break;
            case 2:
                    removeQestion();
                    break;
            case 3:
                    updateQuestion();
                     break;
            case 4:
                    displayAllQuestion();
                     break;
            case 5:
                    getQuestionById();
                    break;
            case 6:
                    takeATest();
                    break;
            case 7:
                    System.exit(0);
                    break;

            default:
                    System.out.println("Invalid Input");
                    break;
        }
        main(args);
    }

    private static void takeATest()  {
        List<Question> questionList=questionService.displayAllQues();
        int marks=0;
        System.out.println("before you start Test Read following note");
        try {
            Thread.sleep(1500);
            System.out.println("NOTE:Copy the Appropriate Answer option do not copy blankSpaces that makes your Answer Wrong");
            Thread.sleep(4000);

            System.out.println("Ready..");
            Thread.sleep(1000);
            System.out.println("3...");
            Thread.sleep(1000);
            System.out.println("2..");
            Thread.sleep(1000);
            System.out.println("1..");
            Thread.sleep(900);

        }catch (InterruptedException e){
            System.out.println(e);
        }

       String ans= scanner.nextLine();
        for (Question q:questionList) {
            System.out.println("Q "+q.getQuestionId()+". "+q.getQuestion());
            System.out.println("1. "+q.getQption1());
            System.out.println("2. "+q.getQption2());
            System.out.println("3. "+q.getQption3());


             ans=scanner.nextLine();
            if (ans.equals(q.getAnswer())){
                marks+=5;
            }else {
                marks-=2;
            }
        }
        System.out.println("---------------------------");
        System.out.println("Your Score is: "+marks);
        System.out.println("----------------------------");
        System.out.println("Thank You Visit Again");
        System.exit(0);
    }

    private static void displayAllQuestion() {

       List<Question> questions= questionService.displayAllQues();
        for (Question q:questions) {
            System.out.println("Q "+q.getQuestionId()+". "+q.getQuestion());
            System.out.println("1. "+q.getQption1());
            System.out.println("2. "+q.getQption2());
            System.out.println("3. "+q.getQption3());
            System.out.println("-> "+q.getAnswer());
            System.out.println("---------------------------------------------");

        }
    }

    private static void updateQuestion() {
        System.out.println("========================");
        System.out.println("Enter your Option");
        System.out.println("1.Modify question");
        System.out.println("2. Modify option");
        System.out.println("3. Exit");
        int ch=scanner.nextInt();
        switch (ch){
            case 1:
                modifyQuestion();
                break;
            case 2:
                modifyOptions();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid Input");
                break;
        }
        updateQuestion();
    }

    private static void modifyOptions() {

        System.out.println("Enter Question id");
        int id=scanner.nextInt();
    if (questionService.getQuestionById(id)!=null) {
        System.out.println("The Question is => " + questionService.getQuestionById(id));
        System.out.println("Enter updated option 1");
        scanner.nextLine();
        String opt1 = scanner.nextLine();
        System.out.println("Enter updated option 2");
        String opt2 = scanner.nextLine();
        System.out.println("Enter updated option 3");
        String opt3 = scanner.nextLine();
        System.out.println("Enter Updated Answer");
        String ans = scanner.nextLine();

        Question question = new Question(questionService.getQuestionById(id), opt1, opt2, opt3, ans);
        int n = questionService.updateOption(question, id);

        System.out.println(n + " rows affected");
     }
    }

    private static void modifyQuestion() {
        System.out.println("Enter Question id");
        int id=scanner.nextInt();
        if (questionService.getQuestionById(id)!=null) {
            System.out.println("Enter Updated question");
            scanner.nextLine();
            String UpQuestion = scanner.nextLine();


            int n = questionService.updateQuestion(id, UpQuestion);
            System.out.println(n + " question updated");
        }
    }

    public static void getQuestionById(){
        System.out.println("Enter Question id");
        int id=scanner.nextInt();
        String question= questionService.getQuestionById(id);
        if (question!=null)
             System.out.println(question);

    }

    private static void removeQestion() {
        System.out.println("Enter QuestionId to delete Question");
        int id=scanner.nextInt();
       int n= questionService.deleteQuestion(id);
        System.out.println(n+" Question deleted");
    }

    private static void addQuestion() {
        System.out.println("NOTE:You don't need to Provide Question ID");
        System.out.println("Enter Question");
            scanner.nextLine();

        String question=scanner.nextLine();
        System.out.println("Enter Option 1");
        String opt1=scanner.nextLine();
        System.out.println("Enter Option 2");
        String opt2=scanner.nextLine();
        System.out.println("Enter Option 3");
        String opt3=scanner.nextLine();

        System.out.println("Enter Valid Answer");
        String ans=scanner.nextLine();

        Question ques=new Question(question,opt1,opt2,opt3,ans);

        int n=questionService.addQuestion(ques);
        System.out.println(n+" Question added");
    }
}
