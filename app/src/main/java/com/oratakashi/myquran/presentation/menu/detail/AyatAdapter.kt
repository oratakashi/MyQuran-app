package com.oratakashi.myquran.presentation.menu.detail

import android.os.Build
import android.text.Html
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.myquran.databinding.ItemAyatBinding
import com.oratakashi.myquran.domain.model.ayat.Ayat
import com.oratakashi.viewbinding.core.binding.list.arrayList
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding

class AyatAdapter: RecyclerView.Adapter<ViewHolder<ItemAyatBinding>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<ItemAyatBinding> {
        return viewBinding(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder<ItemAyatBinding>, position: Int) {
        with(holder.binding) {
            tvAyat.text = data[position].arabic
            tvSurahNumber.text = data[position].nomor
            tvLatin.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(data[position].latin, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(data[position].latin)
            }
            tvTranslation.text = data[position].translation
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addAll(data: List<Ayat>) {
        this.data.clear()
        this.data.addAll(data)
        notifyItemRangeChanged(0, data.size)
    }

    private val data: MutableList<Ayat> by arrayList()
}