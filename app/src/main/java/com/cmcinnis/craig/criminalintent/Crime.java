package com.cmcinnis.craig.criminalintent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private boolean mRequirePolice;
    private String mSuspect;

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDateAsString() {
        SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM dd, yyyy");

        return format.format(mDate);
    }

    public String getHourMinute() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a");

        return format.format(mDate);
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public Crime(){
        mId = UUID.randomUUID();
        mDate = new Date();
        mRequirePolice = false; //default of false
    }

    public Crime(UUID id){
        mId = id;
        mDate = new Date();
    }

    public String getSuspect(){
        return mSuspect;
    }

    public void setSuspect(String suspect){
        mSuspect = suspect;
    }

    public String getPhotoFilename(){
        return "IMG_ " + getId().toString() + ".jpg";
    }

    public boolean isRequirePolice() {
        return mRequirePolice;
    }

    public void setRequirePolice(boolean requiresPolice) {
        mRequirePolice = requiresPolice;
    }
}
