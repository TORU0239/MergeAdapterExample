package sg.toru.mergeadapterex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sg.toru.mergeadapterex.databinding.ActivityMainBinding
import sg.toru.mergeadapterex.ui.FooterAdapter
import sg.toru.mergeadapterex.ui.HeaderAdapter
import sg.toru.mergeadapterex.ui.MainAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        val headerAdapter = HeaderAdapter()
        val mainAdapter = MainAdapter()
        val footerAdapter = FooterAdapter()

        val layoutManager = GridLayoutManager(this, 3)
        layoutManager.spanSizeLookup = object:GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                Log.e("Tori", "position:: $position")
                return when(position) {
                    0 -> 3
                    in 1 until mainAdapter.itemCount -> 2
                    else->3
                }
            }
        }
        mainActivityBinding.rcvMain.layoutManager = layoutManager
        mainActivityBinding.rcvMain.adapter = ConcatAdapter(headerAdapter, mainAdapter, footerAdapter)
        mainAdapter.submitList(listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"))
    }
}