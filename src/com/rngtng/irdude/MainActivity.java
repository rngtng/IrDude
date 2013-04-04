package com.rngtng.irdude;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        try {
//            Object irdaService = this.getSystemService("irda");
//            Class c = irdaService.getClass();
//            Class p[] = {String.class};
//            Method write = c.getMethod("write_irsend", p);
//
//            EditText te = (EditText)findViewById(R.id.sendSeq);
//            write.invoke(irdaService, te.getText().toString());
//    } catch (Exception e) {
//            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
//    }

        Object irdaService = this.getSystemService("irda");
        Class c = irdaService.getClass();
        Class p[] = {String.class};
        Method write;
		try {
			write = c.getMethod("write_irsend", p);
			try {
				write.invoke(irdaService, "38400,173,171,24,62,24,61,24,62,24,17,24,17,24,18,24,17,24,170,24,62,24,61,24,62,24,17,24,19,23,17,24,17,24,17,24,62,24,61,25,61,24,62,24,18,23,17,24,17,25,17,24,17,24,17,24,18,24,17,24,62,24,61,24,62,24,61,24,1880");
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
