package fr.avp41.calculate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "calculate.db";
    private static final int DB_VERSION = 1;

    public static final String METRAGE_TABLE_NAME = "metrage";
    public static final String ID_COL = "id";
    public static final String NOM_CLIENT_COL = "nom_client";
    public static final String PRENOM_CLIENT_COL = "prenom_client";
    public static final String ADRESSE_CLIENT_COL = "adresse_client";
    public static final String CODE_POSTAL_CLIENT_COL = "code_postal_client";
    public static final String VILLE_CLIENT_COL = "ville_client";
    public static final String TELEPHONE_CLIENT_COL = "telephone";
    public static final String ENSEIGNE_COL = "enseigne";
    public static final String DATE_METRAGE_COL = "date_metrage";

    public static final String ENSEIGNES_TABLE = "enseignes";
    public static final String ID_ENSEIGNE_COL = "id";
    public static final String NOM_ENSEIGNE_COL = "nom_enseigne";
    public static final String ADRESSE_ENSEIGNE_COL = "adresse_enseigne";
    public static final String CODE_POSTAL_ENSEIGNE_COL = "code_postal_enseigne";
    public static final String VILLE_ENSEIGNE_COL = "ville_enseigne";
    public static final String TELEPHONE_ENSEIGNE_COL = "telephone_enseigne";
    public static final String NOM_CONTACT_ENSEIGNE_COL = "nom_contact_enseigne";
    public static final String EMAIL_CONTACT_COL = "email_enseigne";

    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableMetrage = "CREATE TABLE " + METRAGE_TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NOM_CLIENT_COL + " TEXT,"
                + PRENOM_CLIENT_COL + " TEXT,"
                + ADRESSE_CLIENT_COL + " TEXT,"
                + CODE_POSTAL_CLIENT_COL + " TEXT,"
                + VILLE_CLIENT_COL + " TEXT,"
                + TELEPHONE_CLIENT_COL + " TEXT,"
                + ENSEIGNE_COL + " TEXT,"
                + DATE_METRAGE_COL + " TEXT)";
        db.execSQL(createTableMetrage);

        String createTableEnseigne = "CREATE TABLE " + ENSEIGNES_TABLE + " ("
                + ID_ENSEIGNE_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NOM_ENSEIGNE_COL + " TEXT,"
                + ADRESSE_ENSEIGNE_COL + " TEXT,"
                + CODE_POSTAL_ENSEIGNE_COL + " TEXT,"
                + VILLE_ENSEIGNE_COL + " TEXT,"
                + TELEPHONE_ENSEIGNE_COL + " TEXT,"
                + NOM_CONTACT_ENSEIGNE_COL + " TEXT,"
                + EMAIL_CONTACT_COL + " TEXT)";
        db.execSQL(createTableEnseigne);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + METRAGE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ENSEIGNES_TABLE);
        onCreate(db);
    }
}