package sg.toru.mergeadapterex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.ConcatAdapter
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
        mainAdapter.submitList(listOf("1", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2"))
        val footerAdapter = FooterAdapter()

        mainActivityBinding.rcvMain.adapter = ConcatAdapter(headerAdapter, mainAdapter, footerAdapter)
    }
}