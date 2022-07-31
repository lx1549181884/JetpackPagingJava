package com.rick.jetpackpagingjava.fragment;

import androidx.paging.LoadState;

import com.rick.jetpackpagingjava.adapter.LoadStateAdapter;
import com.rick.jetpackpagingjava.adapter.RepoAdapter;
import com.rick.jetpackpagingjava.base.BaseFragment;
import com.rick.jetpackpagingjava.databinding.FragmentHeheBinding;
import com.rick.jetpackpagingjava.util.ViewUtil;
import com.rick.jetpackpagingjava.viewmodel.HeheViewModel;

public class HeheFragment extends BaseFragment<FragmentHeheBinding, HeheViewModel> {
    private RepoAdapter repoAdapter;

    @Override
    protected void init(FragmentHeheBinding binding, HeheViewModel viewModel) {
        repoAdapter = new RepoAdapter();
        binding.recyclerView.setAdapter(repoAdapter.withLoadStateFooter(new LoadStateAdapter(repoAdapter::retry)));
        ViewUtil.addRecyclerViewDivider(binding.recyclerView);
        viewModel.repoList.observe(getViewLifecycleOwner(), repoBeanPagingData -> {
            repoAdapter.submitData(getLifecycle(), repoBeanPagingData);
        });
        binding.refresh.setOnRefreshListener(repoAdapter::refresh);
        repoAdapter.addLoadStateListener(combinedLoadStates -> {
            binding.refresh.setRefreshing(combinedLoadStates.getRefresh() instanceof LoadState.Loading);
            return null;
        });
    }
}
