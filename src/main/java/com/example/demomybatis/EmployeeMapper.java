package com.example.demomybatis;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Insert("INSERT INTO employee(company_id, employee_name, employee_address) VALUES(#{employee.companyId}, #{employee.name}, #{employee.address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(@Param("employee") Employee employee);

    /**
     * 자바에서 설정한 Company 객체의 name과 address와 db에서의 company_name, company_address를 매핑해줘야 한다. (이름이 다르기 때문)
     */
    @Select("SELECT * FROM employee")
    @Results(id="EmployeeMap", value = {
            @Result(property = "name", column = "employee_name"),
            @Result(property = "address", column = "employee_address")
    })
    List<Employee> getAll();

//    @Select("select id from mybatis_db.company where company_name='APPLE'")
//    Company getNoId();

    @Select("select * from employee where id=#{id}")
    @ResultMap("EmployeeMap")
    Employee getById(@Param("id") int id);

    @Select("SELECT * FROM employee WHERE company_id = #{companyId}")
    List<Employee> getByCompanyId(@Param("companyId") int companyId);
}
