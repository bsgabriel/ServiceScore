package br.ucs.servicescore.util.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.ucs.servicescore.R;
import br.ucs.servicescore.entity.Place;
import br.ucs.servicescore.util.adapter.viewholder.BusinessViewHolder;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessViewHolder> {
    private List<Place> lstPlaces;
    private Context context;

    public BusinessAdapter(Context context) {
        this.context = context;
    }

    public List<Place> getLstPlaces() {
        if (lstPlaces == null)
            lstPlaces = new ArrayList<>();

        return lstPlaces;
    }

    @NonNull
    @Override
    public BusinessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BusinessViewHolder(LayoutInflater.from(context).inflate(R.layout.item_business, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessViewHolder holder, int position) {
        holder.bind(getLstPlaces().get(position));
    }

    @Override
    public int getItemCount() {
        return getLstPlaces().size();
    }
}
