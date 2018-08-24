package com.miaxis.distinguished.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by tang.yf on 2018/8/14.
 */

@Entity
public class Customer {

    @Id
    private String ID;
    private String CUSTNAME;
    private String IDCARD;
    private String MOBILE;
    private String ORGCODE;
    private String ORGNODE;
    private String HEADIMAGE;
    private String OPDATE;
    private String STATUS;
    @Generated(hash = 591331447)
    public Customer(String ID, String CUSTNAME, String IDCARD, String MOBILE,
            String ORGCODE, String ORGNODE, String HEADIMAGE, String OPDATE,
            String STATUS) {
        this.ID = ID;
        this.CUSTNAME = CUSTNAME;
        this.IDCARD = IDCARD;
        this.MOBILE = MOBILE;
        this.ORGCODE = ORGCODE;
        this.ORGNODE = ORGNODE;
        this.HEADIMAGE = HEADIMAGE;
        this.OPDATE = OPDATE;
        this.STATUS = STATUS;
    }
    @Generated(hash = 60841032)
    public Customer() {
    }
    public String getID() {
        return this.ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getCUSTNAME() {
        return this.CUSTNAME;
    }
    public void setCUSTNAME(String CUSTNAME) {
        this.CUSTNAME = CUSTNAME;
    }
    public String getIDCARD() {
        return this.IDCARD;
    }
    public void setIDCARD(String IDCARD) {
        this.IDCARD = IDCARD;
    }
    public String getMOBILE() {
        return this.MOBILE;
    }
    public void setMOBILE(String MOBILE) {
        this.MOBILE = MOBILE;
    }
    public String getORGCODE() {
        return this.ORGCODE;
    }
    public void setORGCODE(String ORGCODE) {
        this.ORGCODE = ORGCODE;
    }
    public String getORGNODE() {
        return this.ORGNODE;
    }
    public void setORGNODE(String ORGNODE) {
        this.ORGNODE = ORGNODE;
    }
    public String getHEADIMAGE() {
        return this.HEADIMAGE;
    }
    public void setHEADIMAGE(String HEADIMAGE) {
        this.HEADIMAGE = HEADIMAGE;
    }
    public String getOPDATE() {
        return this.OPDATE;
    }
    public void setOPDATE(String OPDATE) {
        this.OPDATE = OPDATE;
    }
    public String getSTATUS() {
        return this.STATUS;
    }
    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
    
}
