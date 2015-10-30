package com.example.mauricioarce.curioso;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mauricio Arce on 28/10/2015.
 */
public class MainActiviy extends AppCompatActivity {

    private FloatingActionButton fab;
    private DrawerLayout drawerLayout;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private final List<Curiosity> curiosities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        fab = (FloatingActionButton) findViewById(R.id.btn_random);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        setupNavigationDrawerContent(navigationView);

        setComponents();

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        updateAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawers();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onCardClick(View view) {
        Intent intent = new Intent(MainActiviy.this, TextActiviy.class);
        TextView title = (TextView) view.findViewById(R.id.txt_card_title);
        TextView introduction = (TextView) view.findViewById(R.id.txt_card_introduction);
        TextView content = (TextView) view.findViewById(R.id.txt_card_content);
        TextView category = (TextView) view.findViewById(R.id.txt_card_category);
        TextView author = (TextView) view.findViewById(R.id.txt_card_author);
        TextView urlPrincipalImage = (TextView) view.findViewById(R.id.txt_img_principal);
        TextView urlFirstImage = (TextView) view.findViewById(R.id.txt_img_first);
        TextView urlSecondImage = (TextView) view.findViewById(R.id.txt_img_second);

        intent.putExtra("title", title.getText());
        intent.putExtra("introduction", introduction.getText());
        intent.putExtra("content", content.getText());
        intent.putExtra("category", category.getText());
        intent.putExtra("author", author.getText());
        intent.putExtra("urlPrincipalImage", urlPrincipalImage.getText());
        intent.putExtra("urlFirstImage", urlFirstImage.getText());
        intent.putExtra("urlSecondImage", urlSecondImage.getText());
        startActivity(intent);
    }

    public void onRandomClick(View view) {
        Random random = new Random();
        int randomIndex = random.nextInt(curiosities.size());
        List<Curiosity> curiosityList = new ArrayList<>();
        curiosityList.add(curiosities.get(randomIndex));
        updateAdapter(curiosityList);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.option_all:
                        menuItem.setChecked(true);
                        updateAdapter();
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.option_science:
                        menuItem.setChecked(true);
                        updateAdapter("Ciencia");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.option_culture:
                        menuItem.setChecked(true);
                        updateAdapter("Cultura");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.option_mistery:
                        menuItem.setChecked(true);
                        updateAdapter("Misterio");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.option_technology:
                        menuItem.setChecked(true);
                        updateAdapter("Tecnología");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.option_settings:
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent = new Intent(MainActiviy.this, TextActiviy.class);
                        startActivity(intent);
                        return true;
                }
                return true;
            }
        });
    }

    private void setComponents() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Curiosidad");
        List<ParseObject> list;
        try {
            list = query.find();
            setComponents(list);
        } catch (ParseException e) {
            Toast.makeText(getApplicationContext(), "Conexión inexistente o inestable", Toast.LENGTH_SHORT).show();
        }
    }

    private void setComponents(List<ParseObject> list) {
        for (ParseObject object : list) {
            Curiosity curiosity = new Curiosity();
            ParseFile filePrincipalImage = object.getParseFile("image");
            ParseFile fileFirstImage = object.getParseFile("firstImage");
            ParseFile fileSecondImage = object.getParseFile("secondImage");

            curiosity.setTitle(object.getString("title"));
            curiosity.setIntroduction(object.getString("introduction"));
            curiosity.setContent(object.getString("content"));
            curiosity.setCategory(object.getString("category"));
            curiosity.setAuthor(object.getString("author"));
            curiosity.setUrl(filePrincipalImage.getUrl());
            curiosity.setUrlFirstImage(fileFirstImage.getUrl());
            curiosity.setUrlSecondImage(fileSecondImage.getUrl());

            curiosities.add(curiosity);
        }
    }

    private void updateAdapter() {
        adapter = new CuriosityAdapter(curiosities, getApplicationContext());
        recycler.setAdapter(adapter);
    }

    private void updateAdapter(List<Curiosity> curiosityList) {
        adapter = new CuriosityAdapter(curiosityList, getApplicationContext());
        recycler.setAdapter(adapter);
    }

    private void updateAdapter(String category) {
        List<Curiosity> filterCuriosities = new ArrayList<>();
        for (Curiosity curiosity : curiosities) {
            if (curiosity.getCategory().equals(category)) {
                filterCuriosities.add(curiosity);
            }
        }
        adapter = new CuriosityAdapter(filterCuriosities, getApplicationContext());
        recycler.setAdapter(adapter);
    }
}
