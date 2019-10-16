import lejos.hardware.motor.*;
import lejos.hardware.lcd.*;

public class MotorClass {

    public MotorClass()
    {
        Motor.A.setSpeed(450);  // sett hastighet (toppfart = 900)
        Motor.C.setSpeed(450);
    }

    public void hitLeft() throws Exception
    {
        LCD.clear();
        System.out.println("Snu 10 grader");
        Motor.A.rotate(10);
        Motor.C.stop();
        while (Motor.A.isMoving()) Thread.yield();
        this.driveForward();
    }

    public void hitRight() throws Exception
    {
        LCD.clear();
        System.out.println("Snu 10 grader");
        Motor.A.rotate(-10);
        Motor.C.stop();
        while (Motor.A.isMoving()) Thread.yield();
        this.driveForward();
    }

    public void driveForward() throws Exception
    {
        Motor.A.forward();  // Start motor A - kj�r framover
        Motor.C.forward();  // Start motor C - kj�r framover
        Thread.sleep(2000); // Vent i 1000 ms f�r vi g�r videre i koden
    }

    public void turnAround()
    {
        try {
            Washer Washer = new Washer();
            Washer.StopWashing();

            LCD.clear();
            System.out.println("Snu 180 grader");
            Motor.A.rotate(180);  // roter 180 gr med motor A
            Motor.C.stop();
            while (Motor.A.isMoving()) Thread.yield();  // vent til rotasjon er ferdig
            this.driveForward();
            Washer.StartWashing();
        } catch(Exception e) {
            this.writeExeption(e);
        }
    }

    public void writeExeption(Exception msg)
    {
        LCD.clear();
        System.out.println(msg);
    }
}
