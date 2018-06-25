#import "FlutterNotificationPlugin.h"
#import <flutter_notification_plugin/flutter_notification_plugin-Swift.h>

@implementation FlutterNotificationPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterNotificationPlugin registerWithRegistrar:registrar];
}
@end
