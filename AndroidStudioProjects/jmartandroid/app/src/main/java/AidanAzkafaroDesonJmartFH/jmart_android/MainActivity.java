package AidanAzkafaroDesonJmartFH.jmart_android;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import androidx.appcompat.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import AidanAzkafaroDesonJmartFH.jmart_android.model.Product;


public class MainActivity extends AppCompatActivity implements ProductFragment.ProductFragmentListener {

    //widgets
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;

    MenuItem create, search;
    SearchView searchView;
    Menu menu;

    private FragmentManager mFragmentManager;
    private ProductFragment productFragment;

    ArrayAdapter<Product> listViewAdapterMain;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager2);

        mFragmentManager = getSupportFragmentManager();

        adapter = new FragmentAdapter(mFragmentManager, getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Products"));
        tabLayout.addTab(tabLayout.newTab().setText("Filter"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }

    @Override
    public void getProductList(ArrayAdapter<Product> listViewAdapter) {
        listViewAdapterMain = listViewAdapter;
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        create = menu.findItem(R.id.create);
        search = menu.findItem(R.id.search);
        searchView = (SearchView) search.getActionView();
        searchView.setQueryHint("Butuh apa hari ini?");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ProductFragment.listViewAdapter.getFilter().filter(newText);

                return false;
            }
        });

        //only show create button if the account has a store
        create.setVisible(LoginActivity.getLoggedAccount().store != null);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.person:
                Intent intentPerson = new Intent(this, AboutMeActivity.class);
                startActivity(intentPerson);
                return true;

            case R.id.create:
                Intent intentCreate = new Intent(this, CreateProductActivity.class);
                startActivity(intentCreate);
                return true;

            case R.id.history:
                Intent intentHistory = new Intent(this, InvoiceHistoryActivityAccount.class);
                startActivity(intentHistory);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}