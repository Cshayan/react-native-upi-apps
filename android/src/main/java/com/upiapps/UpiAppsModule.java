package com.upiapps;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;

import java.util.List;

@ReactModule(name = UpiAppsModule.NAME)
public class UpiAppsModule extends ReactContextBaseJavaModule {
  public static final String NAME = "UpiApps";
  ReactApplicationContext mReactContext;

  public UpiAppsModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.mReactContext = reactContext;
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @ReactMethod
  public void getInstalledUPIAppList(Promise promise) {
    try {
      WritableArray installedAppList = Arguments.createArray();
      Uri uri = Uri.parse(String.format("%s://%s", "upi", "pay"));
      Intent upiUriIntent = new Intent();
      upiUriIntent.setData(uri);
      PackageManager pm = mReactContext.getApplicationContext().getPackageManager();
      List<ResolveInfo> resolveInfoList = pm.queryIntentActivities(upiUriIntent, PackageManager.MATCH_DEFAULT_ONLY);

      if (resolveInfoList != null) {
        for (ResolveInfo resolveInfo: resolveInfoList) {
          WritableMap appInfoMap = Arguments.createMap();
          appInfoMap.putString("name", resolveInfo.activityInfo.loadLabel(pm).toString());
          appInfoMap.putString("packageName", resolveInfo.activityInfo.packageName);
          appInfoMap.putString("icon", DrawableToBase64Util.drawableToBase64(resolveInfo.activityInfo.loadIcon(pm)));
          installedAppList.pushMap(appInfoMap);
        }
      }

      promise.resolve(installedAppList);
    } catch (Exception ex) {
      promise.reject(ex);
    }
  }

}
