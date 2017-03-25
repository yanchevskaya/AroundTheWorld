package error;

public class WrongData extends Exception{
private String errorMessage;

     public WrongData(String errorMessage){
         this.errorMessage = errorMessage;
     }


    public String getErrorMessage() {
        return errorMessage;
    }
}
