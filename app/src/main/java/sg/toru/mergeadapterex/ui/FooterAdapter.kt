package sg.toru.mergeadapterex.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sg.toru.mergeadapterex.R
import sg.toru.mergeadapterex.databinding.ItemFooterBinding

class FooterAdapter:RecyclerView.Adapter<FooterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FooterViewHolder {
        val binding = ItemFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FooterViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: FooterViewHolder, position: Int) {}

    override fun getItemCount(): Int = if(isDismissed) 0 else 1

    override fun getItemViewType(position: Int): Int = R.layout.item_footer

    var isDismissed = true
}

class FooterViewHolder(view: View):RecyclerView.ViewHolder(view)
