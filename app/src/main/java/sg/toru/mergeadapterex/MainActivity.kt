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
import sg.toru.mergeadapterex.ui.NoticeAdapter
import sg.toru.mergeadapterex.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding:ActivityMainBinding


    private val mainAdapter by lazy {
        MainAdapter()
    }

    private val noticeAdapter by lazy {
        NoticeAdapter()
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


    private val data:ArrayList<String> by lazy {
        ArrayList<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

//        val config = ConcatAdapter.Config.Builder().setIsolateViewTypes(true).build()
//        val concatAdapter = ConcatAdapter(config)
//        concatAdapter.addAdapter(headerAdapter)
//        concatAdapter.addAdapter(noticeAdapter)
//        concatAdapter.addAdapter(mainAdapter)
//        concatAdapter.addAdapter(footerAdapter)

        val concatAdapter = ConcatAdapter(headerAdapter, noticeAdapter, mainAdapter, footerAdapter)
        val layoutManager = GridLayoutManager(this, 2)
        layoutManager.spanSizeLookup = object:GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val type = concatAdapter.getItemViewType(position)
                Log.e("Toru", "type adapter : $type")
                return when(concatAdapter.getItemViewType(position)){
                    HEADER -> ViewSpanCount.HEADER.spanCount()
                    NOTICE -> ViewSpanCount.NOTICE.spanCount()
                    MAIN -> ViewSpanCount.MAIN.spanCount()
                    FOOTER -> ViewSpanCount.FOOTER.spanCount()
                    else -> 2
                }
            }
        }

        mainActivityBinding.rcvMain.setHasFixedSize(true)
        mainActivityBinding.rcvMain.layoutManager = layoutManager
        mainActivityBinding.rcvMain.adapter = concatAdapter
        headerAdapter.header = "test"
        headerAdapter.notifyItemInserted(0)


        mainActivityBinding.rcvMain.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val glayoutManager = (mainActivityBinding.rcvMain.layoutManager as GridLayoutManager)
                if (dy > 0 && glayoutManager.findLastVisibleItemPosition() + 2 >= glayoutManager.itemCount) {
                    if(!isLoading){
                        Log.e("Toru", "test!")
                        isLoading = true
                        footerAdapter.isDismissed = false
                        mainActivityBinding.rcvMain.post {
                            footerAdapter.notifyItemInserted(0)
                        }

                        // Network Simulating
                        Handler().postDelayed({
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
                            data += newData

                            mainAdapter.submitList(data)
                            mainActivityBinding.rcvMain.postDelayed({
                                isLoading = false
                                footerAdapter.isDismissed = true
                                footerAdapter.notifyItemRemoved(0)
                                mainAdapter.notifyItemRangeInserted(mainAdapter.currentList.size, newData.size)
                            }, 200)
                        }, 1000)

                    }
                }
            }
        })

        // FAKE DATA
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
        // END

        // FAKE NOTICE DATA //
        Handler().postDelayed({
            val noticeList = ArrayList<String>()
            noticeList.add("NOTICE 1")
            noticeList.add("NOTICE 2")
            noticeList.add("NOTICE 3")
            noticeAdapter.noticeItemList = noticeList
            Log.e("Toru", "NoticeAdapter set data")
            noticeAdapter.notifyItemChanged(0)
            Log.e("Toru", "NoticeAdapter notified data")
        }, 1000)

    }

    var isLoading = false
}