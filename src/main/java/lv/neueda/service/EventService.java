package lv.neueda.service;

import lv.neueda.DAO.EventDao;
import lv.neueda.DAO.TestCaseDao;
import lv.neueda.model.Event;
import lv.neueda.model.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EventService {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private TestCaseDao testCaseDao;

    public void addEvent(Event event) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        testCaseDao.save(event.getTestCase());

        Date formattedDate = null;
        try {
            formattedDate = format.parse(event.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        event.setDateTime(formattedDate);
        eventDao.save(event);
    }

    public List<Event> getAllEvent() {

        List<Event> eventList = eventDao.findAll();

        eventList.sort((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()));

        return eventList;
    }

    public List<Integer> getEventData() {
        Date currentDate = new Date();

        List<Integer> eventList = new ArrayList<>();

        String[] array = {"started", "success", "error", "failure"};

        List<Event> eventList1 = eventDao.findAll();

            for (String anArray : array) {

                int count = (int) eventList1
                        .stream()
                        .filter((e) -> e.getEvent().equals(anArray))
                        .filter((e) -> (currentDate.getTime() - e.getDateTime().getTime()) > 3600000)
                        .count();

                eventList.add(count);
            }

        return eventList;
    }


    public Set<String> getRequirementLabel() {
        List<TestCase> testCaseList = testCaseDao.findAll();
        Set<String> requirement = new HashSet<>();

        for (TestCase testCase:testCaseList) {
            requirement.add(testCase.getRequirement());
        }

        return requirement;
    }

    public List<List<Integer>> getRequirementData() {
        Set<String> req = getRequirementLabel();

        List<List<Integer>> listList = new ArrayList<>();

        List<Integer> startedInt = new ArrayList<>();
        List<Integer> successInt = new ArrayList<>();
        List<Integer> errorInt = new ArrayList<>();
        List<Integer> failureInt = new ArrayList<>();

        for (String st: req) {
            List<Event> eventList = eventDao.findEventByRequirement(st);

            List<Event> started = new ArrayList<>();
            List<Event> success = new ArrayList<>();
            List<Event> error = new ArrayList<>();
            List<Event> failure = new ArrayList<>();

            for (Event e:eventList) {

                switch (e.getEvent()) {
                    case "started":started.add(e);
                        break;
                    case "success":success.add(e);
                        break;
                    case "error":error.add(e);
                        break;
                    case "failure":failure.add(e);
                        break;
                }
            }

            startedInt.add(started.size());
            successInt.add(success.size());
            errorInt.add(error.size());
            failureInt.add(failure.size());

        }

        listList.add(startedInt);
        listList.add(successInt);
        listList.add(errorInt);
        listList.add(failureInt);

        return listList;

    }

    public Set<String> getComponentLabel() {
        List<TestCase> testCaseList = testCaseDao.findAll();

        Set<String> component = new HashSet<>();

        for (TestCase testCase:testCaseList) {
            component.add(testCase.getComponent());
        }

        return component;
    }

    public List<List<Integer>> getComponentData() {
        Set<String> comp = getComponentLabel();

        List<List<Integer>> list = new ArrayList<>();
        List<Integer> componentList = new ArrayList<>();

        for (String st: comp) {
            List<Event> cases = eventDao.findEventByComponent(st);
            componentList.add(cases.size());
        }
        list.add(componentList);

        return list;

    }

}
