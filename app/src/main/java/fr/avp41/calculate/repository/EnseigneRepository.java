package fr.avp41.calculate.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.avp41.calculate.DbHandler;

public class EnseigneRepository {

    private DbHandler dbHandler;

    public EnseigneRepository(Context context) {
        dbHandler = new DbHandler(context);
    }

    public Cursor getAllEnseignes() {
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + DbHandler.ENSEIGNES_TABLE, null);
    }
}