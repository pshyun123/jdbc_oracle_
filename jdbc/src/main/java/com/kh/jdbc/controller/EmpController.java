package com.kh.jdbc.controller;
import com.kh.jdbc.dao.EmpDAO;
import com.kh.jdbc.vo.EmpVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmpController {
    @GetMapping("/select")
    public String selectEmp(Model model){ //view로 모델을 념겨주는 객체
//        System.out.println("여기는 emp select 호출문입니다.");
//        return "test";
        EmpDAO dao = new EmpDAO();
        List<EmpVO> emps= dao.empSelect();//DB 다녀와서 담아줌
        // 화면에 나타내줌
        model.addAttribute("employees",emps);
        return "thymeleafEx/empSelect";
    }
    @GetMapping("/insert")
    public String insertViewEmp(Model model) { //view로 모델을 념겨주는 객체
        model.addAttribute("employees", new EmpVO()); // 입력 받기 위한 빈 객체 넘겨줌-> view에다가 값 채워서 되돌려주려고 함
        return "thymeleafEx/empInsert";

    }
    @PostMapping("/insert") // DB로 가져옴
    public String insertDBEmp(@ModelAttribute("employees")EmpVO empVO) {
        EmpDAO dao = new EmpDAO();
        dao.empInsert(empVO);
        return "thymeleafEx/empInsertRst";
    }
}
