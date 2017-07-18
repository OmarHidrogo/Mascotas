package com.omar_hidrogo_local.mascotas;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.omar_hidrogo_local.mascotas.adaptador.PageAdapter;
import com.omar_hidrogo_local.mascotas.fragment.FragmentPerfilDog;
import com.omar_hidrogo_local.mascotas.fragment.Fragment_RecyclerView;

import java.util.ArrayList;

//importar el id de RECYCLERVIEW asignado en activity_main
import static com.omar_hidrogo_local.mascotas.R.id.rvMascotas;
import static com.omar_hidrogo_local.mascotas.R.id.toolbar;
import static com.omar_hidrogo_local.mascotas.R.id.tabLayout;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

       setUpViewPager();

        //establecer informacion de accion bar
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        //quitar el titulo por defecto al actionbar
        if(toolbar != null){
            bar.setDisplayShowTitleEnabled(false);
        }

    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment_RecyclerView());
        fragments.add(new FragmentPerfilDog());
        return fragments;
    }

    private void setUpViewPager() {

       viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.icons8_dog_house_48);
        tabLayout.getTabAt(1).setIcon(R.drawable.icons8_dog_48);
    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.top:
                Intent intent = new Intent(this, Mascotas_Likes.class);
                this.startActivity(intent);
                break;
            case R.id.mContact:
                Intent intent2 = new Intent(this, Contacto.class);
                this.startActivity(intent2);
                break;
            case R.id.mAbout:
                Intent intent3 = new Intent(this, Acerca_de.class);
                this.startActivity(intent3);

        }
      return super.onOptionsItemSelected(item);
    }





}
