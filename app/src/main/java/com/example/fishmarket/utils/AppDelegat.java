package com.example.fishmarket.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class AppDelegat extends Application {
   // @Override
   /* public void onCreate() {
        super.onCreate();

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }*/

  //  private final TerminalLifecycleObserver observer;
  //private FirebaseAnalytics mFirebaseAnalytics;
    @Override
  protected void attachBaseContext(Context base) {
      super.attachBaseContext(base);
   //   MultiDex.install(this);
      try {
          AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
          //FirebaseApp.initializeApp(this);
       //   mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
          //FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
         // Crashlytics.getInstance().crash();


      }catch (Exception e){
          e.printStackTrace();
      }
  }
    @Override
    public void onCreate() {
        super.onCreate();
        // Register the observer for all lifecycle hooks

        try {
           // OrbotHelper.get(this).init();

          //   handleSSLHandshake();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Don't forget to let the observer know if your application is running low on memory

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

    }
    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }
}
