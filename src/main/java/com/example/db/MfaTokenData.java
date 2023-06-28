package com.example.db;

import java.io.Serializable;

public class MfaTokenData implements Serializable {

    private String qrCode;
    private String mfaCode;
    public MfaTokenData(String qrCode, String mfaCode) {
        this.qrCode = qrCode;
        this.mfaCode = mfaCode;
    }
    
    public String getMfaCode() {
        return mfaCode;
    }
    public void setMfaCode(String mfaCode) {
        this.mfaCode = mfaCode;
    }
    
    public String getQrCode() {
        return qrCode;
    }
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}