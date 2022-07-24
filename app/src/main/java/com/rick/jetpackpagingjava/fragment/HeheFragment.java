package com.rick.jetpackpagingjava.fragment;

import com.blankj.utilcode.util.GsonUtils;
import com.rick.jetpackpagingjava.adapter.LoadStateAdapter;
import com.rick.jetpackpagingjava.adapter.RepoAdapter;
import com.rick.jetpackpagingjava.base.BaseFragment;
import com.rick.jetpackpagingjava.databinding.FragmentHeheBinding;
import com.rick.jetpackpagingjava.util.LogUtil;
import com.rick.jetpackpagingjava.util.ViewUtil;
import com.rick.jetpackpagingjava.viewmodel.HeheViewModel;

public class HeheFragment extends BaseFragment<FragmentHeheBinding, HeheViewModel> {
    private RepoAdapter repoAdapter;

    @Override
    protected void init(FragmentHeheBinding binding, HeheViewModel viewModel) {
        repoAdapter = new RepoAdapter();
        repoAdapter.withLoadStateFooter(new LoadStateAdapter(repoAdapter::retry));
        binding.recyclerView.setAdapter(repoAdapter);
        ViewUtil.addRecyclerViewDivider(binding.recyclerView);
        viewModel.repoList.observe(getViewLifecycleOwner(), repoBeanPagingData -> {
            LogUtil.log("onChanged " + GsonUtils.toJson(repoBeanPagingData));
            repoAdapter.submitData(getLifecycle(), repoBeanPagingData);
        });
    }
}
