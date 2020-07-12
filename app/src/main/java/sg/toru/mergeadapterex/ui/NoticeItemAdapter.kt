package sg.toru.mergeadapterex.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sg.toru.mergeadapterex.R
import sg.toru.mergeadapterex.databinding.ItemNoticeBinding
import sg.toru.mergeadapterex.databinding.ItemNoticeLayoutBinding

class NoticeItemAdapter():ListAdapter<String, NoticeItemAdapter.NoticeItemViewHolder>(NoticeItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeItemViewHolder {
        val binding = ItemNoticeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeItemViewHolder(binding)
    }

    override fun getItemViewType(position: Int) = R.layout.item_notice
    override fun onBindViewHolder(holder: NoticeItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NoticeItemViewHolder(private val binding: ItemNoticeLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item:String){
            binding.txtNoticeItem.text = item
            binding.root.setOnClickListener {
                Log.e("Toru", "${bindingAdapterPosition+1} of Notice")
            }
        }
    }
}

class NoticeItemDiffCallback():DiffUtil.ItemCallback<String>(){
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = (oldItem == newItem)
    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = (oldItem == newItem)
}
