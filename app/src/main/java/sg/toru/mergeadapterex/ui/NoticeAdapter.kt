package sg.toru.mergeadapterex.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import sg.toru.mergeadapterex.R
import sg.toru.mergeadapterex.databinding.ItemNoticeBinding

class NoticeAdapter :RecyclerView.Adapter<NoticeViewHolder>() {

    var noticeItemList:ArrayList<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        Log.e("Toru", "NoticeAdapter onCreateViewHolder init, viewType: $viewType")
        val binding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        Log.e("Toru", "NoticeAdapter onBindViewHolder, position $position")
        noticeItemList?.let {
            Log.e("Toru", "NoticeAdapter binding.")
            holder.bind(it)
        }
    }
    override fun getItemViewType(position: Int) = R.layout.item_notice
    override fun getItemCount(): Int = 1
}

class NoticeViewHolder(private val binding: ItemNoticeBinding) :RecyclerView.ViewHolder(binding.root) {
    private val noticeItemAdapter = NoticeItemAdapter()
    init {
        Log.e("Toru", "NoticeViewHolder init")
        binding.rcvNotice.run {
            adapter = noticeItemAdapter
            setHasFixedSize(true)
            PagerSnapHelper().attachToRecyclerView(this)
        }
    }

    fun bind(noticeItemList:ArrayList<String>?){
        Log.e("Toru", "NoticeViewHolder, list size = ${noticeItemList?.size}")
        noticeItemAdapter.submitList(noticeItemList)
    }
}