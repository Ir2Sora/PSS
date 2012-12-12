package controllers;

import java.io.Serializable;
import dao.*;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class DirectionController implements Serializable{
//    @EJB private DAORemote dao;
    private Direction direction;
    
    public DirectionController(){
    }

    public DirectionController(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }   

}
