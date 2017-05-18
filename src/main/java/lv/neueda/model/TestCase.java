package lv.neueda.model;

import javax.persistence.*;

@Entity
public class TestCase {

    @Id
    private String uuid;
    private String requirement;
    private String component;
    private String title;

    public TestCase(String uuid, String requirement, String component, String title) {
        this.uuid = uuid;
        this.requirement = requirement;
        this.component = component;
        this.title = title;
    }

    public TestCase() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
