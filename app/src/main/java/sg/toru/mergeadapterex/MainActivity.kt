package sg.toru.mergeadapterex

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import sg.toru.mergeadapterex.databinding.ActivityMainBinding
import sg.toru.mergeadapterex.ui.FooterAdapter
import sg.toru.mergeadapterex.ui.HeaderAdapter
import sg.toru.mergeadapterex.ui.MainAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding:ActivityMainBinding


    private val mainAdapter by lazy {
        MainAdapter()
    }

    private val footerAdapter by lazy {
        FooterAdapter()
    }

    private val headerAdapter by lazy {
        HeaderAdapter(::onDismiss)
    }

    private fun onDismiss() {
        headerAdapter.header = null
        headerAdapter.notifyItemRemoved(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        val concatAdapter = ConcatAdapter(headerAdapter, mainAdapter, footerAdapter)
        val layoutManager = GridLayoutManager(this, 2)
        layoutManager.spanSizeLookup = object:GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {

                Log.e("Toru", "item count :: ${concatAdapter.itemCount}")

                val type = concatAdapter.getItemViewType(position)
                Log.e("Toru", "type: $type")
                return when(concatAdapter.getItemViewType(position)){
                    0 -> 2
                    1 -> 1
                    else -> 2
                }
            }
        }


//        layoutManager.spanSizeLookup = object:GridLayoutManager.SpanSizeLookup() {
//            override fun getSpanSize(position: Int): Int {
//                return when(position){
//                    0 -> 2
//                    in 1..mainAdapter.itemCount -> 1
//                    else -> 2
//                }
//            }
//        }


        mainActivityBinding.rcvMain.layoutManager = layoutManager
        mainActivityBinding.rcvMain.adapter = concatAdapter
        val config = ConcatAdapter.Config.Builder().setIsolateViewTypes(true).build()
        headerAdapter.header = "test"
        headerAdapter.notifyItemInserted(0)
        mainAdapter.submitList(listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"))
    }
}