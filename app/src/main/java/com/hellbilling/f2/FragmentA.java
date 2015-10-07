package com.hellbilling.f2;

import android.app.Activity;
import android.os.Bundle;
//import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import android.support.v4.app.Fragment;
import android.widget.Toast;

public class FragmentA extends Fragment {

    private OnFragmentInteractionListener mListener;

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    // Container Activity must implement this interface
    // V aktivite je prepisana metoda onButtonClick
    public interface OnFragmentInteractionListener {
        public void onButtonClick(String lala);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Najdeme view fragmentu
        final View rootView = inflater.inflate(R.layout.fragment_a, container, false);
        // Najdeme tlacitko
        Button button = (Button) rootView.findViewById(R.id.a_button1_id);
        // Definujeme metodu ktora sa vykona ked sa klikne na tlacitko
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Najdeme text
                final TextView wText=(TextView) rootView.findViewById(R.id.a_edit_text_id);
                // Vyvolame
                String fragmetnAString =  wText.getText().toString();
                Toast.makeText(getActivity().getApplicationContext(), "FragmentA  kliknute so stringom: " +fragmetnAString, Toast.LENGTH_SHORT).show();
                // chybova hlaska, nemame text
                if (fragmetnAString == null){
                    fragmetnAString = "BOHUZIAL nemame fragmetnAString";
                }
                // volame onButtonClick tam kde je prepisana metoda onButtonClick
                else {
                    mListener.onButtonClick(fragmetnAString);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    // Deklarujeme rozhranie pre aktivitu
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
