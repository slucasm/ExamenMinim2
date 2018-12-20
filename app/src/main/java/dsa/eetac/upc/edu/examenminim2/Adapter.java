package dsa.eetac.upc.edu.examenminim2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Element> dataset;
    private Context context;

    public Adapter(Context context){
        this.context = context;
        this.dataset = new ArrayList<>();
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cities,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( Adapter.ViewHolder holder, int position) {
        Element element = dataset.get(position);
        holder.TextView_city.setText(element.getMunicipiNom());
        holder.TextView_ine.setText(element.getIne());
        Picasso.with(context).load(element.getMunicipiEscut()).into(holder.ImageView_escut);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addElements(List<Element> listElements){
        dataset.addAll(listElements);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout linearLayout;
        private ImageView ImageView_escut;
        private TextView TextView_city;
        private TextView TextView_ine;

        public ViewHolder(View itemView){
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linearLayout);
            ImageView_escut = itemView.findViewById(R.id.ImageView_escut);
            TextView_city =  itemView.findViewById(R.id.textView_city);
            TextView_ine = itemView.findViewById(R.id.textView_ine);
        }
    }
}
