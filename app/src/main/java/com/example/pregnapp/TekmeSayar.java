package com.example.pregnapp;

import static android.graphics.Color.parseColor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TekmeSayar extends AppCompatActivity {
//button_geri_tekme_sayar
    private ImageButton buttongeriGitmeTekmeSayar;
    private Button buttonTekmeEkle;
    private BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tekme_sayar);

        buttongeriGitmeTekmeSayar=findViewById(R.id.button_geri_tekme_sayar);
        buttonTekmeEkle=findViewById(R.id.buttonTekmeEkle);

        buttongeriGitmeTekmeSayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TekmeSayar.this, MainActivity.class));
            }
        });

        buttonTekmeEkle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                bottomSheetDialog=new BottomSheetDialog(TekmeSayar.this,R.style.BottomSheetTheme);
                View sheetview= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottomsheetdesign,null);
                ImageView tekmeAddimage =sheetview.findViewById(R.id.tekmeAddimage);
                TextView tekmeAdd=sheetview.findViewById(R.id.tekmeAdd);
                Button buttonTekmeiptal=sheetview.findViewById(R.id.buttonTekmeiptal);
                Button buttonTekmeEkle2=sheetview.findViewById(R.id.buttonTekmeEkle2);
                final String[] ayse = new String[]{"0"};

                tekmeAddimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int currentValue = Integer.parseInt(tekmeAdd.getText().toString());
                        int incrementedValue = currentValue + 1;
                        ayse[0] = String.valueOf(incrementedValue);
                        tekmeAdd.setText(String.valueOf(incrementedValue));
                    }
                });
                buttonTekmeiptal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        bottomSheetDialog.dismiss();
                    }
                });
                buttonTekmeEkle2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addNewRelativeLayoutToLinearLayout(new String[]{ayse[0]});
                        bottomSheetDialog.dismiss();

                    }
                });


                bottomSheetDialog.setContentView(sheetview);
                bottomSheetDialog.show();
                bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(parseColor("#80000000")));

            }
        });
    }

    private void addNewRelativeLayoutToLinearLayout(String value[]) {
        // Find the existing LinearLayout by its ID
        LinearLayout linearLayout = findViewById(R.id.siralama); // Replace with your actual ID

        // Create a new RelativeLayout
        RelativeLayout newRelativeLayout = new RelativeLayout(this);
        LinearLayout.LayoutParams relativeLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        int marginInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                10, // 10dp
                getResources().getDisplayMetrics()
        );
        relativeLayoutParams.setMargins(0, marginInDp, 0, 0);
        // Set layout parameters for the new RelativeLayout
        newRelativeLayout.setLayoutParams(relativeLayoutParams);
        newRelativeLayout.setPadding(marginInDp,marginInDp,marginInDp,marginInDp);
        newRelativeLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.tekmeBackground));





        LinearLayout horizontalLayout = new LinearLayout(this);
        LinearLayout.LayoutParams horizontalLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // Create a new RelativeLayout
        horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
        horizontalLayout.setLayoutParams(horizontalLayoutParams);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );


        // Increment the counter for the next layout
        TextView textViewTarih = new TextView(this);
        textViewTarih.setId(View.generateViewId());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy\n   HH:mm", Locale.getDefault());
        String currentDateAndTime = sdf.format(new Date());

        LinearLayout.LayoutParams params5 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params5.setMargins(15, 0, 5, 0); // Left: 20dp, Right: 20dp
        params5.weight = 1;
        textViewTarih.setLayoutParams(params5);

        textViewTarih.setText(currentDateAndTime);
        textViewTarih.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        // Set other properties for textViewTarih as needed
        // ...


        TextView textViewHafta = new TextView(this);
        textViewHafta.setId(View.generateViewId());
        textViewHafta.setText("New Hafta");
        textViewHafta.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        LinearLayout.LayoutParams params22 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params22.setMargins(5, 0, 5, 0); // Left: 20dp, Right: 20dp
        params22.weight = 1;
        textViewHafta.setLayoutParams(params22);
        // ...

        TextView textViewTekme = new TextView(this);
        textViewTekme.setId(View.generateViewId());
        textViewTekme.setText(value[0]);
        textViewTekme.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        // Set other properties for textViewTekme as needed
        // ...

        LinearLayout.LayoutParams params33 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params33.setMargins(5, 0, 5, 0); // Left: 20dp, Right: 20dp
        params33.weight = 1;
        textViewTekme.setLayoutParams(params33);

        ImageView imageView = new ImageView(this);
        imageView.setId(View.generateViewId());
        imageView.setImageResource(R.drawable.uc_nokta_menu);

        LinearLayout.LayoutParams params44 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params44.setMargins(0, 0, 0, 0); // Left: 20dp, Right: 20dp
        params44.weight = 1;
        imageView.setLayoutParams(params44);

        PopupMenu popupMenu = new PopupMenu(this, imageView);

// R.menu.menu_sil XML dosyanızdaki menüyü yükleyin (menüyü res/menu klasörüne eklemelisiniz)
        popupMenu.getMenuInflater().inflate(R.menu.imageviewmenu, popupMenu.getMenu());

// PopupMenu'de bir öğe seçildiğinde yapılacakları belirleyin
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Sil seçeneği seçildiğinde yapılacak işlemleri buraya ekleyin
                if (item.getItemId() == R.id.menu_sil_item) {
                    if (newRelativeLayout != null) {
                        linearLayout.removeView(newRelativeLayout);
                    }
                    return true;
                }
                return false;
            }
        });

// ImageView'a tıklandığında PopupMenu'yu gösterin
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });

        // Add TextViews to the horizontal LinearLayout
        horizontalLayout.addView(textViewTarih);
        horizontalLayout.addView(textViewHafta);
        horizontalLayout.addView(textViewTekme);
        horizontalLayout.addView(imageView);

        newRelativeLayout.addView(horizontalLayout);

        // Add the newRelativeLayout to the existing LinearLayout
        linearLayout.addView(newRelativeLayout);
    }

}