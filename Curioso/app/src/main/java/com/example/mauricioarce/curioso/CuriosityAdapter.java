package com.example.mauricioarce.curioso;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Mauricio Arce on 29/10/2015.
 */
public class CuriosityAdapter extends RecyclerView.Adapter<CuriosityAdapter.CuriosityViewHolder> {

    private List<Curiosity> curiosities;
    private Context appContext;

    public CuriosityAdapter(List<Curiosity> curiosities, Context appContext) {
        this.curiosities = curiosities;
        this.appContext = appContext;
    }

    @Override
    public CuriosityAdapter.CuriosityViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);
        return new CuriosityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CuriosityAdapter.CuriosityViewHolder curiosityViewHolder, int i) {
        ImageView cardImage = curiosityViewHolder.getCardImage();
        Picasso.with(appContext).load(curiosities.get(i).getUrl()).into(cardImage);
        curiosityViewHolder.getCardTitle().setText(curiosities.get(i).getTitle());
        curiosityViewHolder.getCardIntroduction().setText(curiosities.get(i).getIntroduction());
        curiosityViewHolder.getCardContent().setText(curiosities.get(i).getContent());
        curiosityViewHolder.getCardCategory().setText(curiosities.get(i).getCategory());
        curiosityViewHolder.getCardAuthor().setText(curiosities.get(i).getAuthor());
        curiosityViewHolder.getCardUrlPrincipalImage().setText(curiosities.get(i).getUrl());
        curiosityViewHolder.getCardUrlFirstImage().setText(curiosities.get(i).getUrlFirstImage());
        curiosityViewHolder.getCardUrlSecondImage().setText(curiosities.get(i).getUrlSecondImage());
    }

    @Override
    public int getItemCount() {
        return curiosities.size();
    }

    public static class CuriosityViewHolder extends RecyclerView.ViewHolder {

        private static ImageView cardImage;
        private static TextView cardTitle;
        private static TextView cardIntroduction;
        private static TextView cardContent;
        private static TextView cardCategory;
        private static TextView cardAuthor;
        private static TextView cardUrlPrincipalImage;
        private static TextView cardUrlFirstImage;
        private static TextView cardUrlSecondImage;

        public CuriosityViewHolder(View itemView) {
            super(itemView);
            cardImage = (ImageView) itemView.findViewById(R.id.img_card);
            cardTitle = (TextView) itemView.findViewById(R.id.txt_card_title);
            cardIntroduction = (TextView) itemView.findViewById(R.id.txt_card_introduction);
            cardContent = (TextView) itemView.findViewById(R.id.txt_card_content);
            cardCategory = (TextView) itemView.findViewById(R.id.txt_card_category);
            cardAuthor = (TextView) itemView.findViewById(R.id.txt_card_author);
            cardUrlPrincipalImage = (TextView) itemView.findViewById(R.id.txt_img_principal);
            cardUrlFirstImage = (TextView) itemView.findViewById(R.id.txt_img_first);
            cardUrlSecondImage = (TextView) itemView.findViewById(R.id.txt_img_second);
        }

        public static ImageView getCardImage() {
            return cardImage;
        }

        public static TextView getCardTitle() {
            return cardTitle;
        }

        public static TextView getCardCategory() {
            return cardCategory;
        }

        public static TextView getCardIntroduction() {
            return cardIntroduction;
        }

        public static TextView getCardContent() {
            return cardContent;
        }

        public static TextView getCardAuthor() {
            return cardAuthor;
        }

        public static TextView getCardUrlPrincipalImage() {
            return cardUrlPrincipalImage;
        }

        public static TextView getCardUrlFirstImage() {
            return cardUrlFirstImage;
        }

        public static TextView getCardUrlSecondImage() {
            return cardUrlSecondImage;
        }
    }
}
