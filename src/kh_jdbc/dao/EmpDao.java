package kh_jdbc.dao;

import kh_jdbc.Common.Common;
import kh_jdbc.vo.EmpVo;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//DAO : Data Access Object, 데이터 베이스에 접근해 데이터 조회/수정
// 여기서 CRUD기능 다 구현 -> vo파일로
//(DML과 유사한 기능, DDL은(테이블 만들기)는 못함)
public class EmpDao {

    Connection conn = null;// DB 연결시 사용
    Statement stmt = null; // 쿼리문 날리기(수행하기) 위한 것
    PreparedStatement pstmt = null; // 쿼리문 날리기(수행하기) 위한 것-상속해서 하는 것, 속도가 빠름-이게 더 유리
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);


    public List<EmpVo> empSelect() {
        List<EmpVo> list = new ArrayList<>(); // 2:22강의
        try {
            conn = Common.getConnection(); // 아까 깔았던 거에서 가져온 것
            stmt = conn.createStatement(); // 간단한거 할때는 creatStatement 아니면 prepared..
            String sql = "SELECT * FROM EMP";
            rs = stmt.executeQuery(sql); //executeQuery: 셀렉트문 날릴때 사용. execute update:
            //emp테이블의 행개수만큼 받아옴


            while(rs.next()){ // 결과에서 읽을 내용이 있으면 TRUE.print에 넣는 순간 읽혀버림 다음결과가 바뀜( 테이블 행 개수만큼 순회)
                int empNo = rs.getInt("EMPNO");//columlabel
                String name = rs.getString("ENAME");
                String job = rs.getString("JOB");
                int mgr = rs.getInt("MGR");
                Date date = rs.getDate("HIREDATE");
                BigDecimal sal = rs.getBigDecimal("SAL");
                BigDecimal comm = rs.getBigDecimal("COMM");
                int deptNo = rs.getInt("DEPTNO");
                list.add(new EmpVo(empNo, name, job, mgr, date, sal, comm, deptNo));

            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        }catch (Exception e) {
            e.printStackTrace(); // 호출. 역으로 들어감. 부하가 많이 걸려서 잘 쓰지 않음

        }
        return list;

    }
    public void empInsert(){
        System.out.println("사원정보 입력 : ");

        System.out.print("사원번호 : ");
        int no = sc.nextInt();
        System.out.print("이름 : ");
        String name = sc.next();
        System.out.print("직책 : " );
        String job = sc.next();
        System.out.print("상관 : ");
        int mgr = sc.nextInt();
        System.out.print("입사일 : ");
        String date = sc.next();
        System.out.print("급여 : ");
        BigDecimal sal = sc.nextBigDecimal();
        System.out.print("성과금 : ");
        BigDecimal comm = sc.nextBigDecimal();
        System.out.print("부서번호 : ");
        int deptNo = sc.nextInt();

        String sql = "INSERT INTO EMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) VALUES(?,?,?,?,?,?,?,?)";
        try{
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql); // 미리 만들어두기
            pstmt.setInt(1,no);
            pstmt.setString(2, name);
            pstmt.setString(3, job);
            pstmt.setInt(4, mgr);
            pstmt.setString(5, date);
            pstmt.setBigDecimal(6,sal);
            pstmt.setBigDecimal(7,comm);
            pstmt.setInt(8,deptNo);
            int rst = pstmt.executeUpdate();//실행결과가 정수값으로 반환됨. 영향받은 행의 개수가 반환됨(변경,삭제된 것 등)
        } catch (Exception e){
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }

    public void empSelectPrint(List<EmpVo>list){
        for(EmpVo e : list) {
            System.out.print(e.getEmpNo() + " ");
            System.out.print(e.getName() + " ");
            System.out.print(e.getJob() + " ");
            System.out.print(e.getMgr() + " ");
            System.out.print(e.getDate() + " ");
            System.out.print(e.getSal() + " ");
            System.out.print(e.getComm() + " ");
            System.out.print(e.getDeptNo() + " ");
            System.out.println();
        }
    }

}

