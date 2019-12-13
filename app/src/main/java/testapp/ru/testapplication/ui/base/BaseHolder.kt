package testapp.ru.testapplication.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseHolder<BaseItem : Any>(iv: View) : RecyclerView.ViewHolder(iv) {
    abstract fun onCreateViewItem(content: BaseItem)
}