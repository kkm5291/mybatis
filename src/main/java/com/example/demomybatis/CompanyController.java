package com.example.demomybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyService companyService;

    /**
     * sql에서 반환문에서 반환되는 int 값은 입력된 데이터의 개수가 반환된다
     * 데이터 입력실패시에는 0이 반환될 것
     *
     * @param company
     * @return
     */
    @PostMapping("")
    public Company insert(@RequestBody Company company) throws Exception {
        companyService.add(company);
        return company;
    }

    /**
     * id 값은 AUTO_INCREMENT로 지정이 되어있는 상황임
     * 즉, id값은 DB에서 INSERT가 발생할 때 생기는 id값이기 때문에 만약 INSERT 문을 아래와 같이 Company객체로 반환값을 준다면
     * id 값은 없는 상황으로 데이터가 추가되게 된다. (db에서는 id값이 있는것 처럼 보이지만, 정작 해당 company객체의 id값은 설정되지 않은것임)
     * 근데 나는 왜 들어가지?????? 뭐야?
     */
    @PostMapping("/noId")
    public Company insertNoId(@RequestBody Company company) {
//        companyMapper.insert(company);
        return company;
    }


    @GetMapping("")
    public List<Company> getAll() {
        return companyMapper.getAll();
    }


    @GetMapping("/getNoId")
    public Company getNoId() {
        return companyMapper.getNoId();
    }

    @GetMapping("/{id}")
    public Company getById(@PathVariable("id") int id) {
        return companyMapper.getById(id);
    }
}
