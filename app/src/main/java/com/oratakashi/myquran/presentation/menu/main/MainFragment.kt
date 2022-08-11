package com.oratakashi.myquran.presentation.menu.main

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.myquran.databinding.FragmentMainBinding
import com.oratakashi.myquran.domain.model.surah.Surah
import com.oratakashi.myquran.presentation.abstaction.BaseFragment
import com.oratakashi.myquran.presentation.navigation.MainNavigation
import com.oratakashi.myquran.utility.addObservers
import com.oratakashi.myquran.utility.navigation
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import com.oratakashi.viewbinding.core.tools.showDefaultLayout
import com.oratakashi.viewbinding.core.tools.showLoadingLayout
import com.oratakashi.viewbinding.core.tools.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>(), MainDataContract {

    private val surahAdapter: SurahAdapter by lazy {
        SurahAdapter {
            nav.toDetail(it)
        }
    }

    override fun initUI() {
        super.initUI()
        with(binding) {
            rvSurah.apply {
                adapter = surahAdapter
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )
            }
        }
    }

    override fun initAction() {
        super.initAction()
        viewModel.getSurah()
    }

    override fun initObserver() {
        super.initObserver()
        addObservers(MainObserver(viewModel, this))
    }

    override fun onLoadingSurah() {
        binding.msvSurah.showLoadingLayout()
    }

    override fun onSuccessSurah(data: List<Surah>) {
        with(binding) {
            msvSurah.showDefaultLayout()
            surahAdapter.addAll(data)
        }
    }

    override fun onFailSurah(error: Throwable?) {
        error?.printStackTrace()
        toast(error?.message.orEmpty())
    }

    override val binding: FragmentMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModel()
    private val nav: MainNavigation by navigation { activity }
}
