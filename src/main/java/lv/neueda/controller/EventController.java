package lv.neueda.controller;

import lv.neueda.model.Event;
import lv.neueda.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/event/add", method = RequestMethod.POST)
    public void addEvent(@RequestBody Event event){

        eventService.addEvent(event);
    }

    @RequestMapping(value = "/events")
    public List<Event> getAllEvent() {
        return eventService.eventList();
    }

    @RequestMapping(value = "/event/{uuid}")
    public Event getEventById(@PathVariable String uuid) {
        return eventService.getEvent(uuid);
    }

    @RequestMapping(value = "/chartPie")
    public List<Integer> getEventChartPie() {
        return eventService.eventListForPie();
    }

    @RequestMapping(value = "/bar")
    public List<List<Integer>> getEventBar() {
        return eventService.findAllRequirement();
    }

    @RequestMapping(value = "/requirement")
    public Set<String> getRequirement() {
        return eventService.findRequirement();
    }

    @RequestMapping(value = "/componentLabel")
    public Set<String> getComponentLabel() {
        return eventService.getComponentLabel();
    }

    @RequestMapping(value = "/componentData")
    public List<List<Integer>> getComponentData() {
        return eventService.getComponentData();
    }


}