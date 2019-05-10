package lt.velykis.roberta.employees;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EmployeeHistoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmploymentRepository employmentRepository;
    @Autowired
    private EmployeeHistoryRepository historyRepository;

    @Before
    public void beforeEachMethod() {
        employmentRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Test
    public void findAllHistories() {

        Employee employee1 = new Employee(null, "Tadas", "Kavaliauskas", LocalDate.of(1989, 1, 12));
        employeeRepository.insert(employee1);
        Employee employee2 = new Employee(null, "Andrius", "Jankauskas", LocalDate.of(1990, 1, 12));
        employeeRepository.insert(employee2);

        Employment history1 = new Employment(null, employee1.getId(), LocalDate.of(2014, 10, 11), LocalDate.of(2016, 11, 11), "Maxima", new BigDecimal("1000"));
        employmentRepository.insert(history1);
        Employment history2 = new Employment(null, employee2.getId(), LocalDate.of(2014, 10, 11), LocalDate.of(2016, 11, 11), "Senukai", new BigDecimal("1000"));
        employmentRepository.insert(history2);

        EmployeeHistory employeeHistory2 = new EmployeeHistory("Tadas", "Kavaliauskas", "Maxima");
        EmployeeHistory employeeHistory3 = new EmployeeHistory("Andrius", "Jankauskas", "Senukai");

        List<EmployeeHistory> employeeHistory = historyRepository.findAll();

        assertThat(employeeHistory).containsExactly(employeeHistory2, employeeHistory3);
    }

    @Test
    public void findHistories_filterByName() {

        Employee employee1 = new Employee(null, "Tadas", "Kavaliauskas", LocalDate.of(1989, 1, 12));
        employeeRepository.insert(employee1);
        Employee employee2 = new Employee(null, "Andrius", "Kazlauskas", LocalDate.of(1990, 1, 12));
        employeeRepository.insert(employee2);
        Employee employee3 = new Employee(null, "Antanas", "Petrulis", LocalDate.of(1990, 3, 12));
        employeeRepository.insert(employee3);

        Employment history1 = new Employment(null, employee1.getId(), LocalDate.of(2014, 10, 11), LocalDate.of(2016, 11, 11), "Maxima", new BigDecimal("1000"));
        employmentRepository.insert(history1);
        Employment history2 = new Employment(null, employee2.getId(), LocalDate.of(2014, 10, 11), LocalDate.of(2016, 11, 11), "Senukai", new BigDecimal("1000"));
        employmentRepository.insert(history2);
        Employment history3 = new Employment(null, employee1.getId(), LocalDate.of(2016, 10, 11), LocalDate.of(2016, 11, 11), "IKI", new BigDecimal("1030"));
        employmentRepository.insert(history3);
        Employment history4 = new Employment(null, employee3.getId(), LocalDate.of(2017, 10, 11), LocalDate.of(2018, 11, 11), "Rimi", new BigDecimal("1040"));
        employmentRepository.insert(history4);

        EmployeeHistory employeeHistoryExp1 = new EmployeeHistory("Tadas", "Kavaliauskas", "Maxima");
        EmployeeHistory employeeHistoryExp2 = new EmployeeHistory("Tadas", "Kavaliauskas", "IKI");
        EmployeeHistory employeeHistoryExp3 = new EmployeeHistory("Andrius", "Kazlauskas", "Senukai");
        List<EmployeeHistory> employeeHistory = historyRepository.findByEmployeeSurname("Ka%");

        assertThat(employeeHistory).containsExactlyInAnyOrder(employeeHistoryExp1, employeeHistoryExp2, employeeHistoryExp3);
    }
}
