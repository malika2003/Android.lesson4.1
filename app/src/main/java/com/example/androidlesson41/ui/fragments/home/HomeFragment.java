package com.example.androidlesson41.ui.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.androidlesson41.R;
import com.example.androidlesson41.databinding.FragmentHomeBinding;
import com.example.androidlesson41.ui.Activities.AdapterLocal;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private AdapterLocal adapterLocal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
        fragmentListener();
        initRecycler();
        createList();

    }
    private ArrayList<String> createList(){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("item " + i);
        }
        return list;
    }

    private void initRecycler() {
        adapterLocal = new AdapterLocal();

    }

    private void fragmentListener() {
        getParentFragmentManager().setFragmentResultListener("key",
                this,
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        String text = result.getString("textKey");
                        Toast.makeText(requireContext(), text , Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void initListeners() {
        binding.addBtn.setOnClickListener(v -> {
            openFragment();
        });
    }
    private void openFragment() {
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main)
                .navigate(R.id.detaleFragment);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}