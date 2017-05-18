package lv.neueda.model;

import javax.persistence.*;

@Entity
public class Event {

    @Id
    private String uuid;
    private String time;
    private String event;

    @ManyToOne
    @JoinColumn(name = "test_case")
    private TestCase testCase;

    public Event(String uuid, String time, String event, TestCase testCase) {
        this.uuid = uuid;
        this.time = time;
        this.event = event;
        this.testCase = testCase;
    }

    public Event() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }
}
