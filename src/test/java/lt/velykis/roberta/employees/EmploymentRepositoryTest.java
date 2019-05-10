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
public class EmploymentRepositoryTest {

    @Autowired
    private EmploymentRepository repository;
    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee1;

    @Before
    public void setUp() {
        repository.deleteAll();
        employeeRepository.deleteAll();

        employee1 = new Employee(null, "Tadas", "Kavaliauskas", LocalDate.of(1989, 1, 12));
        employeeRepository.insert(employee1);
    }

    @Test
    public void findAll_empty() {
        List<Employment> employments = repository.findAll();
        assertThat(employments).isNotNull();
        assertThat(employments).isEmpty();
    }

    @Test
    public void findAll_single() {

        Employment employment1 = new Employment(null, employee1.getId(), LocalDate.of(2010, 12, 11), LocalDate.of(2012, 11, 11), "Sanitex", new BigDecimal("900"));
        repository.insert(employment1);
        assertThat(employment1.getId()).isNotNull();

        List<Employment> employments = repository.findAll();
        assertThat(employments).containsExactly(employment1);
    }

    @Test
    public void findSingle() {

        Employment employment1 = new Employment(null, employee1.getId(), LocalDate.of(2010, 12, 11), LocalDate.of(2012, 11, 11), "Sanitex", new BigDecimal("900"));
        repository.insert(employment1);
        assertThat(employment1.getId()).isNotNull();

        Employment employment2 = repository.find(employment1.getId());
        assertThat(employment2).isEqualTo(employment1);
    }

    @Test
    public void update() {

        Employment employment1 = new Employment(null, employee1.getId(), LocalDate.of(2014, 10, 11), LocalDate.of(2016, 11, 11), "Senukai", new BigDecimal("1000"));
        repository.insert(employment1);

        Employment updatedHistory = new Employment(employment1.getId(), employee1.getId(), LocalDate.of(2014, 10, 11), LocalDate.of(2016, 11, 11), "Maxima", new BigDecimal("1000"));
        repository.update(updatedHistory);

        Employment employment2 = repository.find(updatedHistory.getId());
        assertThat(employment2).isEqualTo(updatedHistory);
    }

    @Test
    public void delete() {

        Employment employment = new Employment(null, employee1.getId(), LocalDate.of(2014, 10, 11), LocalDate.of(2016, 11, 11), "Senukai", new BigDecimal("1000"));
        repository.insert(employment);

        repository.delete(employment.getId());

        List<Employment> employments = repository.findAll();
        assertThat(employments).isEmpty();
    }
}
