
package com.activityintent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;

public class RNActivityIntentModule extends ReactContextBaseJavaModule {

  private static final String E_CANCELLED = "E_CANCELLED";
  private static final String E_FAILED = "E_FAILED";
  private static final String E_ACTIVITY_DOES_NOT_EXIST = "E_ACTIVITY_DOES_NOT_EXIST";

  private Promise mPromise;

  private final ActivityEventListener mActivityResultListener = new BaseActivityEventListener() {
    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
      if (mPromise != null) {
        if (resultCode == Activity.RESULT_CANCELED) {
          mPromise.reject(E_CANCELLED, "Cancelled");
        } else if (resultCode == Activity.RESULT_OK) {
          if (data != null) {
            WritableMap result = new WritableNativeMap();
            result.putInt("code", resultCode);
            result.putString("data", data.getDataString());
            result.putMap("extras", Arguments.makeNativeMap(data.getExtras()));
            mPromise.resolve(result);
          } else {
            mPromise.resolve(null);
          }
        }
        mPromise = null;
      }
    }
  };

  public RNActivityIntentModule(ReactApplicationContext reactContext) {
    super(reactContext);
    reactContext.addActivityEventListener(mActivityResultListener);
  }

  @Override
  public String getName() {
    return "RNActivityIntent";
  }

  @ReactMethod
  public void openActivity(String action, String data, ReadableMap extras, final Promise promise) {
    Activity currentActivity = getReactApplicationContext().getCurrentActivity();
    if (currentActivity == null) {
      promise.reject("E_ACTIVITY_DOES_NOT_EXIST", "Activity doesn't exist");
      return;
    }

    mPromise = promise;

    try {
      Intent intent = new Intent(action, Uri.parse(data));
      intent.putExtras(Arguments.toBundle(extras));
      currentActivity.startActivityForResult(intent, 42); // requestCode = 42
    } catch (Exception e) {
      mPromise.reject(E_FAILED, e);
      mPromise = null;
    }
  }
}