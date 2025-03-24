package com.oratakashi.myquran.presentation.menu.detail

import android.os.Build
import android.text.Html
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.oratakashi.myquran.databinding.ItemAyatBinding
import com.oratakashi.myquran.domain.model.ayat.Ayat
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding

class AyatPagingAdapter(
    private val onClick: (Ayat) -> Unit
) : PagingDataAdapter<Ayat, ViewHolder<ItemAyatBinding>>(COMPARATOR) {
    override fun onBindViewHolder(holder: ViewHolder<ItemAyatBinding>, position: Int) {
        with(holder.binding) {
            val item = getItem(position) ?: return@with
            tvAyat.text = item.arabic
            tvSurahNumber.text = if(item.nomor > 0) {
                item.nomor.toString()
            } else {
                "-"
            }
            tvLatin.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(item.latin, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(item.latin)
            }
            tvTranslation.text = item.translation
            root.setOnClickListener {
                onClick.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<ItemAyatBinding> {
        return viewBinding(parent)
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<Ayat>() {
            override fun areItemsTheSame(oldItem: Ayat, newItem: Ayat): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Ayat, newItem: Ayat): Boolean {
                return oldItem.nomor == newItem.nomor
            }

        }
    }
}