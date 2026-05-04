package com.example.notmazon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// Handles checkout and navigation
public class CheckoutFragment extends Fragment {

    EditText etName, etEmail, etPhone, etAddress;
    Button btnCheckout, btnHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        etName = view.findViewById(R.id.etName);
        etEmail = view.findViewById(R.id.etEmail);
        etPhone = view.findViewById(R.id.etPhone);
        etAddress = view.findViewById(R.id.etAddress);

        btnCheckout = view.findViewById(R.id.btnCheckout);
        btnHistory = view.findViewById(R.id.btnHistory);

        btnCheckout.setOnClickListener(v -> processCheckout());

        btnHistory.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new PurchaseHistoryFragment())
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }

    private void processCheckout() {
        String name = etName.getText().toString();

        if (name.isEmpty()) {
            Toast.makeText(getContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Order order = new Order(
                "ORDER_" + System.currentTimeMillis(),
                "Today",
                258.50
        );

        Toast.makeText(getContext(), "Order Created!", Toast.LENGTH_SHORT).show();
    }
}

    private void processCheckout() {

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String address = etAddress.getText().toString();

        // Basic validation
        if (name.isEmpty() || email.isEmpty() || address.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create Order object
        Order order = new Order(
                "ORDER_" + System.currentTimeMillis(),
                name,
                email,
                address,
                258.50
        );

        Toast.makeText(getContext(), "Order Placed!", Toast.LENGTH_LONG).show();
    }
}