package fr.avp41.calculate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class CalculeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calcul);

        EditText sideA = findViewById(R.id.sideA);
        EditText sideB = findViewById(R.id.sideB);
        EditText sideC = findViewById(R.id.sideC);
        Button calculateButton = findViewById(R.id.calculateButton);
        Button resetButton = findViewById(R.id.resetButton);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button homeButton = findViewById(R.id.homeButton);
        TextView resultTextView = findViewById(R.id.resultTextView);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculeActivity.this,MainActivity.class));
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sideA.setText("");
                sideB.setText("");
                sideC.setText("");
                resultTextView.setText("");
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String aStr = sideA.getText().toString();
                String bStr = sideB.getText().toString();
                String cStr = sideC.getText().toString();

                if (aStr.isEmpty() || bStr.isEmpty() || cStr.isEmpty()) {
                    Toast.makeText(CalculeActivity.this, "Veuillez entrer tous les côtés", Toast.LENGTH_SHORT).show();
                    return;
                }

                double a = Double.parseDouble(aStr);
                double b = Double.parseDouble(bStr);
                double c = Double.parseDouble(cStr);

                if (a + b > c && a + c > b && b + c > a) {
                    double angleB = calculateAngle(a, c, b);

                    String result = String.format("L'angle du mur est de: %.2f°", angleB);
                    resultTextView.setText(result);
                } else {
                    Toast.makeText(CalculeActivity.this, "Les côtés ne forment pas un triangle", Toast.LENGTH_SHORT).show();
                }
                closeKeyboard();
            }
        });


    }

    private double calculateAngle(double a, double b, double opposite) {
        return Math.toDegrees(Math.acos((a*a + b*b - opposite*opposite) / (2 * a * b)));
    }

    private void closeKeyboard()
    {
        // this will give us the view
        // which is currently focus
        // in this layout
        View view = this.getCurrentFocus();

        // if nothing is currently
        // focus then this will protect
        // the app from crash
        if (view != null) {

            // now assign the system
            // service to InputMethodManager
            InputMethodManager manager
                    = (InputMethodManager)
                    getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            manager
                    .hideSoftInputFromWindow(
                            view.getWindowToken(), 0);
        }
    }

}