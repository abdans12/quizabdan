package com.example.quizabdan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private RadioGroup carRadioGroup;
    private RadioGroup cityRadioGroup;
    private EditText rentDaysEditText;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carRadioGroup = findViewById(R.id.carRadioGroup);
        cityRadioGroup = findViewById(R.id.cityRadioGroup);
        rentDaysEditText = findViewById(R.id.rentDaysText);
        confirmButton = findViewById(R.id.confirmButton);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedCarId = carRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedCarRadioButton = findViewById(selectedCarId);
                String selectedCar = selectedCarRadioButton.getText().toString();

                int selectedCityId = cityRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedCityRadioButton = findViewById(selectedCityId);
                String selectedCity = selectedCityRadioButton.getText().toString();

                String rentDaysStr = rentDaysEditText.getText().toString();
                int rentDays = Integer.parseInt(rentDaysStr);

                double basePrice;
                switch (selectedCar) {
                    case "Pajero":
                        basePrice = 2400000;
                        break;
                    case "Alpard":
                        basePrice = 1550000;
                        break;
                    case "Innova":
                        basePrice = 850000;
                        break;
                    case "Brio":
                        basePrice = 550000;
                        break;
                    default:
                        basePrice = 0;
                }

                double totalPrice = basePrice * rentDays;

                // Add additional charges for inside or outside city
                if (selectedCity.equals("Inside city")) {
                    totalPrice += 135000;
                } else if (selectedCity.equals("Outside city")) {
                    totalPrice += 250000;
                }

                // Apply discount based on total price
                if (totalPrice > 10000000) { // If total price is more than 10 million
                    totalPrice *= 0.93; // 7% discount
                } else if (totalPrice > 5000000) { // If total price is more than 5 million
                    totalPrice *= 0.95; // 5% discount
                }

                // Example: Intent to DetailActivity
                Intent intent = new Intent(MainActivity.this, hasil.class);
                intent.putExtra("selectedCar", selectedCar);
                intent.putExtra("selectedCity", selectedCity);
                intent.putExtra("rentDays", rentDays);
                intent.putExtra("totalPrice", totalPrice);
                startActivity(intent);
            }
        });
    }
}