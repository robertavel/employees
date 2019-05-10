package lt.velykis.roberta.employees;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper
public interface EmployeeHistoryRepository {

    @Select("SELECT * FROM EmployeeHistory")
    List<EmployeeHistory> findAll();

    @Select("SELECT * FROM EmployeeHistory " +
            "WHERE name LIKE #{nameQuery}")
    List<EmployeeHistory> findByEmployeeName(String nameQuery);

}
