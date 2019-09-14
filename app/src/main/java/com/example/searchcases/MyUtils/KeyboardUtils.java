package com.example.searchcases.MyUtils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class KeyboardUtils {
    public static  void initFocusAndShowKeyBoard(final EditText editText, final Context context){
        editText.requestFocus();
        editText.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager )context.getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        }, 300);
    }
    public static  void hideKeyBoard(final EditText editText, final Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
