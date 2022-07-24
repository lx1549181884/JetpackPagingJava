package com.rick.jetpackpagingjava.fragment;

import com.blankj.utilcode.util.GsonUtils;
import com.rick.jetpackpagingjava.base.BaseFragment;
import com.rick.jetpackpagingjava.databinding.FragmentHeheBinding;
import com.rick.jetpackpagingjava.net.api.GithubService;
import com.rick.jetpackpagingjava.util.LogUtil;
import com.rick.jetpackpagingjava.viewmodel.HeheViewModel;

public class HeheFragment extends BaseFragment<FragmentHeheBinding, HeheViewModel> {
    @Override
    protected void init(FragmentHeheBinding binding, HeheViewModel viewModel) {
        GithubService.create().search(1, 2).observe(getViewLifecycleOwner(), listResponseBean -> LogUtil.log("onChanged " + GsonUtils.toJson(listResponseBean)));
    }
}
