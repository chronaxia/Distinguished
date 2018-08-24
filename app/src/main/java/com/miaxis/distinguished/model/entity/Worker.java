package com.miaxis.distinguished.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by tang.yf on 2018/8/14.
 */

@Entity
public class Worker {

    @Id
    private String ID;
    private String WORKERCODE;
    private String WORKERNAME;
    private String WORKERPHONE;
    private String ORGCODE;
    private String ORGNAME;
    private String ORGNODE;
    private String ACCOUNT;
    private String UNIONID;
    private String PASSWORD;
    private String OPUSER;
    private String OPDATE;
    private String STATUS;
    @Generated(hash = 1719163898)
    public Worker(String ID, String WORKERCODE, String WORKERNAME,
            String WORKERPHONE, String ORGCODE, String ORGNAME, String ORGNODE,
            String ACCOUNT, String UNIONID, String PASSWORD, String OPUSER,
            String OPDATE, String STATUS) {
        this.ID = ID;
        this.WORKERCODE = WORKERCODE;
        this.WORKERNAME = WORKERNAME;
        this.WORKERPHONE = WORKERPHONE;
        this.ORGCODE = ORGCODE;
        this.ORGNAME = ORGNAME;
        this.ORGNODE = ORGNODE;
        this.ACCOUNT = ACCOUNT;
        this.UNIONID = UNIONID;
        this.PASSWORD = PASSWORD;
        this.OPUSER = OPUSER;
        this.OPDATE = OPDATE;
        this.STATUS = STATUS;
    }
    @Generated(hash = 107771754)
    public Worker() {
    }
    public String getID() {
        return this.ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getWORKERCODE() {
        return this.WORKERCODE;
    }
    public void setWORKERCODE(String WORKERCODE) {
        this.WORKERCODE = WORKERCODE;
    }
    public String getWORKERNAME() {
        return this.WORKERNAME;
    }
    public void setWORKERNAME(String WORKERNAME) {
        this.WORKERNAME = WORKERNAME;
    }
    public String getWORKERPHONE() {
        return this.WORKERPHONE;
    }
    public void setWORKERPHONE(String WORKERPHONE) {
        this.WORKERPHONE = WORKERPHONE;
    }
    public String getORGCODE() {
        return this.ORGCODE;
    }
    public void setORGCODE(String ORGCODE) {
        this.ORGCODE = ORGCODE;
    }
    public String getORGNAME() {
        return this.ORGNAME;
    }
    public void setORGNAME(String ORGNAME) {
        this.ORGNAME = ORGNAME;
    }
    public String getORGNODE() {
        return this.ORGNODE;
    }
    public void setORGNODE(String ORGNODE) {
        this.ORGNODE = ORGNODE;
    }
    public String getACCOUNT() {
        return this.ACCOUNT;
    }
    public void setACCOUNT(String ACCOUNT) {
        this.ACCOUNT = ACCOUNT;
    }
    public String getUNIONID() {
        return this.UNIONID;
    }
    public void setUNIONID(String UNIONID) {
        this.UNIONID = UNIONID;
    }
    public String getPASSWORD() {
        return this.PASSWORD;
    }
    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
    public String getOPUSER() {
        return this.OPUSER;
    }
    public void setOPUSER(String OPUSER) {
        this.OPUSER = OPUSER;
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
