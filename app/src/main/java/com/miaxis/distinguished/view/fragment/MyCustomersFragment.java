package com.miaxis.distinguished.view.fragment;


import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.miaxis.distinguished.R;
import com.miaxis.distinguished.adapter.EndLessOnScrollListener;
import com.miaxis.distinguished.adapter.MyCustomersAdapter;
import com.miaxis.distinguished.app.DistinguishedApp;
import com.miaxis.distinguished.event.RefreshEvent;
import com.miaxis.distinguished.model.entity.MyCustomer;
import com.miaxis.distinguished.model.retrofit.ResponseEntity;
import com.miaxis.distinguished.presenter.MyCustomersPresenter;
import com.miaxis.distinguished.presenter.contract.MyCustomersContract;
import com.miaxis.distinguished.util.ValueUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;

public class MyCustomersFragment extends BaseFragment implements MyCustomersContract.View {

    @BindView(R.id.rv_my_customers)
    RecyclerView rvMyCustomers;
    @BindView(R.id.srl_my_customers)
    SwipeRefreshLayout srlMyCustomers;

    private MyCustomersAdapter myCustomersAdapter;
    private MyCustomersContract.Presenter presenter;

    public MyCustomersFragment() {
        // Required empty public constructor
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_my_customers;
    }

    @Override
    protected void initData() {
        presenter = new MyCustomersPresenter(this, this);
        DistinguishedApp.eventBus.register(this);
    }

    @Override
    protected void initView() {
        myCustomersAdapter = new MyCustomersAdapter(new ArrayList<MyCustomer>(), getContext());
        myCustomersAdapter.setListener(new MyCustomersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final MyCustomer myCustomer = myCustomersAdapter.getMyCustomerList().get(position);
                new MaterialDialog.Builder(MyCustomersFragment.this.getContext())
                        .content("确认要解绑" + myCustomer.getCUSTNAME() + "吗?")
                        .negativeText("取消")
                        .positiveText("确认")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                presenter.unbindCustomer(myCustomer);
                            }
                        })
                        .show();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvMyCustomers.setLayoutManager(linearLayoutManager);
        rvMyCustomers.setAdapter(myCustomersAdapter);
        srlMyCustomers.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.downMyCustomers(DistinguishedApp.getInstance().getWorker().getWORKERCODE());
            }
        });
        rvMyCustomers.addOnScrollListener(new EndLessOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
            }
        });
        //下载数据
        presenter.downMyCustomers(DistinguishedApp.getInstance().getWorker().getWORKERCODE());
    }

    @Override
    public void downMyCustomersCallback(String message) {
        if (ValueUtil.DOWNLOAD_SUCCESS.equals(message)) {
//            Toasty.success(getContext(), message, Toast.LENGTH_SHORT, true).show();
            Toasty.custom(getContext(), message, getContext().getDrawable(R.drawable.ic_action_custom_toast), Color.parseColor("#8bc34a"), Toast.LENGTH_SHORT,true, true).show();
        } else {
            Toasty.error(getContext(), message, Toast.LENGTH_SHORT, true).show();
        }
        presenter.loadMyCustomers(DistinguishedApp.getInstance().getWorker().getWORKERCODE());
        if (srlMyCustomers.isRefreshing()) {
            srlMyCustomers.setRefreshing(false);
        }
    }

    @Override
    public void loadMyCustomersCallback(List<MyCustomer> customerList) {
        if (customerList == null) {
            Toasty.error(getContext(), "本地数据读取出错", Toast.LENGTH_SHORT, true).show();
            return;
        }
        myCustomersAdapter.setMyCustomerList(customerList);
        myCustomersAdapter.notifyDataSetChanged();
    }

    @Override
    public void unbindCustomerCallback(ResponseEntity responseEntity, MyCustomer myCustomer) {
        if (responseEntity == null) {
            Toasty.error(getContext(), "本地错误", Toast.LENGTH_SHORT, true).show();
        } else if (ValueUtil.SUCCESS.equals(responseEntity.getCode())){
            myCustomersAdapter.getMyCustomerList().remove(myCustomer);
            myCustomersAdapter.notifyDataSetChanged();
//            Toasty.success(getContext(), "解绑成功", Toast.LENGTH_SHORT, true).show();
            Toasty.custom(getContext(), responseEntity.getMessage(), getContext().getDrawable(R.drawable.ic_action_custom_toast), Color.parseColor("#8bc34a"), Toast.LENGTH_SHORT,true, true).show();
            DistinguishedApp.eventBus.post(new RefreshEvent());
        } else if (ValueUtil.FAILED.equals(responseEntity.getCode())) {
            Toasty.error(getContext(), responseEntity.getMessage(), Toast.LENGTH_SHORT, true).show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshEvent(RefreshEvent e) {
        presenter.downMyCustomers(DistinguishedApp.getInstance().getWorker().getWORKERCODE());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DistinguishedApp.eventBus.unregister(this);
        presenter.doDestroy();
    }

}
