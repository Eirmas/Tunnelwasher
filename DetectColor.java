import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

import java.util.ArrayList;

public class DetectColor{

    public ArrayList<Boolean> getColorArray(){
            ArrayList<Boolean> returnArray = new ArrayList<>();

            Brick brick = BrickFinder.getDefault();
            Port s1 = brick.getPort("S1"); // fargesensor venster
            Port s2 = brick.getPort("S2"); // fargesensor høyre

            EV3 ev3 = (EV3) BrickFinder.getLocal();
            TextLCD lcd = ev3.getTextLCD();
            Keys keys = ev3.getKeys();

            lcd.drawString("Trykk for starte", 0, 1);
            keys.waitForAnyPress();

            /* Definerer en fargesensor og fargeAvleser til forran*/
            EV3ColorSensor fargesensorLeft = new EV3ColorSensor(s1); // ev3-fargesensor
            SampleProvider fargeLeserLeft = fargesensorLeft.getMode("RGB");  // svart = 0.01..

            /* Definerer en fargesensor og fargeAvleser til siden*/
            EV3ColorSensor fargesensorRight = new EV3ColorSensor(s2); // ev3-fargesensor
            SampleProvider fargeLeserRight = fargesensorRight.getMode("RGB");  // svart = 0.01..

            float[] fargeSampleLeft = new float[fargeLeserLeft.sampleSize()];  // tabell som innholder avlest verdi forran
            float[] fargeSampleRight = new float[fargeLeserRight.sampleSize()]; // tabell som innholder avlest verdi side

            double blackVar = 0.01;

            fargeLeserLeft.fetchSample(fargeSampleLeft, 0);  // hent verdi fra fargesensor

            if (fargeSampleLeft != null && fargeSampleLeft.length > 0
                    && fargeSampleRight != null && fargeSampleRight.length > 0)
            {

                fargesensorLeft.fetchSample(fargeSampleLeft, 0);
                fargesensorRight.fetchSample(fargeSampleRight, 0);

                if (fargeSampleLeft[0] < blackVar)
                {
                    returnArray.add(true);
                }
                else
                {
                    returnArray.add(false);
                }

                if (fargeSampleRight[0] < blackVar)
                {
                    returnArray.add(true);
                }
                else
                {
                    returnArray.add(false);
                }
            }
            return returnArray;
    }
}
