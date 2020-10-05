package com.psss.travelgram.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.psss.travelgram.R;
import com.psss.travelgram.view.adapter.MemoryAdapter;
import com.psss.travelgram.viewmodel.FollowingJournalViewModel;
import com.psss.travelgram.viewmodel.JournalViewModel;

public class FollowingJournalFragment extends Fragment {

    private RecyclerView recyclerView;

    private FollowingJournalViewModel followingJournalViewModel;


    public static FollowingJournalFragment newInstance(String countryName) {
        FollowingJournalFragment fragment = new FollowingJournalFragment();
        Bundle bundle = new Bundle();
        bundle.putString("countryName", countryName);
        fragment.setArguments(bundle);
        return fragment;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_journal, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.memory_recycler);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // view model
        followingJournalViewModel = new FollowingJournalViewModel(getActivity(), getArguments().getString("countryName"));

        // aspetta il via per l'azione successiva
        followingJournalViewModel.getAdapter().observe(getViewLifecycleOwner(), new Observer<MemoryAdapter>() {
            @Override
            public void onChanged(@Nullable MemoryAdapter adapter) {
                recyclerView.setAdapter(adapter);
            }
        });

        return root;
    }


}