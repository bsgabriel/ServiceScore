package br.ucs.servicescore.util.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import br.ucs.servicescore.R;
import br.ucs.servicescore.entity.Business;

public class BusinessViewHolder extends RecyclerView.ViewHolder {
    private View itemView;
    private TextView txtNome;
    private TextView txtCategoria;
    private TextView txtNumReviews;
    private TextView txtEndereco;
    private ImageView imageView;
    private RatingBar ratingBar;

    public BusinessViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        init();
    }

    private void init() {
        txtNome = (TextView) itemView.findViewById(R.id.txtName);
        txtCategoria = (TextView) itemView.findViewById(R.id.txtCategoria);
        txtNumReviews = (TextView) itemView.findViewById(R.id.txtNumReviews);
        txtEndereco = (TextView) itemView.findViewById(R.id.txtEndereco);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
    }


    public void bind(Business business) {
        txtNome.setText(business.getName());
        ratingBar.setRating(business.getRating());
        txtCategoria.setText(business.getCategories().get(0).getTitle());
        txtNumReviews.setText(business.getReviewCount() + " reviews");
        txtEndereco.setText(business.getLocation().getAddress());

        Glide.with(itemView.getContext())//
                .load(business.getImageUrl())//
                .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(20)))//
                .into(imageView);

    }

}
