package com.example.miinventario.database;


import static com.example.miinventario.util.Constants.*;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.miinventario.util.Constants;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper databaseHelper;

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = Constants.DATABASE_NAME;

    //Constructor
    private DatabaseHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    public static synchronized DatabaseHelper getInstance(Context context){
        if(databaseHelper==null){
            databaseHelper = new DatabaseHelper(context);
        }
        return databaseHelper;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
            String CREAR_TABLA_PRODUCTOS = "CREATE TABLE " + TABLE_PRODUCTOS + "("
                    + COLUMN_PRODUCTOS_NOMBRE + " TEXT PRIMARY KEY, "
                    + COLUMN_PRODUCTOS_DESCRIPCION + " TEXT NOT NULL, " //nullable
                    + COLUMN_PRODUCTOS_STOCK + " INTEGER "
                    + ")";

            db.execSQL(CREAR_TABLA_PRODUCTOS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTOS);

            // Create tables again
            onCreate(db);
        }

        /*
        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);

            //enable foreign key constraints like ON UPDATE CASCADE, ON DELETE CASCADE
            db.execSQL("PRAGMA foreign_keys=ON;");
        }

         */
}
