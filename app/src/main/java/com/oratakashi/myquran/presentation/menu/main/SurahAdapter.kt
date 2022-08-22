package com.oratakashi.myquran.presentation.menu.main

import android.view.ViewGroup
import androidx.core.text.buildSpannedString
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.myquran.R
import com.oratakashi.myquran.databinding.ItemSurahBinding
import com.oratakashi.myquran.domain.model.ayat.Ayat
import com.oratakashi.myquran.domain.model.surah.Surah
import com.oratakashi.viewbinding.core.binding.list.arrayList
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick

class SurahAdapter(
    private val onClick: (Surah) -> Unit
) : RecyclerView.Adapter<ViewHolder<ItemSurahBinding>>() {
    override fun onBindViewHolder(holder: ViewHolder<ItemSurahBinding>, position: Int) {
        with(holder.binding) {
            tvNameArabic.text = data[position].asma
            tvName.text = data[position].nama
            tvSurahNumber.text = data[position].nomor
            tvDesc.text = root.context.getString(
                R.string.placeholder_surah_desc,
                buildSpannedString {
                    val type = data[position].type
                    append(type.first().uppercase())
                    append(type.substring(1, type.count()))
                },
                data[position].ayat.toString()
            )
            root.onClick { onClick.invoke(data[position]) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ItemSurahBinding> {
        return viewBinding(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addAll(data: List<Surah>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(data))
        this.data.clear()
        this.data.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }

    private val data: MutableList<Surah> by arrayList()

    inner class DiffUtilCallback(private val newList: List<Surah>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return data.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return data[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return data[oldItemPosition].nomor == newList[newItemPosition].nomor
        }

    }
}