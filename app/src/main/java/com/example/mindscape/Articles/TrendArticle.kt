package com.example.mindscape.Articles

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mindscape.Adapters.NewsClicked
import com.example.mindscape.Adapters.NewwsAdapter
import com.example.mindscape.NewsService
import com.example.mindscape.R
import com.example.mindscape.news1
import com.example.mindscape.news2
import retrofit2.Call
import retrofit2.Callback

class TrendArticle : AppCompatActivity(), NewsClicked {
    private lateinit var mAdapter: NewwsAdapter
    lateinit var recyclerview: RecyclerView
    lateinit var swipe: SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trendingarticles)
        swipe=findViewById<SwipeRefreshLayout>(R.id.swipe)
        recyclerview=findViewById<RecyclerView>(R.id.recycleview)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val back = findViewById<ImageButton>(R.id.imageButton112)
        back.setOnClickListener {
            onBackPressed()
        }

        getNews()

        swipe.setOnRefreshListener {
            getNews()
            swipe.isRefreshing = false
        }
    }
    private fun getNews() {
        val news = NewsService.newsInstance.getHeadlines("in", "health")
        news.enqueue(object : Callback<news1> {
            override fun onResponse(call: Call<news1>, response: retrofit2.Response<news1>) {
                val news = response.body()
                if (news != null) {
                    Log.d("news", news.toString())
                    val layoutManager = LinearLayoutManager(this@TrendArticle)
                    recyclerview.layoutManager = layoutManager
                    recyclerview.addItemDecoration(DividerItemDecoration(this@TrendArticle, layoutManager.orientation))
                    mAdapter = NewwsAdapter(this@TrendArticle, this@TrendArticle, news.articles)
                    recyclerview.adapter = mAdapter
                }
            }

            override fun onFailure(call: Call<news1>, t: Throwable) {
                Log.d("news", "error", t)
            }
        })
    }

    override fun onItemClicked(articles: news2) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(articles.url))
    }
}