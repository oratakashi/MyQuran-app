package com.oratakashi.myquran.presentation.menu.detail.dialog.infodialog

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.buildSpannedString
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.oratakashi.myquran.R
import com.oratakashi.myquran.databinding.FragmentInfoDialogBinding
import com.oratakashi.myquran.domain.model.surah.Surah
import com.oratakashi.viewbinding.core.binding.bottomsheet.viewBinding

class InfoDialogFragment(
    private val surah: Surah
) : BottomSheetDialogFragment() {

    private val binding: FragmentInfoDialogBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            tvAyat.text = getString(
                R.string.placeholder_surah_desc,
                buildSpannedString {
                    val type = surah.type
                    append(type.first().uppercase())
                    append(type.substring(1, type.count()))
                },
                surah.ayat.toString()
            )
            tvName.text = surah.nama
            tvTranslation.text = surah.arti
            tvDesc.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(surah.keterangan, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(surah.keterangan)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}