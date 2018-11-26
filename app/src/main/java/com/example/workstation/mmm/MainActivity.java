package com.example.workstation.mmm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<PassingObj> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.textViewNom) TextView nom;
        @BindView(R.id.textViewPrenom) TextView prenom;
        @BindView(R.id.textViewNaissance) TextView naissance;
        @BindView(R.id.textViewVille) TextView ville;
        @BindView(R.id.textViewRegion) TextView region;
        @BindView(R.id.textViewNumber) TextView number;

        public MyViewHolder(View v) {
            super(v);
            nom = v.findViewById(R.id.textViewNom);
            prenom = v.findViewById(R.id.textViewPrenom);
            naissance = v.findViewById(R.id.textViewNaissance);
            ville = v.findViewById(R.id.textViewVille);
            region = v.findViewById(R.id.textViewRegion);
            number = v.findViewById(R.id.textViewNumber);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<PassingObj> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.person, parent, false);
        MyViewHolder mvh = new MyViewHolder(view);
        return mvh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.nom.setText(mDataset.get(position).getNom());
        holder.prenom.setText(mDataset.get(position).getPrenom());
        holder.naissance.setText(mDataset.get(position).getNaissance());
        holder.ville.setText(mDataset.get(position).getVille());
        holder.region.setText(mDataset.get(position).getRegion());
        holder.number.setText(mDataset.get(position).getNum());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.buttonAdd) Button add;
    private LinearLayoutManager layoutManager;
    private MyAdapter adapter;


    ArrayList<PassingObj> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataset = new ArrayList<PassingObj>();
        setContentView(R.layout.main);
        ButterKnife.bind(this);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        adapter = new MyAdapter(dataset);
        recyclerView.setAdapter(adapter);

    }

    public void addButton(View view){
        Toast.makeText(getApplicationContext(),"validated", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, FormActivity.class);

        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 0) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                // Do something with the contact here (bigger example below)
                Bundle extras = data.getExtras();

                PassingObj passingObj = extras.getParcelable("passingObj");

                dataset.add(passingObj);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
