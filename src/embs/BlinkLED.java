package embs;

import com.ibm.saguaro.system.*;
import com.ibm.saguaro.logger.*;

public class BlinkLED {

	// timer
    static Timer timer;
    // timer interval in ticks
    static long INTERVAL;

    static {

        // instantiante a new timer
        timer = new Timer();

        // set the callback for the timer
        // i.e. when the timer ticks, it should call the "fire()"
	// method of this class

        timer.setCallback(new TimerEvent(null){
                public void invoke(byte param, long time){
                    BlinkLED.fire(param, time);
                }
            });



        // convert 2 seconds into platform ticks
        INTERVAL = Time.toTickSpan(Time.SECONDS, 2);

        // set a new alarm in 2 seconds from now
        timer.setAlarmBySpan(INTERVAL);



 	}



     /**
     * Callback for the timer
     */

    public static void fire (byte param, long time) {

        // Check and toggle the state of the first LED of the mote (index 0)
        
        if (LED.getState((byte)0) == 1 ) {
            LED.setState((byte)0, (byte)0);
            LED.setState((byte)1, (byte)0);
            LED.setState((byte)2, (byte)0);
            
            }
        else {
            LED.setState((byte)0, (byte)1);
            LED.setState((byte)1, (byte)1);
            LED.setState((byte)2, (byte)1);
		}
		
        // Setup a new timer alarm
        timer.setAlarmBySpan(INTERVAL);
    }


}