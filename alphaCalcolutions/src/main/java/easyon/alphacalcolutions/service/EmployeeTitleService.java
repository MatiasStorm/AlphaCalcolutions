package easyon.alphacalcolutions.service;

import easyon.alphacalcolutions.data.DataFacade;
import easyon.alphacalcolutions.model.EmployeeTitle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmployeeTitleService {

    private final DataFacade dataFacade;

    public EmployeeTitleService(DataFacade dataFacade) {
        this.dataFacade = dataFacade;
    }

    public ArrayList<EmployeeTitle> getEmployeeTitleList(){
        return dataFacade.getEmployeeTitleList();
    }
}
