package controllers;

import entity.Direction;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class DirectionController implements Serializable{

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
