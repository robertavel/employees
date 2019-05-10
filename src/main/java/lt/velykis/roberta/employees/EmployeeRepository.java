package lt.velykis.roberta.employees;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface EmployeeRepository {

    @Select("SELECT * FROM Employee")
    List<Employee> findAll();

    @Select("SELECT * FROM Employee WHERE id = #{id}")
    Employee find(Long id);

    @Insert("INSERT INTO Employee(name, surname, dob) VALUES(#{name}, #{surname}, #{dob})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Employee Employee);

    @Update("UPDATE Employee SET name=#{name}, surname=#{surname}, dob=#{dob} WHERE id = #{id}")
    void update(Employee Employee);

    @Delete("DELETE FROM Employee WHERE id=#{id}")
    void delete(Long id);

    @Delete("DELETE FROM Employee")
    void deleteAll();

}
