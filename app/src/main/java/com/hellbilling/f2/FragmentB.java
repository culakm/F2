package com.hellbilling.f2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentB extends Fragment {

    final static String ARG_POSITION = "position";
    String mCurrentPosition = "mCurrentPositionValue";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getString(ARG_POSITION);
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
            updateArticleView(args.getString(ARG_POSITION));
        } else if (mCurrentPosition.equals("mCurrentPositionValue")) {
            // Set article based on saved instance state defined during onCreateView
            updateArticleView(mCurrentPosition);
        }
    }


    public void updateArticleView(String lala) {
        Toast.makeText(getActivity().getApplicationContext(), "volame  updateArticleView, lala: "+lala, Toast.LENGTH_SHORT).show();
        TextView article = (TextView) getActivity().findViewById(R.id.moj_text_view_id);
        article.setText(lala);
        Toast.makeText(getActivity().getApplicationContext(), "volame  updateArticleView, text: "+article.getText(), Toast.LENGTH_SHORT).show();
        mCurrentPosition = lala;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putString(ARG_POSITION, mCurrentPosition);
    }

}
