package com.example.demomybatis;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompanyMapper {

    @Insert("INSERT INTO COMPANY(company_name, company_address) VALUES(#{company.name}, #{company.address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(@Param("company") Company company);

    /**
     * 자바에서 설정한 Company 객체의 name과 address와 db에서의 company_name, company_address를 매핑해줘야 한다. (이름이 다르기 때문)
     */
    @Select("SELECT * FROM company")
    @Results(id="CompanyMap", value = {
            @Result(property = "name", column = "company_name"),
            @Result(property = "address", column = "company_address"),
            @Result(property = "employeeList", column = "id", many = @Many(select = "com.example.demomybatis.EmployeeMapper.getByCompanyId"))
    })
    List<Company> getAll();

    @Select("select id from mybatis_db.company where company_name='APPLE'")
    Company getNoId();

    @Select("select * from company where id=#{id}")
    @ResultMap("CompanyMap")
    Company getById(@Param("id") int id);

}
