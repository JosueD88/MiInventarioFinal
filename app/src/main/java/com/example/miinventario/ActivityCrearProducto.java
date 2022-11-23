package com.example.miinventario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.miinventario.database.DatabaseQueryClass;
import com.example.miinventario.models.Producto;

import java.util.ArrayList;
import java.util.List;

public class ActivityCrearProducto extends AppCompatActivity {

    private EditText nombre;
    private EditText descripcion;
    private EditText stock;
    private Button btnCrear;
    List<Producto> listaDatos; //datos con los que se construirá el reciclador
    RecyclerView reciclador;


    ArrayList<String> listaDatos2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_producto);

        nombre = this.findViewById(R.id.editTextNombre);
        descripcion = this.findViewById(R.id.editTextDescripcion);
        stock = this.findViewById(R.id.editTextStock);
        btnCrear = this.findViewById(R.id.btCrear);

        this.obtenerListaProductos();

        btnCrear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Producto productoNuevo = new Producto();

                productoNuevo.setNombre(nombre.getText().toString());
                productoNuevo.setDescripcion(descripcion.getText().toString());
                productoNuevo.setStock(stock.getText().toString());

                DatabaseQueryClass dbdbQeryProducto = new DatabaseQueryClass(getBaseContext());
                dbdbQeryProducto.insertarProducto(productoNuevo);

                Intent i = new Intent(ActivityCrearProducto.this,ActivityVistaInventario.class);
                startActivity(i);

            }
        });

    }


    public void obtenerListaProductos(){
        DatabaseQueryClass dbQeryProducto1 = new DatabaseQueryClass(getBaseContext());

        listaDatos=dbQeryProducto1.obtenerProductos();
        AdaptadorListProductos adapter = new AdaptadorListProductos(listaDatos);

    }



}