package lt.velykis.roberta.employees;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper
public interface EmploymentRepository {

    @Select("SELECT * FROM Employment")
    List<Employment> findAll();

    @Select("SELECT * FROM Employment WHERE id = #{id}")
    Employment find(Long id);

    @Insert("INSERT INTO Employment(employeeId, dateFrom, dateUntil, companyName, salary) " +
            "VALUES(#{employeeId}, #{dateFrom}, #{dateUntil}, #{companyName}, #{salary})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Employment history);

    @Update("UPDATE Employment " +
            "SET employeeId = #{employeeId}, dateFrom = #{dateFrom}, dateUntil = #{dateUntil}, companyName = #{companyName}, salary = #{salary} " +
            "WHERE id = #{id}")
    void update(Employment history);

    @Delete("DELETE FROM Employment WHERE id=#{id}")
    void delete(Long id);

    @Delete("DELETE FROM Employment")
    void deleteAll();

}
