import 'dart:async';

import 'package:flutter/services.dart';

class FlutterNotificationPlugin {
  static const MethodChannel _channel =
      const MethodChannel('flutter_notification_plugin');

  static Future<String> showNotification(title, description) async {
    final String version = await _channel.invokeMethod('showNotification',<String, dynamic>{'title': title, 'description': description});
    return version;
  }
}
