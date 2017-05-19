package lv.neueda.DAO;

import lv.neueda.model.Event;
import lv.neueda.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventDao extends JpaRepository<Event, String> {

    @Query("SELECT e FROM Event e WHERE e.event LIKE :type")
    List<Event> findEventByType(@Param("type") String type);

    @Query("SELECT e FROM Event e WHERE e.testCase.requirement LIKE :requirement")
    List<Event> findEventByRequirement(@Param("requirement") String requirement);

    @Query("SELECT e FROM Event e WHERE e.testCase.component LIKE :component")
    List<Event> findEventByComponent(@Param("component") String component);
}
