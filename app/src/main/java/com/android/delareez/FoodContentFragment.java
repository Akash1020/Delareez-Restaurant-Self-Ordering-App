package com.android.delareez;

/**
 * Created by User on 10/8/2017.
 */

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.model.delareez.Menu;




/**
 * Provides UI for the view with List.
 */
public class FoodContentFragment extends Fragment {

            private static final String TAG = "FoodContentFragment";
            private RecyclerView mFoodList;
            private DatabaseReference mFirebaseRef;
            private LinearLayoutManager manager;
            private FirebaseRecyclerAdapter<Menu, ViewHolder> firebaseRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.item_ordered, container,false);

        mFirebaseRef = FirebaseDatabase.getInstance().getReference().child("Menu");

        mFoodList = (RecyclerView) myView.findViewById(R.id.food_list);
        manager = new LinearLayoutManager(this.getContext());
        mFoodList.setHasFixedSize(true);



        //Initializes Recycler View and Layout Manager.

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Menu, ViewHolder>(Menu.class, R.layout.food_row, ViewHolder.class, mFirebaseRef) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, Menu model, int position) {

                viewHolder.menuName.setText(model.getMenuName());
                viewHolder.menuPrice.setText(Double.toString(model.getMenuPrice()));
            }
        };

        mFoodList.setAdapter(firebaseRecyclerAdapter);
        mFoodList.setLayoutManager(manager);

        return myView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView menuName;
        public final TextView menuPrice;
        public final ImageView menuImage;



        public ViewHolder(View itemView) {
            super(itemView);
            menuName = (TextView) itemView.findViewById(R.id.card_title);
            menuPrice = (TextView) itemView.findViewById(R.id.card_price);
            menuImage = (ImageView) itemView.findViewById(R.id.card_image);


        }
    }

    
}
