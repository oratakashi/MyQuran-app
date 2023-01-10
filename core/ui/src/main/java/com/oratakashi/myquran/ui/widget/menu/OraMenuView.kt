package com.oratakashi.myquran.ui.widget.menu

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.FrameLayout
import com.oratakashi.myquran.ui.R
import com.oratakashi.myquran.ui.databinding.LayoutMenuViewBinding
import com.oratakashi.viewbinding.core.binding.customview.viewBinding

/**
 * Custom Menu Views for Generic Menu
 * @author Oratakashi
 * @since 10 Jan 2022
 */
class OraMenuView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutMenuViewBinding by viewBinding()

    var text: String
        get() = binding.tvLabel.text.toString()
        set(value) {
            binding.tvLabel.text = value
        }

    var startIconDrawable: Drawable?
        get() = binding.ivIcon.drawable
        set(value) {
            binding.ivIcon.setImageDrawable(value)
        }

    init {
        attrs?.let {
            val attr = context.obtainStyledAttributes(it, R.styleable.OraMenuView, 0,0)

            runCatching {
                text = attr.getString(R.styleable.OraMenuView_android_text).orEmpty()
                startIconDrawable = attr.getDrawable(R.styleable.OraMenuView_startIconDrawable)
            }.onSuccess { attr.recycle() }
        }
    }
}