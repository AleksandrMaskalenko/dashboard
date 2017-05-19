package lv.neueda.DAO;

import lv.neueda.model.Event;
import lv.neueda.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCaseDao extends JpaRepository<TestCase, String> {


}
