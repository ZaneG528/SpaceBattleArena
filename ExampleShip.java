import java.awt.Color;

import ihs.apcs.spacebattle.*;
import ihs.apcs.spacebattle.commands.*;

public class ExampleShip extends BasicSpaceship {
private Point midpoint = new Point(0.0,0.0);
private int radarTime = 0;
private boolean bool = false;
private Point shipPoint = new Point(0.0,0.0);
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
    public ShipCommand getNextCommand(BasicEnvironment env){
    {
    ObjectStatus ship = env.getShipStatus();
      if(env.getRadar()!=null){
         for(ObjectStatus object : env.getRadar().getByType("Torpedo")){
            if(ship.getPosition().getDistanceTo(object.getPosition())<30){
               return new RaiseShieldsCommand(1.0);
            }
      }
      }
        if((ship.getPosition().getAngleTo(this.midpoint) - ship.getOrientation())!=0){
          return new RotateCommand(ship.getPosition().getAngleTo(this.midpoint) - ship.getOrientation());
      }
      else if((ship.getPosition().getDistanceTo(midpoint))>100&&(ship.getPosition().getAngleTo(this.midpoint) - ship.getOrientation())==0){
         radarTime++;
         if(radarTime>2){
            radarTime =0;
            bool = true;
            return new RadarCommand(4);
         }
        return new ThrustCommand('B',0.6,0.3);
      
            }
      else if((ship.getPosition().getDistanceTo(midpoint))<=75&&ship.getSpeed()>0){
         return new BrakeCommand(0.001);
      }
      return new FireTorpedoCommand('F');
      /*
      ObjectStatus ship = env.getShipStatus();
      if(radarTime>1||bool==false){
     // System.out.println("radar");
         radarTime=0;
         bool=true;
         return new RadarCommand(5);
      }
      else
      radarTime++;
      if(env.getRadar()!=null){
      for(ObjectStatus status : env.getRadar().getByType("Ship")){
         if(status.getPosition().getDistanceTo(ship.getPosition())<ship.getPosition().getDistanceTo(shipPoint)){
         shipPoint = status.getPosition();
         System.out.println(shipPoint);
         }
    }  
      }
      else
      shipPoint = new Point(0.0,0.0);
      if((ship.getPosition().getAngleTo(shipPoint) - ship.getOrientation())>5){
      System.out.println((ship.getPosition().getAngleTo(shipPoint) - ship.getOrientation()));
         return new RotateCommand(ship.getPosition().getAngleTo(shipPoint) - ship.getOrientation());
       }
     else if((ship.getPosition().getAngleTo(shipPoint) - ship.getOrientation())<15&&shipPoint!=new Point(0.0,0.0))
     return new FireTorpedoCommand('F');
     else
     return new IdleCommand(0.1);
     */
     }
     }
}