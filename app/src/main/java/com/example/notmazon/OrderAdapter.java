package com.example.notmazon;

// RecyclerView adapter for orders
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    List<Order> orderList;

    public OrderAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderId, tvDate, tvPrice;

        public ViewHolder(View view) {
            super(view);
            tvOrderId = view.findViewById(R.id.tvOrderId);
            tvDate = view.findViewById(R.id.tvDate);
            tvPrice = view.findViewById(R.id.tvPrice);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.tvOrderId.setText(order.id);
        holder.tvDate.setText(order.date);
        holder.tvPrice.setText("$" + order.price);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
