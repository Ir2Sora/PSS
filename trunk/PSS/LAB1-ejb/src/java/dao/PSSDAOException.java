package dao;

public class PSSDAOException extends Exception {

    public PSSDAOException() {
    }

    public PSSDAOException(String msg) {
        super(msg);
    }
    
    public PSSDAOException(Throwable t){
        super(t);
    }
}
