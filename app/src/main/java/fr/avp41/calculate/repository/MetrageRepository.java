package fr.avp41.calculate.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.avp41.calculate.DbHandler;

import java.time.LocalDate;

public class MetrageRepository {

    private DbHandler dbHandler;

    public MetrageRepository(Context context) {
        dbHandler = new DbHandler(context);
    }

    public long addMetrage(String nomClient, String prenomClient, String adresseClient, String telephoneClient, String codePostalClient, String villeClient, String enseigne) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        LocalDate date = LocalDate.now();

        ContentValues values = new ContentValues();
        values.put(DbHandler.NOM_CLIENT_COL, nomClient);
        values.put(DbHandler.PRENOM_CLIENT_COL, prenomClient);
        values.put(DbHandler.ADRESSE_CLIENT_COL, adresseClient);
        values.put(DbHandler.CODE_POSTAL_CLIENT_COL, codePostalClient);
        values.put(DbHandler.VILLE_CLIENT_COL, villeClient);
        values.put(DbHandler.TELEPHONE_CLIENT_COL, telephoneClient);
        values.put(DbHandler.ENSEIGNE_COL, enseigne);
        values.put(DbHandler.DATE_METRAGE_COL, String.valueOf(date));

        long id = db.insert(DbHandler.METRAGE_TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public Cursor getAllMetrages() {
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        return db.rawQuery("SELECT " + DbHandler.ID_COL + "," + DbHandler.NOM_CLIENT_COL + "," + DbHandler.DATE_METRAGE_COL + " FROM " + DbHandler.METRAGE_TABLE_NAME, null);
    }

    public void deleteMetrageById(long id) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        db.delete(DbHandler.METRAGE_TABLE_NAME, DbHandler.ID_COL + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}