package unram.psti.week5_fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button;
        button = getActivity().findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // inisialisasi fragment manager di dalam fragment
                FragmentManager fragmentManager = getFragmentManager();
                DetailCategoryFragment detailCategoryFragment = new DetailCategoryFragment();

                // pengiriman data antar fragment
                Bundle bundle = new Bundle();
                bundle.putString("MSG", "ini adalah data yang dikirim dari Category Fragment");
                detailCategoryFragment.setArguments(bundle);

                // traksaksi fragment
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, detailCategoryFragment, DetailCategoryFragment.class.getSimpleName())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
