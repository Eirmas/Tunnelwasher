import lejos.hardware.motor.*;
import lejos.hardware.lcd.*;

public class Washer {
    public Boolean tracker;
    public Washer()
    {
        this.tracker = false;
        Motor.D.setSpeed(450);  // sett hastighet (toppfart = 900)
        this.washTrigger();
    }
    public void StartWashing(){
        LCD.clear();
        System.out.println("Starter å vaske");
        this.tracker = true;
    }

    public void washTrigger()
    {
        while(tracker == true)
        {
            Motor.D.rotate(45);
            Motor.D.stop();
            Motor.D.rotate(-45);
        }
    }

    public void StopWashing()
    {
        LCD.clear();
        System.out.println("Stanser å vaske");
        this.tracker = false;
    }
}
