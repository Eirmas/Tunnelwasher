import java.util.ArrayList;

public class Tunnelvask {

    public void Tunnelvask(){
        start();
    }

    public void start(){
        Washer Washer = new Washer();
        Washer.StartWashing();

        while(true){
            MotorClass Motor = new MotorClass();
            DetectColor DetectColor = new DetectColor();
            ArrayList<Boolean> colorArray = DetectColor.getColorArray();

            if(colorArray.get(0) == true && colorArray.get(1) == true){
                // Snu 180 grader
                Motor.turnAround();
            }
            if(colorArray.get(0) == true){
                // Gjør dette når venstre sensor ser svart
                try {
                    Motor.hitLeft();
                } catch (Exception e)
                    {
                        Motor.writeExeption(e);
                    }
            }
            if(colorArray.get(1) == true){
                // Gjør dette når høyre sensor ser svart
                try {
                    Motor.hitRight();
                } catch (Exception e)
                {
                    Motor.writeExeption(e);
                }
            }
        }
    }
}
