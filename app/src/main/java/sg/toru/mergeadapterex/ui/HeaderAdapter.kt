package sg.toru.mergeadapterex.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sg.toru.mergeadapterex.R

class HeaderAdapter :ListAdapter<String,HeaderViewHolder>(HeaderDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        return HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false))
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind("")
    }

    override fun getItemCount(): Int = 1
}

class HeaderDiffCallback :DiffUtil.ItemCallback<String>(){
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = (oldItem == newItem)

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = (oldItem == newItem)

}

class HeaderViewHolder(view: View):RecyclerView.ViewHolder(view){
    fun bind(string:String){

    }
}
