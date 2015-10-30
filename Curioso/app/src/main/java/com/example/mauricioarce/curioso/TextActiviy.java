package com.example.mauricioarce.curioso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Mauricio Arce on 29/10/2015.
 */
public class TextActiviy extends AppCompatActivity {

    private TextView title;
    private TextView introduction;
    private TextView content;
    private TextView category;
    private TextView author;
    private ImageView urlPrincipalImage;
    private ImageView urlFirstImage;
    private ImageView urlSecondImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.curiosity_layout);

        title = (TextView) findViewById(R.id.curiosity_title);
        introduction = (TextView) findViewById(R.id.curiosity_introduction);
        content = (TextView) findViewById(R.id.curiosity_content);
        category = (TextView) findViewById(R.id.curiosity_category);
        author = (TextView) findViewById(R.id.curiosity_author);
        urlPrincipalImage = (ImageView) findViewById(R.id.curiosity_image_principal);
        urlFirstImage = (ImageView) findViewById(R.id.curiosity_first_image);
        urlSecondImage = (ImageView) findViewById(R.id.curiosity_second_image);

        Intent intent = getIntent();
        String tTitle = intent.getStringExtra("title");
        String tIntroduction = intent.getStringExtra("introduction");
        String tContent = intent.getStringExtra("content");
        String tCategory = intent.getStringExtra("category");
        String tAuthor = intent.getStringExtra("author");
        String tUrlPrincipalImage = intent.getStringExtra("urlPrincipalImage");
        String tUrlFirstImage = intent.getStringExtra("urlFirstImage");
        String tUrlSecondImage = intent.getStringExtra("urlSecondImage");

        title.setText(tTitle);
        introduction.setText(tIntroduction);
        content.setText(tContent);
        category.setText(String.format("%s: %s", "Categoría", tCategory));
        author.setText(String.format("%s: %s", "Autor", tAuthor));

        Picasso.with(getApplicationContext()).load(tUrlPrincipalImage).into(urlPrincipalImage);
        Picasso.with(getApplicationContext()).load(tUrlFirstImage).into(urlFirstImage);
        Picasso.with(getApplicationContext()).load(tUrlSecondImage).into(urlSecondImage);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(tTitle);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextActiviy.this.finish();
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
