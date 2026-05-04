package com.example.notmazon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// Displays list of orders
public class PurchaseHistoryFragment extends Fragment {

    RecyclerView recyclerView;
    List<Order> orderList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_purchase_history, container, false);

        recyclerView = view.findViewById(R.id.recyclerOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        orderList = new ArrayList<>();
        orderList.add(new Order("ORDER #123", "Oct 2023", 250));
        orderList.add(new Order("ORDER #456", "Nov 2023", 120));

        recyclerView.setAdapter(new OrderAdapter(orderList));

        return view;
    }
}