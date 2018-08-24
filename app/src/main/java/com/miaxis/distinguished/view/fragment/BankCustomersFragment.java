package com.miaxis.distinguished.view.fragment;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.miaxis.distinguished.R;
import com.miaxis.distinguished.adapter.EndLessOnScrollListener;
import com.miaxis.distinguished.adapter.BankCustomersAdapter;
import com.miaxis.distinguished.app.DistinguishedApp;
import com.miaxis.distinguished.event.RefreshEvent;
import com.miaxis.distinguished.model.entity.Customer;
import com.miaxis.distinguished.model.retrofit.ResponseEntity;
import com.miaxis.distinguished.presenter.BankCustomersPresenter;
import com.miaxis.distinguished.presenter.contract.BankCustomersContract;
import com.miaxis.distinguished.util.ValueUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;

public class BankCustomersFragment extends BaseFragment implements BankCustomersContract.View {

    @BindView(R.id.rv_bank_customers)
    RecyclerView rvBankCustomers;
    @BindView(R.id.srl_bank_customers)
    SwipeRefreshLayout srlBankCustomers;

    private BankCustomersAdapter bankCustomersAdapter;
    private BankCustomersContract.Presenter presenter;
    
    public BankCustomersFragment() {
        // Required empty public constructor
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_bank_customers;
    }

    @Override
    protected void initData() {
        presenter = new BankCustomersPresenter(this, this);
        DistinguishedApp.eventBus.register(this);
    }

    @Override
    protected void initView() {
        bankCustomersAdapter = new BankCustomersAdapter(new ArrayList<Customer>(), getContext());
        bankCustomersAdapter.setListener(new BankCustomersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final Customer customer = bankCustomersAdapter.getCustomerList().get(position);
                new MaterialDialog.Builder(BankCustomersFragment.this.getContext())
                        .content("确认要认领" + customer.getCUSTNAME() + "吗?")
                        .negativeText("取消")
                        .positiveText("确认")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                presenter.bindCustomer(customer);
                            }
                        })
                        .show();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvBankCustomers.setLayoutManager(linearLayoutManager);
        rvBankCustomers.setAdapter(bankCustomersAdapter);
        srlBankCustomers.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.downBankCustomers(DistinguishedApp.getInstance().getWorker().getORGCODE());
            }
        });
        rvBankCustomers.addOnScrollListener(new EndLessOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
            }
        });
        //下载数据
        presenter.downBankCustomers(DistinguishedApp.getInstance().getWorker().getORGCODE());
    }

    @Override
    public void downBankCustomersCallback(String message) {
        if (ValueUtil.DOWNLOAD_SUCCESS.equals(message)) {
//            Toasty.success(getContext(), message, Toast.LENGTH_SHORT, true).show();
            Toasty.custom(getContext(), message, getContext().getDrawable(R.drawable.ic_action_custom_toast), Color.parseColor("#8bc34a"), Toast.LENGTH_SHORT,true, true).show();
        } else {
            Toasty.error(getContext(), message, Toast.LENGTH_SHORT, true).show();
        }
        presenter.loadBankCustomers(DistinguishedApp.getInstance().getWorker().getORGCODE());
        if (srlBankCustomers.isRefreshing()) {
            srlBankCustomers.setRefreshing(false);
        }
    }

    @Override
    public void loadBankCustomersCallback(List<Customer> customerList) {
        if (customerList == null) {
            Toasty.error(getContext(), "本地数据读取出错", Toast.LENGTH_SHORT, true).show();
            return;
        }
        bankCustomersAdapter.setCustomerList(customerList);
        bankCustomersAdapter.notifyDataSetChanged();
    }

    @Override
    public void bindCustomerCallback(ResponseEntity responseEntity, Customer customer) {
        if (responseEntity == null) {
            Toasty.error(getContext(), "本地错误", Toast.LENGTH_SHORT, true).show();
        } else if (ValueUtil.SUCCESS.equals(responseEntity.getCode())) {
            bankCustomersAdapter.getCustomerList().remove(customer);
            bankCustomersAdapter.notifyDataSetChanged();
//            Toasty.success(getContext(), "认领成功", Toast.LENGTH_SHORT, true).show();
            Toasty.custom(getContext(), responseEntity.getMessage(), getContext().getDrawable(R.drawable.ic_action_custom_toast), Color.parseColor("#8bc34a"), Toast.LENGTH_SHORT,true, true).show();
            DistinguishedApp.eventBus.post(new RefreshEvent());
        } else if (ValueUtil.FAILED.equals(responseEntity.getCode())) {
            Toasty.error(getContext(), responseEntity.getMessage(), Toast.LENGTH_SHORT, true).show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshEvent(RefreshEvent e) {
        presenter.downBankCustomers(DistinguishedApp.getInstance().getWorker().getORGCODE());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DistinguishedApp.eventBus.unregister(this);
        presenter.doDestroy();
    }

}