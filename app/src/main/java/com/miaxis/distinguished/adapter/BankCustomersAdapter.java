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
import com.miaxis.distinguished.model.entity.Customer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tang.yf on 2018/8/14.
 */

public class BankCustomersAdapter extends RecyclerView.Adapter<BankCustomersAdapter.ViewHolder> {

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    private OnItemClickListener listener;
    private List<Customer> customerList;
    private Context context;

    public BankCustomersAdapter(List<Customer> customerList, Context context) {
        this.customerList = customerList;
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
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_bank_customer, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Customer customer = customerList.get(position);
        Glide.with(context).load(customer.getHEADIMAGE()).into(holder.ivBankCustomersHeader);
        holder.tvBankCustomersName.setText(customer.getCUSTNAME());
        holder.tvBankCustomersCardNumber.setText(customer.getIDCARD());
        holder.tvBankCustomersMobile.setText(customer.getMOBILE());
        holder.btnBankCustomerDisposable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(holder.btnBankCustomerDisposable, holder.getLayoutPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (customerList != null) {
            return customerList.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_bank_customers_header)
        ImageView ivBankCustomersHeader;
        @BindView(R.id.tv_bank_customers_name)
        TextView tvBankCustomersName;
        @BindView(R.id.tv_bank_customers_card_number)
        TextView tvBankCustomersCardNumber;
        @BindView(R.id.tv_bank_customers_mobile)
        TextView tvBankCustomersMobile;
        @BindView(R.id.btn_bank_customer_subscribe)
        Button btnBankCustomerDisposable;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}