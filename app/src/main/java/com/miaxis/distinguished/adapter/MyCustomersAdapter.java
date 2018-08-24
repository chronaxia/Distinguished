package com.miaxis.distinguished.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.miaxis.distinguished.R;
import com.miaxis.distinguished.model.entity.MyCustomer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tang.yf on 2018/8/14.
 */

public class MyCustomersAdapter extends RecyclerView.Adapter<MyCustomersAdapter.ViewHolder> {

    public void setMyCustomerList(List<MyCustomer> myCustomerList) {
        this.myCustomerList = myCustomerList;
    }

    public List<MyCustomer> getMyCustomerList() {
        return myCustomerList;
    }

    private OnItemClickListener listener;
    private List<MyCustomer> myCustomerList;
    private Context context;

    public MyCustomersAdapter(List<MyCustomer> myCustomerList, Context context) {
        this.myCustomerList = myCustomerList;
        this.context = context;
    }

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_my_customer, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        MyCustomer myCustomer = myCustomerList.get(position);
        Glide.with(context).load(myCustomer.getHEADIMAGE()).into(holder.ivMymyCustomersHeader);
        holder.tvMymyCustomersName.setText(myCustomer.getCUSTNAME());
        holder.tvMymyCustomersCardNumber.setText(myCustomer.getIDCARD());
        holder.tvMymyCustomersMobile.setText(myCustomer.getMOBILE());
        holder.btnMymyCustomerDisposable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(holder.btnMymyCustomerDisposable, holder.getLayoutPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (myCustomerList != null) {
            return myCustomerList.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_my_customers_header)
        ImageView ivMymyCustomersHeader;
        @BindView(R.id.tv_my_customers_name)
        TextView tvMymyCustomersName;
        @BindView(R.id.tv_my_customers_card_number)
        TextView tvMymyCustomersCardNumber;
        @BindView(R.id.tv_my_customers_mobile)
        TextView tvMymyCustomersMobile;
        @BindView(R.id.btn_my_customer_disposable)
        Button btnMymyCustomerDisposable;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
