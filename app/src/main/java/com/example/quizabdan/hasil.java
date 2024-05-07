package com.example.quizabdan;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class hasil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hasil);

        // Mendapatkan data dari intent
        String selectedCar = getIntent().getStringExtra("selectedCar");
        String selectedCity = getIntent().getStringExtra("selectedCity");
        int rentDays = getIntent().getIntExtra("rentDays", 0);
        double totalPrice = getIntent().getDoubleExtra("totalPrice", 0);

        // Menghitung harga rental mobil per hari berdasarkan mobil yang dipilih
        double carRentalPricePerDay;
        switch (selectedCar) {
            case "Pajero":
                carRentalPricePerDay = 2400000; // Harga rental per hari
                break;
            case "Alpard":
                carRentalPricePerDay = 1550000; // Harga rental per hari
                break;
            case "Innova":
                carRentalPricePerDay = 850000; // Harga rental per hari
                break;
            case "Brio":
                carRentalPricePerDay = 550000; // Harga rental per hari
                break;
            default:
                carRentalPricePerDay = 0;
        }

        // Menghubungkan TextView dari layout XML
        TextView selectedCarTextView = findViewById(R.id.CarTextView);
        TextView selectedCityTextView = findViewById(R.id.cityTextView);
        TextView rentalDurationTextView = findViewById(R.id.DurationTextView);
        TextView discountTextView = findViewById(R.id.discountTextView);
        TextView carRentalPriceTextView = findViewById(R.id.RentalPriceTextView); // TextView untuk harga rental mobil per hari
        TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);
        TextView cityPriceTextView = findViewById(R.id.cityPriceTextView); // TextView untuk harga tambahan kota

        // Menampilkan data pada TextView
        selectedCarTextView.setText("Mau Mobil Apa : " + selectedCar);
        selectedCityTextView.setText("Tujuan : " + selectedCity);
        rentalDurationTextView.setText("Rental Duration : " + rentDays + " days");

        // Menampilkan harga rental mobil per hari
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        String formattedCarRentalPricePerDay = formatRupiah.format(carRentalPricePerDay);
        carRentalPriceTextView.setText("Harga Rental Perhari : " + formattedCarRentalPricePerDay);

        // Menghitung dan menampilkan harga tambahan kota
        double cityPrice = 0;
        if (selectedCity.equals("Inside city")) {
            cityPrice = 135000;
        } else if (selectedCity.equals("Outside city")) {
            cityPrice = 250000;
        }
        String formattedCityPrice = formatRupiah.format(cityPrice);
        cityPriceTextView.setText("Harga Tambahan Kota: " + formattedCityPrice);

        // Apply discount based on total price
        double discount = 0;
        if (totalPrice > 10000000) { // If total price is more than 10 million
            discount = totalPrice * 0.07; // 7% discount
        } else if (totalPrice > 5000000) { // If total price is more than 5 million
            discount = totalPrice * 0.05; // 5% discount
        }


        String formattedDiscount = formatRupiah.format(discount);
        String formattedTotalPrice = formatRupiah.format(totalPrice - discount); // Mengurangi diskon dari total harga
        discountTextView.setText("Discount : " + formattedDiscount);
        totalPriceTextView.setText("Total Pembayaran : " + formattedTotalPrice);
    }
}
