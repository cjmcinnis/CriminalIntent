package com.cmcinnis.craig.criminalintent;

import android.support.v4.app.Fragment;

public class CrimeActivity extends SingleFrameActivity {

    @Override
    protected Fragment createFragment(){
        return new CrimeFragment();
    }
}
