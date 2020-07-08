package sg.toru.mergeadapterex

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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


        mainActivityBinding.rcvMain.setHasFixedSize(true)
        mainActivityBinding.rcvMain.layoutManager = layoutManager
        mainActivityBinding.rcvMain.adapter = concatAdapter
        val config = ConcatAdapter.Config.Builder().setIsolateViewTypes(true).build()
        headerAdapter.header = "test"
        headerAdapter.notifyItemInserted(0)

        val data = ArrayList<String>()
        data.add("0")
        data.add("1")
        data.add("2")
        data.add("3")
        data.add("4")
        data.add("5")
        data.add("6")
        data.add("7")
        data.add("8")
        data.add("9")
        mainAdapter.submitList(data)

        mainActivityBinding.rcvMain.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val glayoutManager = (mainActivityBinding.rcvMain.layoutManager as GridLayoutManager)
                if (dy > 0 && glayoutManager.findLastVisibleItemPosition() + 2 >= glayoutManager.itemCount) {
                    if(!isLoading){
                        Log.e("Toru", "test!")
                        isLoading = true
                        footerAdapter.isDismissed = false
                        footerAdapter.notifyItemInserted(0)

                        Handler().postDelayed({
                            isLoading = false
                            Toast.makeText(this@MainActivity, "Loading End", Toast.LENGTH_SHORT).show()
                            footerAdapter.isDismissed = true
                            footerAdapter.notifyItemRemoved(0)

                            val newData = ArrayList<String>()
                            newData.add("10")
                            newData.add("11")
                            newData.add("12")
                            newData.add("13")
                            newData.add("14")
                            newData.add("15")
                            newData.add("16")
                            newData.add("17")
                            newData.add("18")
                            newData.add("19")
                            (mainAdapter.currentList as ArrayList).addAll(newData)
                            mainAdapter.notifyItemRangeInserted(mainAdapter.currentList.size, newData.size)
                        }, 2000)

                    }
                }
            }
        })
    }

    var isLoading = false
}