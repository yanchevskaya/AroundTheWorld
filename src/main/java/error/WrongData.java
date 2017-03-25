package error;

/**
 * Exeption which is frown when user adds incorrect data during registration ot initialization
 * @author Ali Yan
 * @version 1.0
 */
public class WrongData extends Exception{
private String errorMessage;

     public WrongData(String errorMessage){
         this.errorMessage = errorMessage;
     }


    public String getErrorMessage() {
        return errorMessage;
    }
}
