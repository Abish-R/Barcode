package com.aipl.leecodemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class Competition_reporting extends AppCompatActivity {
    private List<Competition> competitionList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CompetitionAdapter cAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_reporting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Daily Competitor Reporting");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        cAdapter = new CompetitionAdapter(competitionList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cAdapter);

        prepareCompetitionData();
    }

    private void prepareCompetitionData() {

        Competition competition = new Competition("HTC", "");
        competitionList.add(competition);

        competition = new Competition("SONY", "");
        competitionList.add(competition);

        competition = new Competition("APPLE", "");
        competitionList.add(competition);

        competition = new Competition("SAMSUNG", "");
        competitionList.add(competition);

        competition = new Competition("MICROMAX", "");
        competitionList.add(competition);

        competition = new Competition("OTHER MOBILES", "");
        competitionList.add(competition);



        cAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            //  onBackPressed();

            startActivity(new Intent(this,DAR_Activity.class));
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();


        startActivity(new Intent(this, DAR_Activity.class));
        finish();

    }
    }
