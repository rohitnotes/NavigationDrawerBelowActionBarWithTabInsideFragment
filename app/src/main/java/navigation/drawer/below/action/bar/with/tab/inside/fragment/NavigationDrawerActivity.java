package navigation.drawer.below.action.bar.with.tab.inside.fragment;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.MenuItem;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import de.hdodenhof.circleimageview.CircleImageView;
import android.content.res.Configuration;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.util.List;
import android.widget.ListView;

public class NavigationDrawerActivity extends AppCompatActivity {

    //******* tool bar variable**********
    private Toolbar toolbar;
    public static ActionBar actionbar;

    //********** drawer item using list view ***********************

    private ListView listView;
    private MenuItemAdapter menuItemAdapter;
    private List<DrawerMenuItem> menuItemValue = new ArrayList<DrawerMenuItem>();;

    //******* drawer other variable *******
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    //******* index to identify current navigation menu item *******
    public static int navigationItemIndex = 0;

    //******* drawer header variable *********
    private View navigationDrawerHeaderView;
    public TextView userName,userEmail;
    public CircleImageView userImage;

    //******* floating action button variable *********
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        setupToolbar();         //***** call setupToolbar Function ********
        setupDrawer();          //***** call setupDrawer Function ********
        setupHeader();          //***** call setupHeader Function ********
        setupDrawerMenuList(); //***** call setupDrawerMenuList Function ********

        //********* Floating Button *********
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Welcome To First Navigation Drawer", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //***** set default fragment ********
        refreshFragment(new HomeFragment());
        setActionBarTitle("Home");
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    //************* setup toolbar ****************
    private void setupToolbar()
    {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        actionbar = getSupportActionBar();
        assert actionbar != null;
    }
    //************* end ****************

    //************* setup drawer ****************
    private void setupDrawer()
    {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerClosed(View drawerView)
            {

            }
            @Override
            public void onDrawerOpened(View drawerView)
            {

            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
    //************* end ****************

    //************* setup header ****************
    private void setupHeader()
    {
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationDrawerHeaderView = navigationView.getHeaderView(0);

        userImage    = navigationDrawerHeaderView.findViewById(R.id.userProfileImage);
        userName     = navigationDrawerHeaderView.findViewById(R.id.userName);
        userEmail    =navigationDrawerHeaderView.findViewById(R.id.userEmail);

        navigationDrawerHeaderView.findViewById(R.id.header).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Header Click", Toast.LENGTH_LONG).show();
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }
    //************* end ****************

    //************* setup drawer menu list ****************
    private void setupDrawerMenuList()
    {
        listView = (ListView) findViewById(R.id.list);

        menuItemValue.add(new DrawerMenuItem("Home", "Home location", R.drawable.ic_menu_camera));
        menuItemValue.add(new DrawerMenuItem("Gallery", "Your photos", R.drawable.ic_menu_gallery));
        menuItemValue.add(new DrawerMenuItem("Slideshow", "Your video", R.drawable.ic_menu_slideshow));
        menuItemValue.add(new DrawerMenuItem("Tools", "Tools kit", R.drawable.ic_menu_manage));
        menuItemValue.add(new DrawerMenuItem("Share", "Share information", R.drawable.ic_menu_share));
        menuItemValue.add(new DrawerMenuItem("Send", "Send email", R.drawable.ic_menu_send));

        menuItemAdapter= new MenuItemAdapter(this, menuItemValue);
        listView.setAdapter(menuItemAdapter);
        menuItemAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                switch(position) {
                    case 0:
                        navigationItemIndex = 0;
                        HomeFragment homeFragment= new HomeFragment();
                        refreshFragment(homeFragment);
                        break;
                    case 1:
                        navigationItemIndex = 1;
                        GalleryFragment galleryFragment= new GalleryFragment();
                        setFragment(galleryFragment);
                        break;
                    case 2:
                        navigationItemIndex = 2;
                        Toast.makeText(getApplicationContext(), " navigationItemIndex = 2 Item Click", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        navigationItemIndex = 3;
                        Toast.makeText(getApplicationContext(), " navigationItemIndex = 3 Item Click", Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        navigationItemIndex = 4;
                        Toast.makeText(getApplicationContext(), "navigationItemIndex = 4 Item Click", Toast.LENGTH_LONG).show();
                        break;
                    case 5:
                        navigationItemIndex = 5;
                        //Display Share Via dialogue
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType(NavigationDrawerConstants.SHARE_TEXT_TYPE);
                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, NavigationDrawerConstants.SHARE_TITLE);
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, NavigationDrawerConstants.SHARE_MESSAGE);
                        startActivity(Intent.createChooser(sharingIntent, NavigationDrawerConstants.SHARE_VIA));
                        Toast.makeText(getApplicationContext(), " navigationItemIndex = 5 Item Click", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Wrong Item Click", Toast.LENGTH_LONG).show();
                }
                setActionBarTitle(menuItemValue.get(position).getTitle());
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }
    //************* end ****************

    //*************** set fragment method ****************
    public void setFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        //*************** show or hide the floatingActionButton ****************
        floatingActionButton();
    }
    //****** end ****************

    //*************** refresh fragment method ****************
    public void refreshFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        setActionBarTitle("Home");

        if (this.drawerLayout.isDrawerOpen(GravityCompat.START))
        {
                this.drawerLayout.closeDrawer(GravityCompat.START);
        }

        //*************** show or hide the floatingActionButton ****************
        floatingActionButton();
    }
    //****** end ****************

    //****** get selected menu ****************
    private void getSelectedNavigationMenu()
    {
        navigationView.getMenu().getItem(navigationItemIndex).setChecked(true);
    }
    //****** end ****************

    //****** set action bar title ****************
    public void setActionBarTitle(String title)
    {
        actionbar.setTitle(title);
    }
    //****** end ****************

    //****** show or hide the floatingActionButton ****************
    private void floatingActionButton()
    {
        if (navigationItemIndex == 0)
            floatingActionButton.show();
        else
            floatingActionButton.hide();
    }
    //****** end ****************

    //******* main menu **********
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_main_message:
                Toast.makeText(getApplicationContext(), "Message", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_main_notifications:
                Toast.makeText(getApplicationContext(), "Notification", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //******* main menu end **********

    //*************** if all fragment is finish ****************
    @Override
    public void onBackPressed() {
        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        }
        else if(fragments == 1)
        {
            ViewGroup viewGroup = findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(this).inflate(R.layout.exit_alert_dialog, viewGroup, false);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(dialogView);
            builder.setCancelable(false);
            final AlertDialog alertDialog = builder.create();
            alertDialog.show();

            Button no = dialogView.findViewById(R.id.no_button);
            Button yes =dialogView.findViewById(R.id.yes_button);

            no.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    alertDialog.dismiss();
                }
            });

            yes.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    alertDialog.dismiss();
                    NavigationDrawerActivity.this.finish();
                }
            });
        } else {
            super.onBackPressed();
        }
    }
    //****** end ****************
}