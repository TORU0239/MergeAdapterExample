package sg.toru.mergeadapterex.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sg.toru.mergeadapterex.R
import sg.toru.mergeadapterex.databinding.ItemMainContentsBinding

class MainAdapter():ListAdapter<String,MainViewHolder>(MainDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemMainContentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MainDiffCallback :DiffUtil.ItemCallback<String>(){
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = (oldItem == newItem)

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = (oldItem == newItem)

}

class MainViewHolder(private val binding: ItemMainContentsBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(string:String){
        binding.txtMainContents.text = string
    }
}
