package com.example.lera.fgadvice.db;

import android.content.Context;

import com.example.lera.fgadvice.model.Advice;
import com.yahoo.squidb.android.AndroidOpenHelper;
import com.yahoo.squidb.data.ISQLiteDatabase;
import com.yahoo.squidb.data.ISQLiteOpenHelper;
import com.yahoo.squidb.data.SquidDatabase;
import com.yahoo.squidb.sql.Table;

public class DatabaseDAO extends SquidDatabase {

    private static final int VERSION = 1;
    private Context context;
    private static DatabaseDAO instance;

    private DatabaseDAO(Context context) {
        super();
        this.context = context;
    }

    public static DatabaseDAO getDBInstance(Context context) {
        if (instance == null) {
            return instance = new DatabaseDAO(context);
        }
        return instance;
    }

    @Override
    public String getName() {
        return "advice_database.db";
    }

    @Override
    protected int getVersion() {
        return VERSION;
    }

    @Override
    protected Table[] getTables() {
        return new Table[]{
                Advices.TABLE
        };
    }

    @Override
    protected boolean onUpgrade(ISQLiteDatabase db, int oldVersion, int newVersion) {
        return false;
    }

    @Override
    protected ISQLiteOpenHelper createOpenHelper(String databaseName, OpenHelperDelegate delegate, int version) {
        return new AndroidOpenHelper(context, databaseName, delegate, version);
    }
}
