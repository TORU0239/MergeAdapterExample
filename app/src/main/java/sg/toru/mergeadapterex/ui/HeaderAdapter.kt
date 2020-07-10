package sg.toru.mergeadapterex.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import sg.toru.mergeadapterex.R
import sg.toru.mergeadapterex.databinding.ItemHeaderBinding

class HeaderAdapter(private val dismissListener: ()->Unit) :RecyclerView.Adapter<HeaderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val itemHeaderBinding = ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeaderViewHolder(itemHeaderBinding, dismissListener)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        header?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = if (header.isNullOrBlank()) 0 else 1

    override fun getItemViewType(position: Int): Int = R.layout.item_header

    var header:String? = null
}

class HeaderViewHolder(private val binding: ItemHeaderBinding, private val dismissListener:()->Unit):RecyclerView.ViewHolder(binding.root){
    fun bind(string:String){
        binding.txtDismiss.setOnClickListener {
            dismissListener.invoke()
        }
    }
}