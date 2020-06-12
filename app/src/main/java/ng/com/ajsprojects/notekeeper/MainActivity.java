package ng.com.ajsprojects.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements  NavigationView.OnNavigationItemSelectedListener{
        private NoteRecyclerAdapter mNoteRecyclerAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                Toolbar toolbar = findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);

                FloatingActionButton fab = findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, NoteActivity.class));
                    }
                });

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
                );
                drawer.setDrawerListener(toggle);
                toggle.syncState();

                NavigationView navigationView = findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(this);

                intializeDisplayContent();
        }

        @Override
        protected void onResume() {
                super.onResume();
                mNoteRecyclerAdapter.notifyDataSetChanged();
        }

        private void intializeDisplayContent() {
        final RecyclerView recyclerNotes = findViewById(R.id.list_items);
        final LinearLayoutManager notesLayoutManager = new LinearLayoutManager(this);
                recyclerNotes.setLayoutManager(notesLayoutManager);

                List<NoteInfo> notes = DataManager.getInstance().getNotes();
                mNoteRecyclerAdapter = new NoteRecyclerAdapter(this, notes);
                recyclerNotes.setAdapter(mNoteRecyclerAdapter);
        }

        @Override
        public void onBackPressed() {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen((GravityCompat.START)){
                drawer.closeDrawer(GravityCompat.START);
                } else {
                    super.onBackPressed();
                }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the me`nu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.main, menu);
                return true;
        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
        }

        @Override
        public void onPointerCaptureChanged(boolean hasCapture) {

        }
}