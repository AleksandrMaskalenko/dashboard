package lv.neueda.DAO;

import lv.neueda.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseDao extends JpaRepository<TestCase, String> {
}
