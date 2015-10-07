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
 //Toast.makeText(getApplicationContext(), "MainActivity: Mam predchadzajuci stav", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            FragmentA firstFragment = new FragmentA();
            Toast.makeText(getApplicationContext(), "MainActivity: startujem novy FragmentA", Toast.LENGTH_SHORT).show();
            // ????
            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
        }
    }

    // Metoda ktora je implementovana rozhranim com.hellbilling.f2.FragmentA.OnFragmentInteractionListener
    public void onButtonClick(String lala) {

        //skusime ci odchytime fragment fragmentb z activity layout
        FragmentB mc_fragment_b = (FragmentB) getSupportFragmentManager().findFragmentById(R.id.fragmentb);

        // Ak existuje (zariadenie je land)
        if (mc_fragment_b != null) {
            // If article frag is available, we're in two-pane layout...

            // Volame metodu vo FragmentB aby sme updatli obsah
            mc_fragment_b.updateText(lala+"  landscape layoutu");

        } else {
            // Nemame fragmentb (sme portretovy) musime fragmenty prepnut
//Toast.makeText(getApplicationContext(), "nemame fragment b:, vytvarame ho z main activity ", Toast.LENGTH_SHORT).show();
            // Vytvorime fragment a dame mu argument zmeneneho textu
            FragmentB newFragment = new FragmentB();
            Bundle args = new Bundle();
            // nastavime argument fragmentu
            args.putString(FragmentB.ARG_TEXT, lala);
            newFragment.setArguments(args);
            // Zacneme fragmentovu transakciu
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
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
}
