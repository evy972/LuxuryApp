package com.example.evy.ocs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProductsActivity extends AppCompatActivity {

    //show specific item
    public static class  CarViewHolder extends RecyclerView.ViewHolder{
        public TextView carName;
        public TextView carPrice;

        public CarViewHolder(View v){
            super(v);
            carName = (TextView)itemView.findViewById(R.id.Name);
            carPrice = (TextView)itemView.findViewById(R.id.Price);
        }
    }

    //the name of the table
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
            //show in view
            protected void populateViewHolder(CarViewHolder viewHolder, car model, int position) {
                viewHolder.carName.setText(model.getName());
                viewHolder.carPrice.setText(model.getPrice());
            }
        };

        //show each item
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

        mCarRecyclerView.setLayoutManager(mLinearLayoutManager);
        mCarRecyclerView.setAdapter(mFirebaseAdapter);
    }
}
