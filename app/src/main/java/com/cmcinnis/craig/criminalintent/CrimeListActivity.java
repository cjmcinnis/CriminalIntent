package com.cmcinnis.craig.criminalintent;

import android.support.v4.app.Fragment;

public class CrimeListActivity extends SingleFrameActivity{

    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
