package com.example.mindscape

import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.mindscape.databinding.ActivityWebDesignBinding


class WebDesign : AppCompatActivity() {
private val binding:ActivityWebDesignBinding by lazy {
ActivityWebDesignBinding.inflate(layoutInflater)
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.webView.loadUrl( "https://mediai-9do7.onrender.com/")
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled=true


    }
    override fun onKeyDown(keyCode:Int,event: KeyEvent?):Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK && binding.webView.canGoBack()) {
            binding.webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}