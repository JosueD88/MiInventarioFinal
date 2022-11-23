package com.example.miinventario.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.example.miinventario.models.Producto;
import com.example.miinventario.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryClass {

    private Context context;

    public DatabaseQueryClass(Context context) { this.context = context; }

    public String insertarProducto(Producto p) {
        String nombreProducto= p.getNombre();
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        //valores de contenido
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.COLUMN_PRODUCTOS_NOMBRE, p.getNombre());
        contentValues.put(Constants.COLUMN_PRODUCTOS_STOCK, p.getStock());
        contentValues.put(Constants.COLUMN_PRODUCTOS_DESCRIPCION, p.getDescripcion());

        try {
            //decirle a la BD que le añada los datos
            sqLiteDatabase.insertOrThrow(Constants.TABLE_PRODUCTOS, null, contentValues);
        }
        catch (SQLiteException e) {
            Toast.makeText(context, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return nombreProducto;
    }

    //listado
    public ArrayList<Producto> obtenerProductos() {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;

        try {
            cursor = sqLiteDatabase.query(Constants.TABLE_PRODUCTOS, null, null, null, null, null, null, null);
        if(cursor!=null)
            if (cursor.moveToFirst()){
                ArrayList<Producto> ListaObtenida =new ArrayList<>();
                do{
                    Producto productoObtenido = new Producto();

                    String nombre=cursor.getString(cursor.getColumnIndex(Constants.COLUMN_PRODUCTOS_NOMBRE));
                    String descripcion=cursor.getString(cursor.getColumnIndex(Constants.COLUMN_PRODUCTOS_DESCRIPCION));
                    String stock=cursor.getString(cursor.getColumnIndex(Constants.COLUMN_PRODUCTOS_STOCK));
                    //se asigna a al producto
                    productoObtenido.setNombre(nombre);
                    productoObtenido.setDescripcion(descripcion);
                    productoObtenido.setStock(stock);
                    //agrego producto a la lista
                    ListaObtenida.add(productoObtenido);

                }while(cursor.moveToNext());
                return ListaObtenida;
            }
        }
        catch (SQLiteException e){
            Toast.makeText(context, "error al listar:" + e.getMessage(), Toast.LENGTH_SHORT).show();

        } finally {
            sqLiteDatabase.close();
        }
        return new ArrayList<Producto>();

    }


    //-----------------------------------------------------------


    public Producto obtenerProducto(String nombre) {
        Producto productoObtenido = new Producto();
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        int id = 25;
        Cursor cursor = null;

        try {
            cursor = sqLiteDatabase.query(Constants.TABLE_PRODUCTOS,
                    null, Constants.COLUMN_PRODUCTOS_NOMBRE + " = ? ",
                    new String[]{nombre}, null, null, null, null);

            if (cursor.moveToFirst()) {

                String nombreP = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_PRODUCTOS_NOMBRE));
                String descripcion = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_PRODUCTOS_DESCRIPCION));
                String stock=cursor.getString(cursor.getColumnIndex(Constants.COLUMN_PRODUCTOS_STOCK));
                productoObtenido.setNombre(nombreP);
                productoObtenido.setDescripcion(descripcion);
                productoObtenido.setStock(stock);


            }
            return productoObtenido;


        } catch (SQLiteException e) {
            Toast.makeText(context, "error al listar:" + e.getMessage(), Toast.LENGTH_SHORT).show();

        } finally {
            sqLiteDatabase.close();
        }
        return productoObtenido;

    }
}

