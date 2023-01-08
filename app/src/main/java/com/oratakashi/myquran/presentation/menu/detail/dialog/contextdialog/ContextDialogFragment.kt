package com.oratakashi.myquran.presentation.menu.detail.dialog.contextdialog

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.oratakashi.myquran.R
import com.oratakashi.myquran.databinding.FragmentContextDialogBinding
import com.oratakashi.myquran.domain.model.ayat.Ayat
import com.oratakashi.myquran.domain.model.surah.Surah
import com.oratakashi.viewbinding.core.binding.bottomsheet.viewBinding

class ContextDialogFragment(
    private val surah: Surah,
    private val ayat: Ayat
) : BottomSheetDialogFragment() {

    private val binding: FragmentContextDialogBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            tvName.text = getString(R.string.placeholder_surah_name, surah.nama)

            tvAyat.text = ayat.arabic
            tvSurahNumber.text = if (ayat.nomor > 0) {
                ayat.nomor.toString()
            } else {
                "-"
            }
            tvLatin.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(ayat.latin, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(ayat.latin)
            }
            tvTranslation.text = ayat.translation
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}