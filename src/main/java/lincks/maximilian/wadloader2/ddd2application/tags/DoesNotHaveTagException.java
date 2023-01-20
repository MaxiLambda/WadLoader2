package lincks.maximilian.wadloader2.ddd2application.tags;

public class DoesNotHaveTagException extends RuntimeException{
    public DoesNotHaveTagException(String message) {
        super(message);
    }
}
