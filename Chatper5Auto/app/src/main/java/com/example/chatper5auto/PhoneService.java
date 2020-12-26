package com.example.chatper5auto;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class PhoneService extends Service {
    private ITelephony iTelephony;
    private TelephonyManager manager;
    private String inNumber = null;

    public void onCreate() {
        if(Constant.flag) {
            getphoner();
            manager.listen(new PhoneStateListener() {
                @Override
                public void onCallStateChanged(int state, String incomingNumber) {
                    super.onCallStateChanged(state, incomingNumber);
                    inNumber = incomingNumber;
                    switch (state) {
                        case 1:
                            try {
                                if(Constant.flag) {
                                    iTelephony.endCall();
                                    Log.i("wzxtest",incomingNumber);
                                    Toast.makeText(getApplicationContext(), "endcall:" + incomingNumber, Toast.LENGTH_SHORT).show();
                                    sendSMS(incomingNumber, "<CallToSms自动回复>\n" + Constant.anti_sms);
                                }
                            } catch (RemoteException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            break;
                        default:
                            break;
                    }
                }
            }, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    public void getphoner(){
        manager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        Class<TelephonyManager> c = TelephonyManager.class;
        Method method = null;
        try {
            method = c.getDeclaredMethod("getITelephony", (Class[])null);
            method.setAccessible(true);
            iTelephony = (ITelephony) method.invoke(manager, (Object[])null);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendSMS(String phonenumber, String msg){  //发送短信方法，需要短信权限
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> list = smsManager.divideMessage(msg);
        for (String sms : list)
            smsManager.sendTextMessage(phonenumber, null, sms, null, null);
    }
}
