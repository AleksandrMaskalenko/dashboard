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
        return eventService.getAllEvent();
    }

    @RequestMapping(value = "/eventData")
    public List<Integer> getEventData() {
        return eventService.getEventData();
    }

    @RequestMapping(value = "/requirementData")
    public List<List<Integer>> getRequirementData() {
        return eventService.getRequirementData();
    }

    @RequestMapping(value = "/requirementLabel")
    public Set<String> getRequirementLabel() {
        return eventService.getRequirementLabel();
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