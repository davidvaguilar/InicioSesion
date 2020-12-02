package cl.santotomas.iniciosesion.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cl.santotomas.iniciosesion.R;
import cl.santotomas.iniciosesion.modelo.Specialty;

public class SpecialtyAdapter extends ArrayAdapter<Specialty> {

    private ArrayList<Specialty> specialtyList;
    private Context context;
    private int layoutId;

    public SpecialtyAdapter(@NonNull Context context, int resource, @NonNull List<Specialty> objects) {
        super(context, resource, objects);
        this.specialtyList = new ArrayList<>(objects);
        this.context = context;
        this.layoutId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if( convertView == null ){
            LayoutInflater  inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutId, null);

            viewHolder = new ViewHolder();

            viewHolder.id = (TextView) convertView.findViewById(R.id.especialidad_lista_item_codigo);
            viewHolder.nombre = (TextView) convertView.findViewById(R.id.especialidad_lista_item_nombre);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Specialty specialty = specialtyList.get(position);
        viewHolder.id.setText(String.valueOf(specialty.getId()));
        viewHolder.nombre.setText(specialty.getName());

        return convertView;
    }

    private class ViewHolder{
        public TextView id;
        public TextView nombre;
    }
}
