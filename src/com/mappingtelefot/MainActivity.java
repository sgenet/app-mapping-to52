package com.mappingtelefot;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity 
{
	private String android_id;
	
	private TextView mInputUserId;
	private TextView mInputDeviceId;
	private TextView mInputDeviceBrand;
	private TextView mInputDeviceModel;
	private TextView mInputPhoneNumber;
	private Button mButtonSend;
	
	private Context context;
	private TelefotServices telefotServices;
	private Details details;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Context :
        context = getApplicationContext();
        telefotServices = new TelefotServices(context);
        details = new Details();
        
        // Form :
        mInputUserId = (TextView)findViewById(R.id.InputUserId);
        mInputDeviceId = (TextView)findViewById(R.id.InputDeviceId);
        mInputDeviceBrand = (TextView)findViewById(R.id.InputDeviceBrand);
        mInputDeviceModel = (TextView)findViewById(R.id.InputDeviceModel);
        mInputPhoneNumber = (TextView)findViewById(R.id.InputPhoneNumber);
		mButtonSend = ( Button )findViewById( R.id.ButtonSendForm);
        
		// Device Id :
        android_id = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID); 
        mInputDeviceId.setText(android_id);
    
        // Device Brand :
        mInputDeviceBrand.setText(android.os.Build.MANUFACTURER);
        
        // Device Model :
        mInputDeviceModel.setText(android.os.Build.MODEL);
        
        // Phone Number :
        TelephonyManager telephonyMgr = (TelephonyManager) getSystemService (Context.TELEPHONY_SERVICE); 
        mInputPhoneNumber.setText(telephonyMgr.getLine1Number()); 
    
        // Send Datas :
		mButtonSend.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick( View view ) 
            {
            	
            	details.setUserId(Integer.parseInt(mInputUserId.getText().toString()));
            	details.setDeviceId(mInputDeviceId.getText().toString());
            	details.setDeviceBrand(mInputDeviceBrand.getText().toString());
            	details.setDeviceModel(mInputDeviceModel.getText().toString());
            	details.setPhoneNumber(mInputPhoneNumber.getText().toString());
            	
            	// send datas in new thread
        		Thread thread = new Thread(new Runnable() {
        			
        			@Override
        			public void run() {
        				
        				telefotServices.sendDatas(details);
        			}
        		});
        		
        		thread.start();
            }
        });
    }

}