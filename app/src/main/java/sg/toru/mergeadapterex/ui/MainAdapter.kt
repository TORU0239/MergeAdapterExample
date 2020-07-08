package sg.toru.mergeadapterex.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sg.toru.mergeadapterex.R

class MainAdapter():ListAdapter<String,MainViewHolder>(MainDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main_contents, parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MainDiffCallback :DiffUtil.ItemCallback<String>(){
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = (oldItem == newItem)

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = (oldItem == newItem)

}

class MainViewHolder(view: View):RecyclerView.ViewHolder(view){
    fun bind(string:String){

    }
}
