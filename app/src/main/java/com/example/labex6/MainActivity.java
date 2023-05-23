package com.example.labex6;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.slider.Slider;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    ImageView IVpokeball;
    EditText ETpokemon;
    TextView TVindex;
    TextView TVname;
    TextView TVtype;
    Slider TVhp;
    TextView TVnamep;
    Slider TVattack;
    Slider TVdefense;
    Slider TVspAttack;
    Slider TVspDefense;
    Slider TVspeed;
    ImageButton BTNsearch;
    ImageButton BTNclear;

    TextView TVmoves;

    TextView texthp, textatk, textdef, textspatk, textspdef, textspeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void initialize() {
        ETpokemon = findViewById(R.id.ETpokemon);
        IVpokeball = findViewById(R.id.IVpokeball);
        TVindex = findViewById(R.id.TVindex);
        TVname = findViewById(R.id.TVname);
        TVnamep = findViewById(R.id.TVnamep);
        TVtype = findViewById(R.id.TVtype);
        TVhp = findViewById(R.id.TVhp);
        TVattack = findViewById(R.id.TVattack);
        TVdefense = findViewById(R.id.TVdefense);
        TVspAttack = findViewById(R.id.TVspAttack);
        TVspDefense = findViewById(R.id.TVspDefense);
        TVspeed = findViewById(R.id.TVspeed);
        BTNsearch = findViewById(R.id.BTNsearch);
        BTNclear = findViewById(R.id.BTNclear);
        TVmoves = findViewById(R.id.TVmoves);
        texthp = findViewById(R.id.texthp);
        textatk = findViewById(R.id.textatk);
        textdef = findViewById(R.id.textdef);
        textspatk = findViewById(R.id.textspatk);
        textspdef = findViewById(R.id.textspdef);
        textspeed = findViewById(R.id.textspeed);


        BTNsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = ETpokemon.getText().toString().toLowerCase().trim();
                if (search.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a valid pokemon", Toast.LENGTH_SHORT).show();
                    return;
                }

                String url = "https://pokeapi.co/api/v2/pokemon/" + search;

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            //ID
                            JSONArray gameIndices = response.getJSONArray("game_indices");
                            JSONObject gameIndex = gameIndices.getJSONObject(5);
                            String indexSTR = gameIndex.getString("game_index");

                            TVindex.setText("#00" + indexSTR);

                            JSONArray jsonArray = response.getJSONArray("forms");
                            JSONObject data = jsonArray.getJSONObject(0);
                            String nameSTR = data.getString("name");

                            TVname.setText(nameSTR.toUpperCase());
                            TVnamep.setText(nameSTR.toUpperCase());

                            //3D IMAGE
                            JSONObject sprites = response.getJSONObject("sprites");
                            JSONObject sprites2 = sprites.getJSONObject("other");
                            JSONObject sprites3 = sprites2.getJSONObject("home");
                            String spriteSTR = sprites3.getString("front_default");

                            Glide.with(MainActivity.this).load(spriteSTR).into(IVpokeball);

                            //TYPE
                            jsonArray = response.getJSONArray("types");
                            data = jsonArray.getJSONObject(0);
                            JSONObject object = data.getJSONObject("type");
                            String typeSTR = object.getString("name");

                            TVtype.setText(typeSTR.toUpperCase());

                            //HP
                            jsonArray = response.getJSONArray("stats");
                            data = jsonArray.getJSONObject(0);
                            String hpSTR = data.getString("base_stat");
                            texthp.setText("HP: " + hpSTR);

                            int hpValue = Integer.parseInt(hpSTR);
                            Slider hpSlider = findViewById(R.id.TVhp);
                            hpSlider.setValue(hpValue);

                            //ATTACK
                            jsonArray = response.getJSONArray("stats");
                            data = jsonArray.getJSONObject(1);
                            String attackSTR = data.getString("base_stat");
                            textatk.setText("ATTACK: " + attackSTR);

                            int atkValue = Integer.parseInt(attackSTR);
                            Slider atkSlider = findViewById(R.id.TVattack);
                            atkSlider.setValue(atkValue);

                            //DEFENSE
                            jsonArray = response.getJSONArray("stats");
                            data = jsonArray.getJSONObject(2);
                            String defenseSTR = data.getString("base_stat");
                            textdef.setText("DEFENSE: " + defenseSTR);

                            int defValue = Integer.parseInt(defenseSTR);
                            Slider defSlider = findViewById(R.id.TVdefense);
                            defSlider.setValue(defValue);

                            //SP ATTACK
                            jsonArray = response.getJSONArray("stats");
                            data = jsonArray.getJSONObject(3);
                            String spAttackSTR = data.getString("base_stat");
                            textspatk.setText("SP ATK: " + spAttackSTR);

                            int spatkValue = Integer.parseInt(spAttackSTR);
                            Slider spatkSlider = findViewById(R.id.TVspAttack);
                            spatkSlider.setValue(spatkValue);

                            //SP DEFENSE
                            jsonArray = response.getJSONArray("stats");
                            data = jsonArray.getJSONObject(4);
                            String spDefenseSTR = data.getString("base_stat");
                            textspdef.setText("SP DEF: " + spDefenseSTR);

                            int spdefValue = Integer.parseInt(spDefenseSTR);
                            Slider spdefSlider = findViewById(R.id.TVspDefense);
                            spdefSlider.setValue(spdefValue);

                            //SPEED
                            jsonArray = response.getJSONArray("stats");
                            data = jsonArray.getJSONObject(5);
                            String speedSTR = data.getString("base_stat");
                            textspeed.setText("SPEED: " + speedSTR);

                            int speedValue = Integer.parseInt(speedSTR);
                            Slider speedSlider = findViewById(R.id.TVspeed);
                            speedSlider.setValue(speedValue);

                            //MOVES
                            jsonArray = response.getJSONArray("moves");
                            StringBuilder movesBuilder = new StringBuilder();

                            for (int i = 0; i < 4; i++) {
                                data = jsonArray.getJSONObject(i);
                                String moveName = data.getJSONObject("move").getString("name");
                                movesBuilder.append(moveName);
                                if (i != 3) {
                                    movesBuilder.append(", ");
                                }
                            }

                            String moves = movesBuilder.toString();
                            TVmoves.setText(moves);

                            ETpokemon.setText("");



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Incorrect pokemon", Toast.LENGTH_SHORT).show();
                        ETpokemon.setText("");
                        TVindex.setText("INDEX");
                        TVname.setText("");
                        TVnamep.setText("");
                        TVtype.setText("TYPE");


                        // Reset the value of the Slider to its default value
                        Slider hpSlider = findViewById(R.id.TVhp);
                        Slider attackSlider = findViewById(R.id.TVattack);
                        Slider defenseSlider = findViewById(R.id.TVdefense);
                        Slider spAttackSlider = findViewById(R.id.TVspAttack);
                        Slider spDefenseSlider = findViewById(R.id.TVspDefense);
                        Slider speedSlider = findViewById(R.id.TVspeed);

                        int defaultValue = (int) hpSlider.getValueFrom();
                        hpSlider.setValue(defaultValue);
                        attackSlider.setValue(defaultValue);
                        defenseSlider.setValue(defaultValue);
                        spAttackSlider.setValue(defaultValue);
                        spDefenseSlider.setValue(defaultValue);
                        speedSlider.setValue(defaultValue);

                        texthp.setText("HP");
                        textatk.setText("ATTACK");
                        textdef.setText("DEFENSE");
                        textspatk.setText("SP ATK");
                        textspdef.setText("SP DEF");
                        textspeed.setText("SPEED");
                        TVmoves.setText("MOVESET");

                        IVpokeball.setImageResource(R.drawable.apokeball);
                        BTNsearch.setEnabled(true);
                    }
                });
                queue.add(request);
            }
        });

        BTNclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ETpokemon.setText("");
                TVindex.setText("INDEX");
                TVname.setText("");
                TVnamep.setText("");
                TVtype.setText("TYPE");


                // Reset the value of the Slider to its default value
                Slider hpSlider = findViewById(R.id.TVhp);
                Slider attackSlider = findViewById(R.id.TVattack);
                Slider defenseSlider = findViewById(R.id.TVdefense);
                Slider spAttackSlider = findViewById(R.id.TVspAttack);
                Slider spDefenseSlider = findViewById(R.id.TVspDefense);
                Slider speedSlider = findViewById(R.id.TVspeed);

                int defaultValue = (int) hpSlider.getValueFrom();
                hpSlider.setValue(defaultValue);
                attackSlider.setValue(defaultValue);
                defenseSlider.setValue(defaultValue);
                spAttackSlider.setValue(defaultValue);
                spDefenseSlider.setValue(defaultValue);
                speedSlider.setValue(defaultValue);

                texthp.setText("HP");
                textatk.setText("ATTACK");
                textdef.setText("DEFENSE");
                textspatk.setText("SP ATK");
                textspdef.setText("SP DEF");
                textspeed.setText("SPEED");
                TVmoves.setText("MOVESET");

                IVpokeball.setImageResource(R.drawable.apokeball);
                BTNsearch.setEnabled(true);
            }
        });


    }

}