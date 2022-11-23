package com.example.miinventario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miinventario.models.Producto;

import java.util.List;

public class AdaptadorListProductos extends RecyclerView.Adapter<AdaptadorListProductos.ViewHolderDatos> {

    List<Producto> listaDatos;

    public AdaptadorListProductos(List<Producto> listaDatos) { this.listaDatos = listaDatos; }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view =LayoutInflater.from((parent.getContext())).inflate(R.layout.item_list_productos, null, false);
        //return new ViewHolderDatos(view);
        return null ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listaDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView nomProducto;
        TextView descripcion;
        TextView stock;


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
                //nomProducto=itemView.findViewById(R.id.nomProducto);
                //stock=itemView.findViewById(R.id.Stock);
        }

    public void asignarDatos (Producto producto) {

            nomProducto.setText(producto.getNombre());
            descripcion.setText(producto.getDescripcion());
            stock.setText(producto.getStock());

        }
    }
}
