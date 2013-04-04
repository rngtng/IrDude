package com.rngtng.irdude;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	HashMap<Integer, String> irData;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        irData = new HashMap<Integer, String>();
        irData.put(R.id.buttonPower,   "0000 006d 0022 0002 0152 00aa 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0696 0152 0055 0015 0e23");
        irData.put(R.id.buttonVolDown, "0000 006d 0022 0002 0152 00aa 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 0015 0015 003f 0015 003f 0015 0015 0015 0015 0015 0696 0152 0055 0015 0e23");
        irData.put(R.id.buttonMute,    "0000 006d 0022 0002 0152 00aa 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 0015 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0696 0152 0055 0015 0e23");
        irData.put(R.id.buttonVolUp,   "0000 006d 0022 0002 0152 00aa 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0696 0152 0055 0015 0e23");                    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }   
        
    public void irSend(View view) {
    	System.out.println(view.getId());
    	
    	String data = irData.get(view.getId());
    	sendIr( hex2dec(data) );
    }
    
    public String hex2dec(String irData) {
    	List<String> list = new ArrayList<String>(Arrays.asList(irData.split(" ")));
    	list.remove(0); // dummy
    	int frequency = Integer.parseInt( list.remove(0), 16) ; // frequency    	
    	list.remove(0); // seq1
    	list.remove(0); // seq2    	
    	
    	for(int i=0; i<list.size(); i++){
    		list.set(i, Integer.toString( Integer.parseInt(list.get(i), 16) ) );
        }
    	
    	frequency = (int) (1000000 / (frequency * 0.241246));
    	list.add(0, Integer.toString(frequency));
    	
    	irData = "";
    	for (String s : list)
    	{
    		irData += s + ",";
    	}    	
    	return irData;
   }
    
   public void sendIr(String irData) {    	
        Object irdaService = this.getSystemService("irda");
        Class c = irdaService.getClass();
        Class p[] = {String.class};
        Method write;
		try {
			write = c.getMethod("write_irsend", p);
			try {
				write.invoke(irdaService,irData);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
}
