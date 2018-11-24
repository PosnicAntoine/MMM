package com.example.workstation.mmm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

class PassingObj implements Parcelable {

    private String nom;
    private String prenom;
    private String naissance;
    private String ville;
    private String region;
    private String num;


    public PassingObj(String nom, String prenom, String naissance,
                      String ville, String region, String num){
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
        this.ville = ville;
        this.region = region;
        this.num= num;

    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        //out.writeInt(mData);
        out.writeString(nom);
        out.writeString(prenom);
        out.writeString(naissance);
        out.writeString(ville);
        out.writeString(region);
        out.writeString(num);

    }

    public static final Parcelable.Creator<PassingObj> CREATOR
            = new Parcelable.Creator<PassingObj>() {
        public PassingObj createFromParcel(Parcel in) {
            return new PassingObj(in);
        }

        public PassingObj[] newArray(int size) {
            return new PassingObj[size];
        }
    };

    private PassingObj(Parcel in) {
        //mData = in.readInt();
        this.nom = in.readString();
        this.prenom = in.readString();
        this.naissance = in.readString();
        this.ville = in.readString();
        this.region = in.readString();
        this.num= in.readString();

    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNaissance() {
        return naissance;
    }

    public String getVille() {
        return ville;
    }

    public String getRegion() {
        return region;
    }

    public String getNum() {
        return num;
    }
}

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.editTextNom) EditText nom;
    @BindView(R.id.editTextPrenom) EditText prenom;
    @BindView(R.id.editTextNaissance) EditText naissance;
    @BindView(R.id.editTextVille) EditText ville;
    @BindView(R.id.spinnerDepartements) Spinner region;
    EditText num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);

    }

    public void validateButton(View view){
        Toast.makeText(getApplicationContext(),"validated", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PostActivity.class);

        PassingObj passingObj;
        if(num == null) {
            passingObj = new PassingObj(nom.getText().toString(), prenom.getText().toString(),
                    naissance.getText().toString(), ville.getText().toString(),
                    region.getSelectedItem().toString(), "");
        }else {
            passingObj = new PassingObj(nom.getText().toString(), prenom.getText().toString(),
                    naissance.getText().toString(), ville.getText().toString(),
                    region.getSelectedItem().toString(), num.getText().toString());
        }

        intent.putExtra("passingObj", passingObj);
        /*
        intent.putExtra("nom", nom.getText().toString());
        intent.putExtra("prenom", prenom.getText().toString());
        intent.putExtra("naissance", naissance.getText().toString());
        intent.putExtra("ville", ville.getText().toString());
        intent.putExtra("region", region.getSelectedItem().toString());

        if(num!=null)
            intent.putExtra("num", num.getText().toString());
        */

        startActivity(intent);
    }

    public void villeButton(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://fr.wikipedia.org"));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        Log.i("Menu","menu created");
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Log.i("Menu","item clicked");
        switch(item.getItemId()){
            case R.id.menuResetButton:
                resetFields();
                break;
            case R.id.menuAddTelNumberButton:
                addNumber();
                break;
        }
        return true;
    }

    public void resetFields(){
        nom.setText("");
        prenom.setText("");
        naissance.setText("");
        ville.setText("");
    }

    public void addNumber(){
        if(this.num == null) {
            this.num = new EditText(getApplicationContext());
            this.num.setText("");
            this.num.setHint("Number");
            this.num.setId(View.generateViewId());
            this.num.setInputType(InputType.TYPE_CLASS_PHONE);
            this.num.setWidth(360);

            ConstraintSet set = new ConstraintSet();
            ConstraintLayout layout;

            layout = findViewById(R.id.main);
            layout.addView(this.num);
            set.clone(layout);
            set.connect(this.num.getId(), ConstraintSet.TOP, R.id.spinnerDepartements, ConstraintSet.BOTTOM, 320);
            set.setMargin(this.num.getId(), ConstraintSet.LEFT, 12);//marche po
            set.applyTo(layout);

        }else{
            ConstraintLayout cl = findViewById(R.id.main);
            cl.removeView(this.num);
            this.num = null;
        }

    }


}
