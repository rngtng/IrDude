package com.rngtng.irdude;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;

public class MainActivity extends Activity {
	Object irdaService;
	Method irWrite;
	SparseArray<String> irData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		irData = new SparseArray<String>();
		irData.put(
				R.id.buttonPower,
				hex2dec("0000 006d 0022 0002 0152 00aa 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0696 0152 0055 0015 0e23"));
		irData.put(
				R.id.buttonVolDown,
				hex2dec("0000 006d 0022 0002 0152 00aa 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 0015 0015 003f 0015 003f 0015 0015 0015 0015 0015 0696 0152 0055 0015 0e23"));
		irData.put(
				R.id.buttonMute,
				hex2dec("0000 006d 0022 0002 0152 00aa 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 0015 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0696 0152 0055 0015 0e23"));
		irData.put(
				R.id.buttonVolUp,
				hex2dec("0000 006d 0022 0002 0152 00aa 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0696 0152 0055 0015 0e23"));
		irData.put(
				R.id.buttonTuner,
				hex2dec("0000 006d 0022 0002 0152 00aa 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 0015 0015 0696 0152 0055 0015 0e23"));
		irData.put(
				R.id.buttonPhono,
				hex2dec("0000 006d 0022 0002 0154 00aa 0014 0016 0014 0016 0014 0016 0014 0016 0014 0016 0014 0016 0014 0016 0014 0041 0014 0016 0014 0016 0014 0016 0014 0016 0014 0041 0013 0041 0014 0041 0014 0016 0014 0016 0014 0041 0013 0017 0013 0017 0013 0017 0013 0017 0013 0041 0014 0041 0014 0041 0013 0017 0013 0041 0014 0041 0014 0041 0013 0041 0014 0016 0014 0016 0014 06bf 0154 0056 0014 0e6b"));
		irData.put(
				R.id.buttonAux,
				hex2dec("0000 006d 0022 0002 0154 00aa 0014 0016 0014 0016 0014 0016 0014 0016 0014 0016 0014 0016 0014 0016 0014 0041 0014 0016 0014 0016 0014 0016 0014 0016 0014 0041 0013 0041 0014 0041 0014 0016 0014 0016 0014 0041 0013 0041 0014 0041 0014 0016 0014 0016 0014 0041 0013 0041 0014 0041 0014 0016 0014 0016 0014 0016 0014 0041 0013 0041 0014 0016 0014 0016 0014 06bf 0154 0057 0013 0e6b"));

		irInit();
	}

	public void irInit() {
		irdaService = this.getSystemService("irda");
		Class c = irdaService.getClass();
		Class p[] = { String.class };
		try {
			irWrite = c.getMethod("write_irsend", p);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	public void irSend(View view) {
		String data = irData.get(view.getId());
		if (data != null) {
			try {
				irWrite.invoke(irdaService, data);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	protected String hex2dec(String irData) {
		List<String> list = new ArrayList<String>(Arrays.asList(irData
				.split(" ")));
		list.remove(0); // dummy
		int frequency = Integer.parseInt(list.remove(0), 16); // frequency
		list.remove(0); // seq1
		list.remove(0); // seq2

		for (int i = 0; i < list.size(); i++) {
			list.set(i, Integer.toString(Integer.parseInt(list.get(i), 16)));
		}

		frequency = (int) (1000000 / (frequency * 0.241246));
		list.add(0, Integer.toString(frequency));

		irData = "";
		for (String s : list) {
			irData += s + ",";
		}
		return irData;
	}

}
