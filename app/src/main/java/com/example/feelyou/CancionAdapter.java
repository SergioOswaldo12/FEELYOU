package com.example.feelyou;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class CancionAdapter extends RecyclerView.Adapter<CancionAdapter.ViewHolder>{
    private List<Cancion> canciones;
    private Context context;

    public CancionAdapter(Context context, List<Cancion> canciones) {
        this.context = context;
        this.canciones = canciones;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cancion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cancion cancion = canciones.get(position);
        holder.nombre.setText(cancion.getNombre());
        holder.imagen.setImageURI(Uri.parse(cancion.getRutaImagen()));

        holder.btnPlay.setOnClickListener(v -> {
            MediaPlayer mp = new MediaPlayer();
            try {
                mp.setDataSource(cancion.getRutaAudio());
                mp.prepare();
                mp.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public int getItemCount() {
        return canciones.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        ImageView imagen;
        Button btnPlay;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txtNombreCancion);
            imagen = itemView.findViewById(R.id.imgCancion);
            btnPlay = itemView.findViewById(R.id.btnReproducir);
        }
    }
}
