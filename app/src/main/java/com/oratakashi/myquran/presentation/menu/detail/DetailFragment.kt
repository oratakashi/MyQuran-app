package com.oratakashi.myquran.presentation.menu.detail

import androidx.core.text.buildSpannedString
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.myquran.R
import com.oratakashi.myquran.databinding.FragmentDetailBinding
import com.oratakashi.myquran.domain.model.ayat.Ayat
import com.oratakashi.myquran.presentation.abstaction.BaseFragment
import com.oratakashi.myquran.presentation.navigation.MainNavigation
import com.oratakashi.myquran.utility.addObservers
import com.oratakashi.myquran.utility.navigation
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.showDefaultLayout
import com.oratakashi.viewbinding.core.tools.showLoadingLayout
import com.oratakashi.viewbinding.core.tools.toast
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>(), DetailDataContract {

    private val ayatAdapter: AyatAdapter by lazy {
        AyatAdapter()
    }

    override fun initUI() {
        super.initUI()
        with(binding) {
            rvAyat.apply {
                adapter = ayatAdapter
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )
            }
            tvName.text = args.data.nama
            tvTranslation.text = args.data.arti
            tvDesc.text = getString(
                R.string.placeholder_surah_desc,
                buildSpannedString {
                    val type = args.data.type
                    append(type.first().uppercase())
                    append(type.substring(1, type.count()))
                },
                args.data.ayat.toString()
            )
        }
    }

    override fun initAction() {
        super.initAction()
        with(binding) {
            ivBack.onClick {
                nav.toPrevious()
            }
            viewModel.getAyat(args.data.nomor.toInt())
        }
    }

    override fun initObserver() {
        super.initObserver()
        addObservers(DetailObserver(viewModel, this))
    }

    override fun onLoadingAyat() {
        binding.msvDetail.showLoadingLayout()
    }

    override fun onSuccessAyat(data: StateFlow<List<Ayat>>) {
        with(binding) {
            msvDetail.showDefaultLayout()
            val dataList: MutableList<Ayat> = ArrayList()
            lifecycleScope.launch {
                data.collect {
                    dataList.addAll(it)
                    ayatAdapter.addAll(dataList)
                }
            }
        }
    }

    override fun onFailAyat(error: Throwable?) {
        toast(error?.message.orEmpty())
        error?.printStackTrace()
    }

    override val binding: FragmentDetailBinding by viewBinding()
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModel()
    private val nav: MainNavigation by navigation { activity }
}