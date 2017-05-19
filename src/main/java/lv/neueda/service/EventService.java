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

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @Autowired
    private EventDao eventDao;

    @Autowired
    private TestCaseDao testCaseDao;

    public void addEvent(Event event) {
        testCaseDao.save(event.getTestCase());
        eventDao.save(event);
    }

    public Event getEvent(String uuid) {
        return eventDao.findOne(uuid);
    }

    public List<Event> eventList() {

        List<Event> eventList = eventDao.findAll();

        eventList.sort((Event o1, Event o2) -> {
            Date formattedDate2 = null;
            Date formattedDate1 = null;

            try {
                formattedDate2 = format.parse(o2.getTime());
                formattedDate1 = format.parse(o1.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return formattedDate2.compareTo(formattedDate1);

        });

        return eventList;
    }

    public List<Integer> eventListForPie() {
        Date currentDate = new Date();

        List<Integer> eventList = new ArrayList<>();

        String[] array = {"started", "success", "error", "failure"};
        try {

            for (String anArray : array) {

                List<Event> eventByType = eventDao.findEventByType(anArray);
                List<Event> newEventByType = new ArrayList<>();

                for (Event event : eventByType) {

                    Date formattedDate = format.parse(event.getTime());

                    if (currentDate.getTime() - formattedDate.getTime() > 3600000) {
                        newEventByType.add(event);
                    }
                }
                eventList.add(newEventByType.size());
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return eventList;
    }


    public Set<String> findRequirement() {
        List<TestCase> testCaseList = testCaseDao.findAll();
        Set<String> requirement = new HashSet<>();

        for (TestCase testCase:testCaseList) {
            requirement.add(testCase.getRequirement());
        }

        return requirement;
    }

    public List<List<Integer>> findAllRequirement() {
        Set<String> req = findRequirement();

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
