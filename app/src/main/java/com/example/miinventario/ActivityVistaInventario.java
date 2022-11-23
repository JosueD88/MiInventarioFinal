package com.example.miinventario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.miinventario.database.DatabaseQueryClass;
import com.example.miinventario.models.Producto;

import java.util.ArrayList;
import java.util.List;

public class ActivityVistaInventario extends AppCompatActivity {

    ArrayList<Producto> listaProductos;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_inventario);

        DatabaseQueryClass dbQeryUsuario1 = new DatabaseQueryClass(getBaseContext());


        listaProductos=dbQeryUsuario1.obtenerProductos();
        AdaptadorListProductos adapter = new AdaptadorListProductos(listaProductos);

        lista= findViewById(R.id.listProductos);
        ArrayList<Producto> pr = dbQeryUsuario1.obtenerProductos();
        ArrayList<String> list =new ArrayList<String>();

        for(Producto p:pr) {
            list.add("Nombre producto: " + p.getNombre() + "   Descripcion: "+ p.getDescripcion() + "                Stock: " + p.getStock());
        }
        ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,list);
        lista.setAdapter(a);
       }

    public void onClickCrearProducto(View view) {
        Intent i = new Intent(ActivityVistaInventario.this,ActivityCrearProducto.class);
        startActivity(i);
    }
}