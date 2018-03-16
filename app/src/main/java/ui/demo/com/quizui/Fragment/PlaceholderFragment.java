package ui.demo.com.quizui.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ui.demo.com.quizui.R;

/**
 * Created by Design-4 on 3/8/2018.
 */

public class PlaceholderFragment extends Fragment {

    private static final String ARG_SCOUT_KEY = "scout_key";

    public static PlaceholderFragment newInstance(String key) {

        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SCOUT_KEY, key);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.current_scout_fragment, container, false);

        //getArguments().getString(ARG_SCOUT_KEY));

        return rootView;
    }
}
