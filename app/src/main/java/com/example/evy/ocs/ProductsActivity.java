package com.example.evy.ocs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ListActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;

public class ProductsActivity extends AppCompatActivity {

    public static class  CarViewHolder extends RecyclerView.ViewHolder{
        public TextView carName;
        public TextView carPrice;

        public CarViewHolder(View v){
             super(v);
             carName = (TextView)itemView.findViewById(R.id.Name);
             carPrice = (TextView)itemView.findViewById(R.id.Price);
        }
    }

    public static  final String Cars = "products";

    private RecyclerView mCarRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private DatabaseReference mFIrebaseDatabaseReference;
    private FirebaseRecyclerAdapter<car, CarViewHolder> mFirebaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        mCarRecyclerView = (RecyclerView)findViewById(R.id.carRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setStackFromEnd(true);
        mCarRecyclerView.setLayoutManager(mLinearLayoutManager);

        mFIrebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<car, CarViewHolder>(
                car.class,
                R.layout.list_of_products,
                CarViewHolder.class,
                mFIrebaseDatabaseReference.child(Cars)) {
            @Override
            protected void populateViewHolder(CarViewHolder viewHolder, car model, int position) {
                viewHolder.carName.setText(model.getName());
                viewHolder.carPrice.setText(model.getPrice());
            }
        };

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int carCount = mFirebaseAdapter.getItemCount();
                int lastVisiablePosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastVisiablePosition == -1 || (positionStart >= (carCount -1) && lastVisiablePosition == (positionStart -1))) {
                    mCarRecyclerView.scrollToPosition(positionStart);

                }
            }
        });
        /*
        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionstart, int itemCount){
                super.onItemRangeInserted(positionstart,itemCount);
                int carCount = mFirebaseAdapter.getItemCount();
                int lastVisiablePosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                mCarRecyclerView.scrollToPosition(positionstart);


                if (lastVisiablePosition == -1 || (positionstart >=(carCount -1) && lastVisiablePosition == (positionstart -1))){
                    mCarRecyclerView.scrollToPosition(positionstart);
                }

            }
        });

        */

        mCarRecyclerView.setLayoutManager(mLinearLayoutManager);
        mCarRecyclerView.setAdapter(mFirebaseAdapter);


    }
}
