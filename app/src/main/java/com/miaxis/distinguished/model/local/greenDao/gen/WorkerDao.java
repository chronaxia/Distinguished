package com.miaxis.distinguished.model.local.greenDao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.miaxis.distinguished.model.entity.Worker;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "WORKER".
*/
public class WorkerDao extends AbstractDao<Worker, String> {

    public static final String TABLENAME = "WORKER";

    /**
     * Properties of entity Worker.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property ID = new Property(0, String.class, "ID", true, "ID");
        public final static Property WORKERCODE = new Property(1, String.class, "WORKERCODE", false, "WORKERCODE");
        public final static Property WORKERNAME = new Property(2, String.class, "WORKERNAME", false, "WORKERNAME");
        public final static Property WORKERPHONE = new Property(3, String.class, "WORKERPHONE", false, "WORKERPHONE");
        public final static Property ORGCODE = new Property(4, String.class, "ORGCODE", false, "ORGCODE");
        public final static Property ORGNAME = new Property(5, String.class, "ORGNAME", false, "ORGNAME");
        public final static Property ORGNODE = new Property(6, String.class, "ORGNODE", false, "ORGNODE");
        public final static Property ACCOUNT = new Property(7, String.class, "ACCOUNT", false, "ACCOUNT");
        public final static Property UNIONID = new Property(8, String.class, "UNIONID", false, "UNIONID");
        public final static Property PASSWORD = new Property(9, String.class, "PASSWORD", false, "PASSWORD");
        public final static Property OPUSER = new Property(10, String.class, "OPUSER", false, "OPUSER");
        public final static Property OPDATE = new Property(11, String.class, "OPDATE", false, "OPDATE");
        public final static Property STATUS = new Property(12, String.class, "STATUS", false, "STATUS");
    }


    public WorkerDao(DaoConfig config) {
        super(config);
    }
    
    public WorkerDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"WORKER\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: ID
                "\"WORKERCODE\" TEXT," + // 1: WORKERCODE
                "\"WORKERNAME\" TEXT," + // 2: WORKERNAME
                "\"WORKERPHONE\" TEXT," + // 3: WORKERPHONE
                "\"ORGCODE\" TEXT," + // 4: ORGCODE
                "\"ORGNAME\" TEXT," + // 5: ORGNAME
                "\"ORGNODE\" TEXT," + // 6: ORGNODE
                "\"ACCOUNT\" TEXT," + // 7: ACCOUNT
                "\"UNIONID\" TEXT," + // 8: UNIONID
                "\"PASSWORD\" TEXT," + // 9: PASSWORD
                "\"OPUSER\" TEXT," + // 10: OPUSER
                "\"OPDATE\" TEXT," + // 11: OPDATE
                "\"STATUS\" TEXT);"); // 12: STATUS
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WORKER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Worker entity) {
        stmt.clearBindings();
 
        String ID = entity.getID();
        if (ID != null) {
            stmt.bindString(1, ID);
        }
 
        String WORKERCODE = entity.getWORKERCODE();
        if (WORKERCODE != null) {
            stmt.bindString(2, WORKERCODE);
        }
 
        String WORKERNAME = entity.getWORKERNAME();
        if (WORKERNAME != null) {
            stmt.bindString(3, WORKERNAME);
        }
 
        String WORKERPHONE = entity.getWORKERPHONE();
        if (WORKERPHONE != null) {
            stmt.bindString(4, WORKERPHONE);
        }
 
        String ORGCODE = entity.getORGCODE();
        if (ORGCODE != null) {
            stmt.bindString(5, ORGCODE);
        }
 
        String ORGNAME = entity.getORGNAME();
        if (ORGNAME != null) {
            stmt.bindString(6, ORGNAME);
        }
 
        String ORGNODE = entity.getORGNODE();
        if (ORGNODE != null) {
            stmt.bindString(7, ORGNODE);
        }
 
        String ACCOUNT = entity.getACCOUNT();
        if (ACCOUNT != null) {
            stmt.bindString(8, ACCOUNT);
        }
 
        String UNIONID = entity.getUNIONID();
        if (UNIONID != null) {
            stmt.bindString(9, UNIONID);
        }
 
        String PASSWORD = entity.getPASSWORD();
        if (PASSWORD != null) {
            stmt.bindString(10, PASSWORD);
        }
 
        String OPUSER = entity.getOPUSER();
        if (OPUSER != null) {
            stmt.bindString(11, OPUSER);
        }
 
        String OPDATE = entity.getOPDATE();
        if (OPDATE != null) {
            stmt.bindString(12, OPDATE);
        }
 
        String STATUS = entity.getSTATUS();
        if (STATUS != null) {
            stmt.bindString(13, STATUS);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Worker entity) {
        stmt.clearBindings();
 
        String ID = entity.getID();
        if (ID != null) {
            stmt.bindString(1, ID);
        }
 
        String WORKERCODE = entity.getWORKERCODE();
        if (WORKERCODE != null) {
            stmt.bindString(2, WORKERCODE);
        }
 
        String WORKERNAME = entity.getWORKERNAME();
        if (WORKERNAME != null) {
            stmt.bindString(3, WORKERNAME);
        }
 
        String WORKERPHONE = entity.getWORKERPHONE();
        if (WORKERPHONE != null) {
            stmt.bindString(4, WORKERPHONE);
        }
 
        String ORGCODE = entity.getORGCODE();
        if (ORGCODE != null) {
            stmt.bindString(5, ORGCODE);
        }
 
        String ORGNAME = entity.getORGNAME();
        if (ORGNAME != null) {
            stmt.bindString(6, ORGNAME);
        }
 
        String ORGNODE = entity.getORGNODE();
        if (ORGNODE != null) {
            stmt.bindString(7, ORGNODE);
        }
 
        String ACCOUNT = entity.getACCOUNT();
        if (ACCOUNT != null) {
            stmt.bindString(8, ACCOUNT);
        }
 
        String UNIONID = entity.getUNIONID();
        if (UNIONID != null) {
            stmt.bindString(9, UNIONID);
        }
 
        String PASSWORD = entity.getPASSWORD();
        if (PASSWORD != null) {
            stmt.bindString(10, PASSWORD);
        }
 
        String OPUSER = entity.getOPUSER();
        if (OPUSER != null) {
            stmt.bindString(11, OPUSER);
        }
 
        String OPDATE = entity.getOPDATE();
        if (OPDATE != null) {
            stmt.bindString(12, OPDATE);
        }
 
        String STATUS = entity.getSTATUS();
        if (STATUS != null) {
            stmt.bindString(13, STATUS);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public Worker readEntity(Cursor cursor, int offset) {
        Worker entity = new Worker( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // ID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // WORKERCODE
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // WORKERNAME
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // WORKERPHONE
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // ORGCODE
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // ORGNAME
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // ORGNODE
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // ACCOUNT
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // UNIONID
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // PASSWORD
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // OPUSER
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // OPDATE
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12) // STATUS
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Worker entity, int offset) {
        entity.setID(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setWORKERCODE(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setWORKERNAME(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setWORKERPHONE(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setORGCODE(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setORGNAME(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setORGNODE(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setACCOUNT(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setUNIONID(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setPASSWORD(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setOPUSER(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setOPDATE(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setSTATUS(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
     }
    
    @Override
    protected final String updateKeyAfterInsert(Worker entity, long rowId) {
        return entity.getID();
    }
    
    @Override
    public String getKey(Worker entity) {
        if(entity != null) {
            return entity.getID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Worker entity) {
        return entity.getID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
