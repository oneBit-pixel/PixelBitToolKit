// Generated by view binder compiler. Do not edit!
package com.onePixel.spinner.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import com.onePixel.spinner.R;
import java.lang.NullPointerException;
import java.lang.Override;

public final class PowerspinnerItemDefaultPowerBinding implements ViewBinding {
  @NonNull
  private final AppCompatTextView rootView;

  @NonNull
  public final AppCompatTextView itemDefaultText;

  private PowerspinnerItemDefaultPowerBinding(@NonNull AppCompatTextView rootView,
      @NonNull AppCompatTextView itemDefaultText) {
    this.rootView = rootView;
    this.itemDefaultText = itemDefaultText;
  }

  @Override
  @NonNull
  public AppCompatTextView getRoot() {
    return rootView;
  }

  @NonNull
  public static PowerspinnerItemDefaultPowerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static PowerspinnerItemDefaultPowerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.powerspinner_item_default_power, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static PowerspinnerItemDefaultPowerBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    AppCompatTextView itemDefaultText = (AppCompatTextView) rootView;

    return new PowerspinnerItemDefaultPowerBinding((AppCompatTextView) rootView, itemDefaultText);
  }
}
