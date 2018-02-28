package vn.com.mattana.dms;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.ButterKnife;
import vn.com.mattana.dms.checkin.CalendarActivity;
import vn.com.mattana.dms.checkin.CheckInActivity;
import vn.com.mattana.dms.order.ShowOrderActivity;
import vn.com.mattana.util.MRes;
import vn.com.mattana.util.RealmController;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getName();
    NavigationView navigationView;

    TextView txtName;

    TextView txtCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        createToolbar();
        createNavDraw();

    }



    private void createNavDraw() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        View viewHeader = (View) navigationView.getHeaderView(0);
        txtName = (TextView) viewHeader.findViewById(R.id.name);
        txtCode = (TextView) viewHeader.findViewById(R.id.code);

        String name = prefsHelper.get(MRes.getInstance().PREF_KEY_NAME, null);
        String code = prefsHelper.get(MRes.getInstance().PREF_KEY_CODE, null);

        txtName.setText(name);
        txtCode.setText("Mã NV: " + code);

        locationRequire();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_customer) {

        } else if (id == R.id.nav_calendar) {
            commons.startActivity(MainActivity.this, CalendarActivity.class);
        } else if (id == R.id.nav_order) {
            commons.startActivity(MainActivity.this, ShowOrderActivity.class);
        } else if (id == R.id.nav_logout) {
            commons.showAlertCancel(MainActivity.this, "Cảnh báo", "Bạn muuốn đăng xuất tài khoản này ?", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    RealmController.getInstance().clearAll();
                    prefsHelper.put(MRes.getInstance().PREF_KEY_USER, "");
                    prefsHelper.put(MRes.getInstance().PREF_KEY_TOKEN, "");
                    prefsHelper.put(MRes.getInstance().PREF_KEY_NAME, "");
                    prefsHelper.put(MRes.getInstance().PREF_KEY_CODE, "");

                    if (mService != null) {
                        mService.removeLocationUpdates();
                        prefsHelper.put(MRes.getInstance().PREF_UPDATE, false);
                    }

                    commons.startActivity(MainActivity.this, LoginActivity.class);
                    finish();
                }
            });
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public void checkInClick(View view) {
        commons.startActivity(MainActivity.this, CheckInActivity.class);
    }

    public void showCalendar(View view) {
        commons.startActivity(MainActivity.this, CalendarActivity.class);

    }



}
