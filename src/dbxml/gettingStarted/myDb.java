//
// See the file LICENSE for redistribution information.
//
// Copyright (c) 2004, 2014 Oracle and/or its affiliates.  All rights reserved.
//

package dbxml.gettingStarted;

import com.sleepycat.db.*;

//Class used to open and close a Berkeley Database using a transaction.
public class myDb {
    private Environment dbEnv_ = null;
    private String dbName_ = null;
    private boolean dbIsOpen_ = false;
    private Database db_ = null;

    public myDb( String dbName, Environment dbEnv ) throws Throwable {
        System.err.println("dbName " + dbName);
        DatabaseConfig config = new DatabaseConfig();
        config.setAllowCreate(true);
        config.setType(DatabaseType.BTREE);
        dbName_ = dbName;
        dbEnv_ = dbEnv;
        Transaction txn = dbEnv.beginTransaction(null,null);
        db_ = dbEnv.openDatabase(txn, dbName, null, config);
        txn.commit();
        //Boolean used to know whether to close the database
        // when the cleanup() method is called.
        dbIsOpen_ = true;
    }

    public Database getDatabase() {
        return db_;
    }

    public Environment getEnvironment() {
        return dbEnv_;
    }

    public String getDatabaseName() {
        return dbName_;
    }

    //Close the database
    public void cleanup() throws Throwable {
        if (dbIsOpen_) {
            db_.close();
            dbIsOpen_ = false;
       }
    }
}
