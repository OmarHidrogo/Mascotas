package com.omar_hidrogo_local.mascotas;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by tmhidrooma on 11/08/2017.
 */

public class NotificacionIDTokenServices extends FirebaseInstanceIdService {

    private static final String TAG ="firebase_token";
    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);
    }

    private void enviarTokenRegistro(String token){
        Log.d(TAG, token);
    }
}
