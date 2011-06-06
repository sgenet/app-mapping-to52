package com.mappingtelefot;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class TelefotServices {
	
	private static final String SOAP_ACTION = "http://telefot-set.utbm.fr/userDeviceMatching.php/matchDevicetoUser";
	private static final String METHOD_NAME = "matchDevicetoUser";
	private static final String NAMESPACE = "";
	private static final String URL = "http://telefot-set.utbm.fr/userDeviceMatching.php";
	private Context context;
	
	public TelefotServices(Context appContext){
		this.context = appContext;
	}
	
    public void sendDatas(Details details){
		int result = StoreDetails(details);
		handler.sendEmptyMessage(result);
    }
    
	@SuppressWarnings("finally")
	public int StoreDetails(Details details){
		
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
		AndroidHttpTransport aht = new AndroidHttpTransport(URL);
        
        // Add request properties 
		Log.e("DATAS", details.getUserId()+" : "+details.getDeviceModel()+" : "+details.getDeviceId()+" : "+details.getDeviceBrand()+" : "+details.getPhoneNumber());
		request.addProperty("userId", details.getUserId());
		request.addProperty("model", details.getDeviceModel());
		request.addProperty("serialNumber", details.getDeviceId());
		request.addProperty("brand", details.getDeviceBrand());
//		request.addProperty("phoneNumber", details.getPhoneNumber());
		request.addProperty("phoneNumber", "123456789");
        
        // Send request
        soapEnvelope.setOutputSoapObject(request);
        soapEnvelope.encodingStyle = "http://schemas.xmlsoap.org/soap/encoding/";
        soapEnvelope.setOutputSoapObject(request);
        soapEnvelope.env="http://schemas.xmlsoap.org/soap/envelope/";
        soapEnvelope.enc="http://schemas.xmlsoap.org/soap/encoding/";
        soapEnvelope.dotNet = false;
        soapEnvelope.xsd = "http://www.w3.org/2001/XMLSchema";
        soapEnvelope.xsi = "http://www.w3.org/2001/XMLSchema-instance";
        
        int result = -1;

        System.out.println("body out : " + soapEnvelope.bodyOut.toString());
        
        try{
        	aht.call(SOAP_ACTION, soapEnvelope);
        	result = Integer.parseInt(soapEnvelope.getResponse().toString());
        } catch(Exception e){
        	Log.e("ERROR StoreDetails", e.getLocalizedMessage());
//        	e.printStackTrace();
            System.out.println("body int : " + soapEnvelope.bodyIn.toString());
        }
        finally {
        	return result;
        }
	}
	
    private Handler handler = new Handler() {

    	public void handleMessage(android.os.Message msg) {
    		
    		if(msg.what == 0)
    			Toast.makeText(context, "Mapping OK.", Toast.LENGTH_LONG).show();
    		else
    			Toast.makeText(context, "Erreur du service : "+ msg.what, Toast.LENGTH_LONG).show();
    	};
    };
	
}
