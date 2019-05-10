package lt.velykis.roberta.employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeHistory {

    private String name;
    private String surname;
    private String companyName;

}
