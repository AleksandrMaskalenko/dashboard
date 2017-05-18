package lv.neueda.controller;

import lv.neueda.model.Event;
import lv.neueda.model.TestCase;
import lv.neueda.service.EventService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


public class EventControllerTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    private List<Event> eventList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        TestCase testCase = new TestCase();

        eventList.add(new Event("uuid1", "time1", "event1", testCase));
        eventList.add(new Event("uuid2", "time2", "event2", testCase));
        eventList.add(new Event("uuid3", "time3", "event3", testCase));
    }


    @Test
    public void getAllSong() throws Exception {
        when(eventService.eventList()).thenReturn(eventList);

        List<Event> allEvent = eventController.getAllEvent();

        Assert.assertEquals(3, allEvent.size());
    }

}