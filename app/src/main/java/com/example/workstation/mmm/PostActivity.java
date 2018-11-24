package com.example.workstation.mmm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostActivity extends AppCompatActivity {
    @BindView(R.id.textViewNom) TextView nom;
    @BindView(R.id.textViewPrenom) TextView prenom;
    @BindView(R.id.textViewNaissance) TextView naissance;
    @BindView(R.id.textViewVille) TextView ville;
    @BindView(R.id.textViewRegion) TextView region;
    @BindView(R.id.textViewNumber) TextView number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();

        PassingObj passingObj = extras.getParcelable("passingObj");


        nom.setText(passingObj.getNom());
        prenom.setText(passingObj.getPrenom());
        naissance.setText(passingObj.getNaissance());
        ville.setText(passingObj.getVille());
        region.setText(passingObj.getRegion());
        number.setText(passingObj.getNum());

        /*
        nom.setText(extras.get("nom").toString());
        prenom.setText(extras.get("prenom").toString());
        naissance.setText(extras.get("naissance").toString());
        ville.setText(extras.get("ville").toString());
        region.setText(extras.get("region").toString());
        if(extras.get("num") !=null)
            number.setText(extras.get("num").toString());
        */
    }
}
