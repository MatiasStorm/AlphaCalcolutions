package easyon.alphacalcolutions.model.exception;

public class CreateUserHasTaskException extends Exception{
    public CreateUserHasTaskException(String msg){
        super(msg);
    }

    public CreateUserHasTaskException(){
        this("Could not assign users to task");
    }
}
