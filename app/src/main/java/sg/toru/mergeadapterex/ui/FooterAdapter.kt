package sg.toru.mergeadapterex.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sg.toru.mergeadapterex.R

class FooterAdapter():ListAdapter<String,FooterViewHolder>(FooterDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FooterViewHolder {
        return FooterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_footer, parent, false))
    }

    override fun onBindViewHolder(holder: FooterViewHolder, position: Int) {
        holder.bind("")
    }

    override fun getItemCount(): Int = 1
}

class FooterDiffCallback :DiffUtil.ItemCallback<String>(){
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = (oldItem == newItem)

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = (oldItem == newItem)

}

class FooterViewHolder(view: View):RecyclerView.ViewHolder(view){
    fun bind(string:String){

    }
}
