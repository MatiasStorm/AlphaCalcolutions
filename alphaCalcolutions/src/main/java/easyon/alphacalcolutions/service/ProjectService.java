package easyon.alphacalcolutions.service;

import easyon.alphacalcolutions.data.DataFacade;
import easyon.alphacalcolutions.model.Project;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProjectService {

    private final DataFacade dataFacade;

    public ProjectService(DataFacade dataFacade) {
        this.dataFacade = dataFacade;
    }

    public void createProject(Project project){
        dataFacade.createProject(project);
    }

//    public ArrayList<Project> getProjectList() {return dataFacade.getProjectList();}
//
//    public Project getProject(int projectId) {return dataFacade.getProject(projectId);}

}
