package lt.velykis.roberta.employees;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
//@DataJdbcTest
@Transactional
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    @Before
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void findAll_empty() {
        List<Employee> employees = repository.findAll();
        assertThat(employees).isNotNull();
        assertThat(employees).isEmpty();
    }

    @Test
    public void findAll_single() {
        Employee employee1 = new Employee(null, "Tadas", "Kavaliauskas", LocalDate.of(1989, 1, 12));
        repository.insert(employee1);
        assertThat(employee1.getId()).isNotNull();

        List<Employee> employees = repository.findAll();
        assertThat(employees).isNotNull();
        assertThat(employees).containsExactly(employee1);
    }

    @Test
    public void findSingle() {
        Employee employee1 = new Employee(null, "Tadas", "Kavaliauskas", LocalDate.of(1989, 1, 12));
        repository.insert(employee1);
        assertThat(employee1.getId()).isNotNull();

        Employee found = repository.find(employee1.getId());
        assertThat(found).isEqualTo(employee1);
    }

    @Test
    public void update() {
        Employee employee = new Employee(null, "Tadas", "Kavaliauskas", LocalDate.of(1989, 1, 12));
        repository.insert(employee);

        Employee updatedEmployee = new Employee(employee.getId(), "Tadas1", "Kavaliauskas1", LocalDate.of(1989, 1, 12));
        repository.update(updatedEmployee);

        Employee found = repository.find(employee.getId());
        assertThat(found).isEqualTo(updatedEmployee);
    }

    @Test
    public void delete() {
        Employee employee = new Employee(null, "Justas", "Varnauskas", LocalDate.of(1989, 2, 12));
        repository.insert(employee);

        repository.delete(employee.getId());

        List<Employee> employees = repository.findAll();
        assertThat(employees).isEmpty();
    }
}
