package com.example.nopaper;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nopaper.dialog.BottomSheetFragment;
import com.example.nopaper.ui.document.DocumentBottomSheetFragment;
import com.example.nopaper.ui.document.DocumentFragment;
import com.example.nopaper.ui.gallery.GalleryBottomSheetFragment;
import com.example.nopaper.ui.gallery.GalleryFragment;
import com.example.nopaper.ui.text.TextBottomSheetFragment;
import com.example.nopaper.ui.text.TextFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nopaper.databinding.ActivityMainBinding;
import com.example.nopaper.R;


public class MainActivity extends AppCompatActivity {
    // Show the Database User Data to the Nav Header Main
    TextView tv_first_name,tv_last_name,tv_email;

    //Another thing
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(view -> {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (currentFragment instanceof GalleryFragment) {
                GalleryBottomSheetFragment galleryBottomSheetFragment = new GalleryBottomSheetFragment();
                galleryBottomSheetFragment.show(getSupportFragmentManager(), "GalleryBottomSheet");
            } else if (currentFragment instanceof DocumentFragment) {
                DocumentBottomSheetFragment documentBottomSheetFragment = new DocumentBottomSheetFragment();
                documentBottomSheetFragment.show(getSupportFragmentManager(), "DocumentBottomSheet");
            } else if (currentFragment instanceof TextFragment) {
                TextBottomSheetFragment textBottomSheetFragment = new TextBottomSheetFragment();
                textBottomSheetFragment.show(getSupportFragmentManager(), "TextBottomSheet");
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_gallery, R.id.nav_document, R.id.nav_text)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(item -> {

            Fragment selectedFragment = null;
            String tag = null;;
            if (item.getItemId() == R.id.nav_gallery) {
                selectedFragment = new GalleryFragment();
                tag = "GalleryFragment";
            } else if (item.getItemId() == R.id.nav_document) {
                selectedFragment = new DocumentFragment();
                tag = "DocumentFragment";
            } else if (item.getItemId() == R.id.nav_text) {
                selectedFragment = new TextFragment();
                tag = "TextFragment";
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment, tag)
                        .commit();
                getSupportActionBar().setTitle(item.getTitle());
            }
            drawer.closeDrawer(androidx.core.view.GravityCompat.START);
            return true;

        });
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new GalleryFragment(), "GalleryFragment")
                    .commit();
        }


        // Get the header view from NavigationView
        View headerView = navigationView.getHeaderView(0);

        // Find TextView elements in the header layout
        TextView tvFirstName = headerView.findViewById(R.id.first_name);
        TextView tvLastName = headerView.findViewById(R.id.last_name);
        TextView tvEmail = headerView.findViewById(R.id.email);

        // Get Intent Extra Values (User details)
        String firstName = getIntent().getStringExtra("first_name");
        String lastName = getIntent().getStringExtra("last_name");
        String email = getIntent().getStringExtra("email");

        // Set the user details in the navigation header
        tvFirstName.setText(firstName != null ? firstName : "First Name");
        tvLastName.setText(lastName != null ? lastName : "Last Name");
        tvEmail.setText(email != null ? email : "Email");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Handle sign out
        if (id == R.id.signOut) {
            signUserOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void signUserOut() {
        Toast.makeText(MainActivity.this, "The User Signed Out", Toast.LENGTH_LONG).show();
        // Example: Redirect to login screen
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }
}