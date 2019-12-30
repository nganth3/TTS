package com.example.tts;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.util.Locale;

public class CusttomTTS extends ActivityCompat {
    public CusttomTTS(Context context) {
        this.context = context;
    }

    Context context;
    TextToSpeech textToSpeech =null;
    public void speechNoidung(final String txtnoidung) {
        this.textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {

                    int result=textToSpeech.setLanguage(new Locale("vi_VN"));

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.i("TextToSpeech", "Language Not Supported");
                    }
                    textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                        @Override
                        public void onStart(String utteranceId) {
                            Log.i("TextToSpeech", "On Start");

                        }

                        @Override
                        public void onDone(String utteranceId) {
                            Log.i("TextToSpeech", "On onDone");
                         onDonel();

                       //     Toast.makeText(context,"Đã nói", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onError(String utteranceId) {
                            Log.i("TextToSpeech", "On onError");
                        }
                    });
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        String a =editNoidung(txtnoidung);
                        textToSpeech.speak(a, TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);
                    }
                } else {
                    Log.i("TextToSpeech", "Initialization Failed");
                }
            }
        });
    }
    private void onDonel(){
     // /  Toast.makeText(context,"Đã nói", Toast.LENGTH_LONG).show();,"com.google.android.tts"
       // textToSpeech.shutdown();
       Toast.makeText(context,"Đã nói", Toast.LENGTH_LONG).show();
       Log.i("TextToSpeech", "On onDone XXXXXXXXX");
    }

    private String editNoidung(String noidung){
        String ketquaedit="";
        Character tam;

        for (int i = 0; i <= noidung.length()-1;i++){
            tam = noidung.charAt(i);
            ketquaedit = ketquaedit +" " + tam;
        }

        return  ketquaedit;

    }
    private String editSo(int t){
        String strNum ="";
        switch (t){
            case '0':strNum+= "không ";break;
            case '1':strNum+= "một ";break;
            case '2':strNum+= "hai ";break;
            case '3':strNum+= "ba ";break;
            case '4':strNum+= "bốn ";break;
            case '5':strNum+= "năm ";break;
            case '6':strNum+= "sáu ";break;
            case '7':strNum+= "bảy ";break;
            case '8':strNum+= "tám ";break;
            case '9':strNum+= "chín ";break;
            default: strNum+= "";
        }
        return  strNum;
    }
}
