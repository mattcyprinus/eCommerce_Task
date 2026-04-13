package com.example.ecommerce_task;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NavigationActivity extends AppCompatActivity {

    private LinearLayout[] navItems;
    private ImageView[] navIcons;
    private TextView[] navTexts;
    private Fragment[] fragments;
    private Fragment active;
    private final int COLOR_ACTIVE   = 0xFF004252;
    private final int COLOR_INACTIVE = 0x50536166;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_navigation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        setupFragments();
        setupNav();
        setupFab();
    }

    private void init(){
        navItems = new LinearLayout[]{
                findViewById(R.id.nav_home),
                findViewById(R.id.nav_search),
                findViewById(R.id.nav_cart),
                findViewById(R.id.nav_profile)
        };
        navIcons = new ImageView[]{
                findViewById(R.id.icon_home),
                findViewById(R.id.icon_search),
                findViewById(R.id.icon_cart),
                findViewById(R.id.icon_profile)
        };
        navTexts = new TextView[]{
                findViewById(R.id.text_home),
                findViewById(R.id.text_search),
                findViewById(R.id.text_cart),
                findViewById(R.id.text_profile)
        };

        fragments = new Fragment[]{
                new HomeFragment(),
                new SearchFragment(),
                new CartFragment(),
                new ProfileFragment()
        };
    }
    private void setupFragments() {
        var ft = getSupportFragmentManager().beginTransaction();

        for (int i = 0; i < fragments.length; i++) {
            ft.add(R.id.fragmentContainer, fragments[i]);
            if (i != 0) ft.hide(fragments[i]);
        }

        ft.commit();
        active = fragments[0];
        setNavActive(0);
    }
    private void setupFab() {
        FloatingActionButton fab = findViewById(R.id.fab_cart);
        fab.setOnClickListener(v -> switchTab(2));
    }

    private void setupNav() {
        for (int i = 0; i < navItems.length; i++) {
            int index = i;
            navItems[i].setOnClickListener(v -> switchTab(index));
        }
    }

    private void switchTab(int index) {
        if (fragments[index] == active) return;

        getSupportFragmentManager().beginTransaction()
                .hide(active)
                .show(fragments[index])
                .commit();

        active = fragments[index];

        setNavActive(index);
    }
    private void setNavActive(int index) {
        for (int i = 0; i < navItems.length; i++) {
            boolean isActive = (i == index);
            int color = isActive ? COLOR_ACTIVE : COLOR_INACTIVE;

            navIcons[i].setImageTintList(ColorStateList.valueOf(color));

            navTexts[i].setTextColor(color);
            navTexts[i].setTypeface(null, isActive ? Typeface.BOLD : Typeface.NORMAL);

        }
    }
}
