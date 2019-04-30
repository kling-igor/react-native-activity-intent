
package com.reactlibrary;

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

public class RNActivityIntentModule extends ReactContextBaseJavaModule implements ActivityEventListener  {

  private static final String E_CANCELLED = "E_CANCELLED";
  private static final String E_FAILED = "E_FAILED";

  private Promise mPromise;

  private final ActivityEventListener mActivityResultListener = new BaseActivityEventListener() {
    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
      if (mPromise != null) {
        if (resultCode == Activity.RESULT_CANCELED) {
          mPromise.reject(E_CANCELLED, "Cancelled...");
        } else if (resultCode == Activity.RESULT_OK) {
          if (data != null) {
            mPromise.resolve(Arguments.makeNativeMap(data.getExtras()));
          }
          else {
            mPromise.resolve(null)
          }
        }
        mPromise = null;
      }
    }
  }

  public RNActivityIntentModule(ReactApplicationContext reactContext) {
    super(reactContext);
    reactContext.addActivityEventListener(mActivityResultListener);
  }

  @Override
  public String getName() {
    return "RNActivityIntent";
  }

  @ReactMethod
  public void openActivity(String action, ReadableMap data, final Promise promise) {
    mPromise = promise;
    try {
      Activity activity = getReactApplicationContext().getCurrentActivity();
      Intent intent = new Intent(action);
      intent.putExtras(Arguments.toBundle(data));
      activity.startActivityForResult(intent, 42); // requestCode = 42
    }
    catch (Exception e) {
      mPromise.reject(E_FAILED, e);
      mPromise = null;
    }
    
  }
}