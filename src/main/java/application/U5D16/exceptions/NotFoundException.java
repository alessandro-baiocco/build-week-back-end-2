package application.U5D16.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(int id){
        super("Elemento con id " + id + " non trovato!");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
