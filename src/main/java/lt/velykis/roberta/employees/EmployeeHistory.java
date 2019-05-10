package lt.velykis.roberta.employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeHistory {

    private Long employeeId;
    private String name;
    private String surname;
    private LocalDate dob;
    private LocalDate dateFrom;
    private LocalDate dateUntil;
    private String companyName;
    private BigDecimal salary;

}
