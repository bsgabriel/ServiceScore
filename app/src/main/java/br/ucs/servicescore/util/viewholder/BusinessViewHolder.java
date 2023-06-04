package br.ucs.servicescore.util.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.ucs.servicescore.R;
import br.ucs.servicescore.entity.Business;

public class BusinessViewHolder extends RecyclerView.ViewHolder {
    private View itemView;
    private TextView txtNome;

    public BusinessViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        init();
    }

    private void init() {
        txtNome = (TextView) itemView.findViewById(R.id.txtName);
    }


    public void bind(Business business) {
        txtNome.setText(business.getName());
    }

}
