import java.awt.Color;

import ihs.apcs.spacebattle.*;
import ihs.apcs.spacebattle.commands.*;

public class ExampleShip extends BasicSpaceship {
private Point midpoint = new Point(0.0,0.0);
private boolean bool = false;
    public static void main(String[] args)
    {
        TextClient.run("10.56.98.121", new ExampleShip());
    }

    @Override
    public RegistrationData registerShip(int numImages, int worldWidth, int worldHeight)
    {
        midpoint = new Point(worldWidth/2, worldHeight/2);
        return new RegistrationData("John Joseph the 4573rd", new Color(255, 0, 127), 3);
    }

    @Override
    public ShipCommand getNextCommand(BasicEnvironment env)
    {
    ObjectStatus ship = env.getShipStatus();
     System.out.println((ship.getPosition().getAngleTo(this.midpoint) - ship.getOrientation()));
    
      if((ship.getPosition().getAngleTo(this.midpoint) - ship.getOrientation())!=0){
          return new RotateCommand(ship.getPosition().getAngleTo(this.midpoint) - ship.getOrientation());
      }
      else if((ship.getPosition().getDistanceTo(midpoint))>75){
         return new ThrustCommand('B',0.3,0.3);
      }
      else if((ship.getPosition().getDistanceTo(midpoint))<=150){
         return new BrakeCommand(0.12);
      }
      else
      return new IdleCommand(0.1);

      //if(centerx)
        //return new IdleCommand(0.1);
        
    }
}