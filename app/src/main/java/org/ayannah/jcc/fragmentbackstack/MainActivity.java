package org.ayannah.jcc.fragmentbackstack;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements FragmentActions,FragmentManager.OnBackStackChangedListener {

    FragmentManager manager;
    boolean onFragmentA = false;
    boolean onFragmentB = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btnMenuFragment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragmentMenu();
            }
        });
        manager = getSupportFragmentManager();
        addHomeFragment();
    }

    public void addHomeFragment (){
        FragmentHome fragmentHome = new FragmentHome();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.rv_container, fragmentHome, "HomeFragment");
        transaction.commit();
    }
    public void showFragmentMenu(){
        Constants.chosenFragment = "";
        FragmentC fragmentC = new FragmentC();
        FragmentHome fragmentHome = (FragmentHome) manager.findFragmentByTag("HomeFragment");
        FragmentTransaction transaction = manager.beginTransaction();
        if (fragmentHome!=null) {
            transaction.remove(fragmentHome);
        }
        transaction.replace(R.id.rv_container, fragmentC, "FragmentC");
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (manager.getBackStackEntryCount() != 0){
            manager.popBackStack();
        }else{
            changeFragmentBasedOnChoice();
        }
    }

    @Override
    public void onBackStackChanged() {


    }

    public void changeFragmentBasedOnChoice(){
        FragmentHome fragmentHome = new FragmentHome();
        FragmentA fragmentA = new FragmentA();
        FragmentB fragmentB = new FragmentB();
        FragmentTransaction transaction = manager.beginTransaction();
        if (Constants.chosenFragment.equals("A")) {
            transaction.replace(R.id.rv_container, fragmentA, "FragmentA");
            transaction.commit();
        }
        else if (Constants.chosenFragment.equals("B")){
            transaction.replace(R.id.rv_container, fragmentB, "FragmentB");
            transaction.commit();
        }else{
            transaction.add(R.id.rv_container, fragmentHome, "FragmentB");
            transaction.commit();
        }
    }

    @Override
    public void goToA() {
        FragmentA fragmentA = new FragmentA();
            FragmentTransaction transactions = manager.beginTransaction();
        transactions.replace(R.id.rv_container, fragmentA, "FragmentA");
        transactions.addToBackStack("AA");
        transactions.commit();
    }

    @Override
    public void goToB() {
        FragmentB fragmentB = new FragmentB();
        FragmentTransaction transactions = manager.beginTransaction();
        transactions.replace(R.id.rv_container, fragmentB, "FragmentB");
        transactions.addToBackStack("BB");
        transactions.commit();
    }
}
