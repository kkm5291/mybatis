package com.example.demomybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * sql에서 반환문에서 반환되는 int 값은 입력된 데이터의 개수가 반환된다
     * 데이터 입력실패시에는 0이 반환될 것
     *
     * @param employee
     * @return
     */
    @PostMapping("")
    public int insert(@RequestBody Employee employee) {
        return employeeMapper.insert(employee);
    }

    /**
     * id 값은 AUTO_INCREMENT로 지정이 되어있는 상황임
     * 즉, id값은 DB에서 INSERT가 발생할 때 생기는 id값이기 때문에 만약 INSERT 문을 아래와 같이 Company객체로 반환값을 준다면
     * id 값은 없는 상황으로 데이터가 추가되게 된다. (db에서는 id값이 있는것 처럼 보이지만, 정작 해당 company객체의 id값은 설정되지 않은것임)
     * 근데 나는 왜 들어가지?????? 뭐야?
     */
    @PostMapping("/noId")
    public Employee insertNoId(@RequestBody Employee employee) {
        employeeMapper.insert(employee);
        return employee;
    }


    @GetMapping("")
    public List<Employee> getAll() {
        return employeeMapper.getAll();
    }


//    @GetMapping("/getNoId")
//    public Employee getNoId() {
//        return employeeMapper.getNoId();
//    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable("id") int id) {
        return employeeMapper.getById(id);
    }
}

