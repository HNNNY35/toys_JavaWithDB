import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Surveys {
    public void Survey(Statement statement) {
            // 설문 시작
            System.out.println("설문시작");
            // 이름, 휴대폰 입력 받기
            System.out.print("이름 : ");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.next();
            System.out.print("휴대폰 번호 : ");
            String phone_number = scanner.next();

            Commons commons = new Commons();
            String strDate = commons.getGeneratorID();
            // UID는 입력 받는 부분이 아니기 때문에 만들어줘야 함
            String query = "INSERT INTO users_list(USERS_UID, PHONE, NAME) VALUES ('"+strDate+"', '"+phone_number+"', '"+name+"')";

            try {
                statement.execute(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // 설문과 답항 내용 출력
            query = "SELECT *  FROM questions_list ORDER BY ORDERS";
            try {
                ResultSet resultSet =  statement.executeQuery(query);
                while(resultSet.next()) {
                    System.out.print(resultSet.getInt("ORDERS") + ". ");
                    System.out.println(resultSet.getString("QUESTIONS"));

                    String uid = resultSet.getString("QUESTIONS_UID");

                    // 설문 문항에 맞는 설문 답항 출력
                    query = "SELECT example_list.EXAMPLE_UID, example_list.EXAMPLE, example_list.ORDERS " + 
                    "FROM answers INNER JOIN example_list ON answers.EXAMPLE_UID = example_list.EXAMPLE_UID " + 
                    "WHERE QUESTIONS_UID = '"+uid+"' ORDER BY ORDERS";

                    ResultSet resultSet2 = statement.executeQuery(query);
                    ArrayList arrayList = new ArrayList<>();
                    while(resultSet2.next()) {
                        System.out.print("(" + resultSet2.getInt("ORDERS") + ")");
                        System.out.println(resultSet2.getString("EXAMPLE"));
                        arrayList.add(resultSet2)
                    }
                    // 설문자 답 받기
                    System.out.print("응답 번호 : ");
                    String answer = scanner.next();
                    // 설문자에게 받는 답은 1,2,3,4,5 같은 숫자 한자리임 survey에 넣기 위해선 EXAMPLE_UID로 변환해줘야 함
                    // 위의 arraylist에 설문자에게 받는 답을 index로 
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }



    }
}
