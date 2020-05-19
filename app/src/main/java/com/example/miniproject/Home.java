package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;

import com.example.miniproject.Interface.ItemClickListener;
import com.example.miniproject.Model.Category;
import com.example.miniproject.Model.Order;
import com.example.miniproject.ViewHolder.MenuViewHolder;
import com.example.miniproject.common.Common;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference category;
    TextView textFullName;
    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter;
   // StorageReference storageReference;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);


        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");
        //storageReference = FirebaseStorage.getInstance().getReference();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent = new Intent(Home.this,Cart.class);
                startActivity(cartIntent);
            }
        });
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        setupDrawerContent(navigationView);
       //navigationView.setNavigationItemSelectedListener(R.id.);
        View headerView = navigationView.getHeaderView(0);
        textFullName = (TextView)headerView.findViewById(R.id.textFullName);
        textFullName.setText(Common.currentUser.getName());
        recycler_menu = (RecyclerView)findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);
        
        
        loadMenu();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
      /* mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();*/
        //NavController navController = Navigation.findNavController(this, R.id.nav_controller_view_tag);
       // NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
      //  NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void loadMenu() {
        adapter = new  FirebaseRecyclerAdapter<Category, MenuViewHolder> (Category.class,R.layout.menu_item,MenuViewHolder.class,category) {
            @Override
            protected void populateViewHolder(MenuViewHolder menuViewHolder, Category category, int i) {
                menuViewHolder.textMenuName.setText(category.getName());
                Picasso.with(getBaseContext()).load(category.getImage()).into(menuViewHolder.imageView);
                final Category clickItem =  category;
                menuViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent foodList= new Intent(Home.this, FoodList.class);
                        foodList.putExtra("CategoryId",adapter.getRef(position).getKey());
                        startActivity(foodList);
                    }
                });

            }
        };
        recycler_menu.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();


        }
       // Toast.makeText(this,"back key is pressed", Toast.LENGTH_SHORT).show();

    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_menu){

        }else if (id == R.id.nav_cart){
            Intent cartIntent =  new Intent(Home.this,Cart.class);
            startActivity(cartIntent);
        }else if (id == R.id.nav_order){
            Intent orderIntent = new Intent(Home.this, Order.class);
            startActivity(orderIntent);
        }else if ( id == R.id.nav_log_out){
            Intent signIn = new Intent(Home.this,SignIn.class);
            signIn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(signIn);
        }
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_controller_view_tag);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        if (id == R.id.nav_menu){

                        }else if (id == R.id.nav_cart){
                            Intent cartIntent =  new Intent(Home.this,Cart.class);
                            startActivity(cartIntent);
                        }else if (id == R.id.nav_order){
                            Intent orderIntent = new Intent(Home.this, OrderStatus.class);
                            startActivity(orderIntent);
                        }else if ( id == R.id.nav_log_out){
                            Intent signIn = new Intent(Home.this,SignIn.class);
                            signIn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(signIn);
                        }
                        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        return true;

                    }
                });
    }


}
