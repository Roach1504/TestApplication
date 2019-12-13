package testapp.ru.testapplication.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter<BaseItem : Any> : RecyclerView.Adapter<BaseHolder<BaseItem>>() {

    abstract fun getLayout(viewType: Int): Int

    abstract fun createHolder(view: View, viewType: Int): BaseHolder<BaseItem>

    protected val items = AsyncListDiffer(this, object : DiffUtil.ItemCallback<BaseItem>() {
        override fun areItemsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean {
            return isItemsTheSame(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean {
            return isContentsTheSame(oldItem, newItem)
        }
    })

    fun getItem(position: Int) = items.currentList[position]


    abstract fun isItemsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean

    abstract fun isContentsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean

    @CallSuper
    open fun updateList(newItems: List<BaseItem>, onLoaded: Runnable = Runnable {}) {
        items.submitList(newItems, onLoaded)
    }

    @CallSuper
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<BaseItem> {
        val view = LayoutInflater.from(parent.context)
            .inflate(getLayout(viewType), parent, false)
        return createHolder(view, viewType)
    }


    @CallSuper
    override fun onBindViewHolder(baseHolder: BaseHolder<BaseItem>, position: Int) {
        baseHolder.onCreateViewItem(items.currentList[position])
    }

    override fun getItemCount() = items.currentList.size

}

interface OnItemClickListener {
    fun onItemClicked(position: Int, view: View)
}

fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
    this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewDetachedFromWindow(view: View) {
            view.setOnClickListener(null)
        }

        override fun onChildViewAttachedToWindow(view: View) {
            view.setOnClickListener {
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(holder.adapterPosition, view)
            }
        }
    })
}