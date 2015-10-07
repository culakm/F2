package com.hellbilling.f2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentB extends Fragment {

    final static String ARG_TEXT = "position";
    String mText1 = "mCurrentPositionValue";
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_b, container, false);
        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            mText1 = savedInstanceState.getString(ARG_TEXT);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateText(args.getString(ARG_TEXT));
        } else if (! mText1.equals("mCurrentPositionValue")) {
            // Set article based on saved instance state defined during onCreateView
            updateText(mText1);
        }
    }


    public void updateText(String lala) {

        if (isAdded()) {
            Toast.makeText(getActivity().getApplicationContext(), "SOM PRIDANY", Toast.LENGTH_SHORT).show();
        }

        if (getActivity() == null) {
        //    Toast.makeText(getActivity().getApplicationContext(), "getactivity nULL:", Toast.LENGTH_SHORT).show();
        }

       // http://examples.javacodegeeks.com/java-basics/exceptions/java-lang-nullpointerexception-how-to-handle-null-pointer-exception/
//        Button button = (Button) rootView.findViewById(R.id.a_button1_id);
        Toast.makeText(getActivity().getApplicationContext(), "lala:", Toast.LENGTH_SHORT).show();
//if (getActivity().findViewById(R.id.moj_text_view_id) != null) {
if (rootView.findViewById(R.id.moj_text_view_id) != null) {

    TextView article = (TextView) getActivity().findViewById(R.id.moj_text_view_id);
    article.setText(lala);
    Toast.makeText(getActivity().getApplicationContext(), "prva verzia", Toast.LENGTH_SHORT).show();
    mText1 = lala;
}else{
    TextView article = (TextView) rootView.findViewById(R.id.moj_text_view_id);
    article.setText(lala);
    Toast.makeText(getActivity().getApplicationContext(), "druha verzia", Toast.LENGTH_SHORT).show();}
      mText1 = lala;

        // TOTO TU BOLO POVODNE
//        TextView article = (TextView) getActivity().findViewById(R.id.moj_text_view_id);
//        article.setText(lala);
//        mText1 = lala;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the current article selection in case we need to recreate the fragment
        outState.putString(ARG_TEXT, mText1);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);



    }
}
