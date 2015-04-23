package com.hellbilling.f2;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends FragmentActivity implements com.hellbilling.f2.FragmentA.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            FragmentA firstFragment = new FragmentA();

            // ????
            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Metoda ktora je implementovana rozhranim com.hellbilling.f2.FragmentA.OnFragmentInteractionListener
    public void onButtonClick(String lala) {

        Toast.makeText(getApplicationContext(), "FragmentA  kliknute so stringom: "+lala, Toast.LENGTH_SHORT).show();

        //Odchytime fragment fragmentb z activity layout
        FragmentB articleFrag = (FragmentB) getSupportFragmentManager().findFragmentById(R.id.fragmentb);

        // Ak existuje (zariadenie je land)
        if (articleFrag != null) {
            // If article frag is available, we're in two-pane layout...

            // Volame metodu vo FragmentB aby sme updatli obsah
            articleFrag.updateArticleView(lala);

        } else {
            // Nemame fragmentb (sme portretovy) musime fragmenty prepnut

            // Vytvorime fragment a dame mu argument zmeneneho textu
            FragmentB newFragment = new FragmentB();
            Bundle args = new Bundle();
            args.putString(FragmentB.ARG_POSITION, lala);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }
}
