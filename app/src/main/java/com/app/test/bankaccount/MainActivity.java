package com.app.test.bankaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Déclarer les variable
    EditText name;
    EditText lastName;
    EditText montant;
    EditText taux;
    EditText time;
    Button calculButton;
    Button clearButton;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //associer les variables a leurs champs
        name = (EditText) findViewById(R.id.nameEditText);
        lastName = (EditText)findViewById(R.id.lastNameEditText);
        montant = (EditText) findViewById(R.id.montantEditText);
        taux = (EditText) findViewById(R.id.tauxEditText);
        time = (EditText)findViewById(R.id.timeEditText);
        result = (TextView)findViewById(R.id.result);
        calculButton = (Button)findViewById(R.id.calculButton);
        clearButton = (Button)findViewById(R.id.clearButton);

        //Méthode pour effacer les champs des inputs
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText(" ");
                lastName.setText("");
                montant.setText("");
                taux.setText("");
                time.setText("");
            }
        });

        //Méthode pour effectuer le calcul
        calculButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Récupérer les champs saisis depuis un formulaire
                double priceMontnat = Double.parseDouble(montant.getText().toString());
                double priceTaux = Double.parseDouble(taux.getText().toString()) / 100;
                int timeValue = Integer.parseInt(time.getText().toString());
                double timeValueNegative = (~(timeValue- 1));
                double tauxMonths = priceTaux / 12;
                double upMensualite = priceMontnat * tauxMonths;
                double exponent = Math.pow(1+tauxMonths,timeValueNegative);
                double downMensualite = 1 - exponent;

                //Results
                double totalMensualite = upMensualite / downMensualite;
                double total = totalMensualite * timeValue;

                //Créer un string pour le resultat (text)
                StringBuilder builder = new StringBuilder();
                builder.append("Résumé : ");
                builder.append("\n");
                builder.append("Mensualité : ");
                builder.append(totalMensualite);
                builder.append("\n");
                builder.append("Total : ");
                builder.append(total);
                String showResult = builder.toString();

                //Afficher le résultat dans l'interface graphique
                result.setText(showResult);
            }
        });

    }

}
