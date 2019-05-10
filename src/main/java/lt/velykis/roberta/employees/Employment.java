package lt.velykis.roberta.employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employment {

    private Long id;
    private Long employeeId;
    private LocalDate dateFrom;
    private LocalDate dateUntil;
    private String companyName;
    private BigDecimal salary;

}
