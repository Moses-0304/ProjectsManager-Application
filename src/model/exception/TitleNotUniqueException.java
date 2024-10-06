package model.exception;
public class TitleNotUniqueException extends RuntimeException{
    public TitleNotUniqueException(){
        super();
    }

    public TitleNotUniqueException(String message){
        super(message);
    }
}
