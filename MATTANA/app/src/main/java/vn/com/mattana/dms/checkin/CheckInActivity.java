package vn.com.mattana.dms.checkin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.mattana.adapter.ViewPagerAdapter;
import vn.com.mattana.dms.BaseActivity;
import vn.com.mattana.dms.R;
import vn.com.mattana.model.api.ResultInfo;
import vn.com.mattana.model.api.checkin.CWorkInfo;
import vn.com.mattana.model.api.checkin.CWorkResult;
import vn.com.mattana.util.MRes;
import vn.com.mattana.view.CheckInPlanFragment;
import vn.com.mattana.view.CheckOutPlanFragment;

public class CheckInActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    int SHOW_TASK = 1;
    public List<CWorkInfo> workInfos;
    CheckInPlanFragment inPlanFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        ButterKnife.bind(this);

        createToolbar();

        workInfos = new ArrayList<>();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        createTab();
        makeRequest();
    }

    private void makeRequest() {
        showpDialog();
        workInfos.clear();
        String user = prefsHelper.get(MRes.getInstance().PREF_KEY_USER, null);
        Call<CWorkResult> call = apiInterface().showWorks(user);

        call.enqueue(new Callback<CWorkResult>() {
            @Override
            public void onResponse(Call<CWorkResult> call, Response<CWorkResult> response) {

                if (response.body() != null) {

                    if ("1".equals(response.body().getId())) {
                       // workInfos.addAll(response.body().getWorks());
                        inPlanFragment.refestData(response.body().getWorks());
                    } else {
                        commons.makeToast(CheckInActivity.this, response.body().getMsg()).show();
                    }

                }

                hidepDialog();
            }

            @Override
            public void onFailure(Call<CWorkResult> call, Throwable t) {
                hidepDialog();
                commons.showToastDisconnect(CheckInActivity.this);
            }
        });
    }

    public void makeCheckIn(final String workId) {
        showpDialog();
        Call<ResultInfo> call = apiInterface().checkIn(user, token, workId);

        call.enqueue(new Callback<ResultInfo>() {
            @Override
            public void onResponse(Call<ResultInfo> call, Response<ResultInfo> response) {

                if (response.body() != null) {
                    if ("1".equals(response.body().getId())) {
                        Intent intent = commons.createIntent(CheckInActivity.this, CheckInTaskActivity.class);
                        intent.putExtra("TimeIn", response.body().getMsg());
                        intent.putExtra("workId", workId);
                        startActivityForResult(intent, SHOW_TASK);
                    } else {
                        commons.makeToast(CheckInActivity.this, response.body().getMsg()).show();
                    }
                }

                hidepDialog();
            }

            @Override
            public void onFailure(Call<ResultInfo> call, Throwable t) {
                hidepDialog();
                commons.showToastDisconnect(CheckInActivity.this);
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
         inPlanFragment = new CheckInPlanFragment();
        inPlanFragment.setData(CheckInActivity.this);
        adapter.addFragment(inPlanFragment, "DANH SÁCH GHÉ THĂM");


        CheckOutPlanFragment otherFragment = new CheckOutPlanFragment();
        adapter.addFragment(otherFragment, "NGOÀI KẾ HOẠCH");
        viewPager.setAdapter(adapter);
    }

    private void createTab() {
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SHOW_TASK) {
            if (resultCode == RESULT_OK) {
                makeRequest();
            } else if (resultCode == RESULT_CANCELED) {

            }
        }
    }
}
