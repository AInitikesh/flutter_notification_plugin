package com.falio.flutternotificationplugin;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.io.IOException;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** FlutterNotificationPlugin */
public class FlutterNotificationPlugin implements MethodCallHandler {
  /** Plugin registration. */
  
  Registrar registrar;

  public FlutterNotificationPlugin(Registrar registrar) {
    this.registrar = registrar;
  }

  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_notification_plugin");
    channel.setMethodCallHandler(new FlutterNotificationPlugin(registrar));
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("showNotification")) {
//      AssetManager assetManager = registrar.context().getAssets();
//      String key = registrar.lookupKeyForAsset("assets/icon-send.png");
//      try {
//        AssetFileDescriptor fd = assetManager.openFd(key);
//      } catch (IOException e) {
//        e.printStackTrace();
//      }

      String title = call.argument("title");
      String description = call.argument("description");

      NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this.registrar.context(), "CHANNEL_ID")
              .setSmallIcon(R.drawable.notification_tile_bg)
              .setContentTitle(title)
              .setContentText(description)
              .setPriority(NotificationCompat.PRIORITY_DEFAULT);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        CharSequence name = "My Channel";
        String cDescription = "Describe my channel";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
        channel.setDescription(cDescription);
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManager = this.registrar.context().getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
      }

      NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this.registrar.context());
      notificationManager.notify(12345, mBuilder.build());

      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else {
      result.notImplemented();
    }
  }
}
