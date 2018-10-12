package project;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement(name = "projectlistwrapper")
public class ProjectListWrapper {

    public ProjectListWrapper() {
    }

    private Map<Integer, Project> allProjects = new HashMap<>();
    {
        allProjects.putAll(AllData.getAllProjects());
    }

    @XmlElement(name = "allprojects")
    public Map<Integer, Project> getAllProjects() {
        return allProjects;
    }

    public void setAllProjects(Map<Integer, Project> allProjects) {
        this.allProjects = allProjects;
    }
}
