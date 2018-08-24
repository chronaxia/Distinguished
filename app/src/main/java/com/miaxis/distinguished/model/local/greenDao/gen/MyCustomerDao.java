package com.miaxis.distinguished.model.local.greenDao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.miaxis.distinguished.model.entity.MyCustomer;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MY_CUSTOMER".
*/
public class MyCustomerDao extends AbstractDao<MyCustomer, String> {

    public static final String TABLENAME = "MY_CUSTOMER";

    /**
     * Properties of entity MyCustomer.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property ID = new Property(0, String.class, "ID", true, "ID");
        public final static Property CUSTNAME = new Property(1, String.class, "CUSTNAME", false, "CUSTNAME");
        public final static Property IDCARD = new Property(2, String.class, "IDCARD", false, "IDCARD");
        public final static Property MOBILE = new Property(3, String.class, "MOBILE", false, "MOBILE");
        public final static Property ORGCODE = new Property(4, String.class, "ORGCODE", false, "ORGCODE");
        public final static Property ORGNODE = new Property(5, String.class, "ORGNODE", false, "ORGNODE");
        public final static Property HEADIMAGE = new Property(6, String.class, "HEADIMAGE", false, "HEADIMAGE");
        public final static Property OPDATE = new Property(7, String.class, "OPDATE", false, "OPDATE");
        public final static Property STATUS = new Property(8, String.class, "STATUS", false, "STATUS");
        public final static Property WORKERCODE = new Property(9, String.class, "WORKERCODE", false, "WORKERCODE");
    }


    public MyCustomerDao(DaoConfig config) {
        super(config);
    }
    
    public MyCustomerDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MY_CUSTOMER\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: ID
                "\"CUSTNAME\" TEXT," + // 1: CUSTNAME
                "\"IDCARD\" TEXT," + // 2: IDCARD
                "\"MOBILE\" TEXT," + // 3: MOBILE
                "\"ORGCODE\" TEXT," + // 4: ORGCODE
                "\"ORGNODE\" TEXT," + // 5: ORGNODE
                "\"HEADIMAGE\" TEXT," + // 6: HEADIMAGE
                "\"OPDATE\" TEXT," + // 7: OPDATE
                "\"STATUS\" TEXT," + // 8: STATUS
                "\"WORKERCODE\" TEXT);"); // 9: WORKERCODE
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MY_CUSTOMER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MyCustomer entity) {
        stmt.clearBindings();
 
        String ID = entity.getID();
        if (ID != null) {
            stmt.bindString(1, ID);
        }
 
        String CUSTNAME = entity.getCUSTNAME();
        if (CUSTNAME != null) {
            stmt.bindString(2, CUSTNAME);
        }
 
        String IDCARD = entity.getIDCARD();
        if (IDCARD != null) {
            stmt.bindString(3, IDCARD);
        }
 
        String MOBILE = entity.getMOBILE();
        if (MOBILE != null) {
            stmt.bindString(4, MOBILE);
        }
 
        String ORGCODE = entity.getORGCODE();
        if (ORGCODE != null) {
            stmt.bindString(5, ORGCODE);
        }
 
        String ORGNODE = entity.getORGNODE();
        if (ORGNODE != null) {
            stmt.bindString(6, ORGNODE);
        }
 
        String HEADIMAGE = entity.getHEADIMAGE();
        if (HEADIMAGE != null) {
            stmt.bindString(7, HEADIMAGE);
        }
 
        String OPDATE = entity.getOPDATE();
        if (OPDATE != null) {
            stmt.bindString(8, OPDATE);
        }
 
        String STATUS = entity.getSTATUS();
        if (STATUS != null) {
            stmt.bindString(9, STATUS);
        }
 
        String WORKERCODE = entity.getWORKERCODE();
        if (WORKERCODE != null) {
            stmt.bindString(10, WORKERCODE);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MyCustomer entity) {
        stmt.clearBindings();
 
        String ID = entity.getID();
        if (ID != null) {
            stmt.bindString(1, ID);
        }
 
        String CUSTNAME = entity.getCUSTNAME();
        if (CUSTNAME != null) {
            stmt.bindString(2, CUSTNAME);
        }
 
        String IDCARD = entity.getIDCARD();
        if (IDCARD != null) {
            stmt.bindString(3, IDCARD);
        }
 
        String MOBILE = entity.getMOBILE();
        if (MOBILE != null) {
            stmt.bindString(4, MOBILE);
        }
 
        String ORGCODE = entity.getORGCODE();
        if (ORGCODE != null) {
            stmt.bindString(5, ORGCODE);
        }
 
        String ORGNODE = entity.getORGNODE();
        if (ORGNODE != null) {
            stmt.bindString(6, ORGNODE);
        }
 
        String HEADIMAGE = entity.getHEADIMAGE();
        if (HEADIMAGE != null) {
            stmt.bindString(7, HEADIMAGE);
        }
 
        String OPDATE = entity.getOPDATE();
        if (OPDATE != null) {
            stmt.bindString(8, OPDATE);
        }
 
        String STATUS = entity.getSTATUS();
        if (STATUS != null) {
            stmt.bindString(9, STATUS);
        }
 
        String WORKERCODE = entity.getWORKERCODE();
        if (WORKERCODE != null) {
            stmt.bindString(10, WORKERCODE);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public MyCustomer readEntity(Cursor cursor, int offset) {
        MyCustomer entity = new MyCustomer( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // ID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // CUSTNAME
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // IDCARD
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // MOBILE
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // ORGCODE
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // ORGNODE
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // HEADIMAGE
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // OPDATE
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // STATUS
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // WORKERCODE
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MyCustomer entity, int offset) {
        entity.setID(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setCUSTNAME(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setIDCARD(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMOBILE(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setORGCODE(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setORGNODE(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setHEADIMAGE(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setOPDATE(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setSTATUS(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setWORKERCODE(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final String updateKeyAfterInsert(MyCustomer entity, long rowId) {
        return entity.getID();
    }
    
    @Override
    public String getKey(MyCustomer entity) {
        if(entity != null) {
            return entity.getID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(MyCustomer entity) {
        return entity.getID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
