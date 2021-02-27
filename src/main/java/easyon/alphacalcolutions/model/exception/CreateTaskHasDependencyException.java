package easyon.alphacalcolutions.model.exception;

public class CreateTaskHasDependencyException extends Exception{
    public CreateTaskHasDependencyException(String msg){
        super(msg);
    }

    public CreateTaskHasDependencyException(){
        this("Could not create task dependencies");
    }
}
