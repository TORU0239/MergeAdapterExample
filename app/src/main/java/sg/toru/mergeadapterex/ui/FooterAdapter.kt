package sg.toru.mergeadapterex.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sg.toru.mergeadapterex.R

class FooterAdapter:RecyclerView.Adapter<FooterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FooterViewHolder {
        return FooterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_footer, parent, false))
    }

    override fun onBindViewHolder(holder: FooterViewHolder, position: Int) {
        holder.bind("")
    }

    override fun getItemCount(): Int = if(isDismissed) 0 else 1

    override fun getItemViewType(position: Int): Int = 300

    var isDismissed = true
}

class FooterViewHolder(view: View):RecyclerView.ViewHolder(view){
    fun bind(string:String){

    }
}
