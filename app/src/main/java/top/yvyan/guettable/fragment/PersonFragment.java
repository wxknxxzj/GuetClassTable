package top.yvyan.guettable.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import top.yvyan.guettable.R;

public class PersonFragment extends Fragment implements View.OnClickListener {
    private static PersonFragment personFragment;

    private static final String TAG = "PersonFragment";
    private View view;

    public PersonFragment() {
        // Required empty public constructor
    }

    public static PersonFragment newInstance() {
        if (personFragment == null) {
            personFragment = new PersonFragment();
        }
        return personFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "createPersonFragmentView");
        view = inflater.inflate(R.layout.fragement_preson, container, false);

        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
