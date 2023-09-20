package kh_jdbc.Common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Common { //진짜 그대로 써주면 된다.
    final static String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    final static String ORACLE_ID = "SCOTT";
    final static String ORACLE_PW = "TIGER";
    final static String ORACLE_DRV = "oracle.jdbc.driver.OracleDriver";

    public static Connection getConnection() { //자바 sql 커넥션쪽에 걸어야
        Connection conn = null;
        try {
            Class.forName(ORACLE_DRV); // 드라이버 로드
            // 연결 얻기
            conn = DriverManager.getConnection(ORACLE_URL, ORACLE_ID, ORACLE_PW);
            System.out.println("Connection 생성 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
// 오류가 안나는건 드라이버가 제대로 연결되어 있다는 뜻!

    public static void close(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection 해제 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void close(Statement stmt) {
        try {
            if(stmt != null && !stmt.isClosed()) {
                stmt.close();
                System.out.println("Statement 해제 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void close(ResultSet rset) {
        try {
            if(rset != null && !rset.isClosed()) {
                rset.close();
                System.out.println("Result set 해제 성공");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//위의 세가지 import 함
}