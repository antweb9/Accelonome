package diy.ananth.accelonome

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "https://google.com"
        MobileAds.initialize(applicationContext, "ca-app-pub-6815878541496227~2236371059")

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        webView.webViewClient = object : WebViewClient() {

            /*override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if(url!!.startsWith("https://google.com")){
                    webView.loadUrl(url)
                } else {
                    val intent = Intent(ACTION_VIEW, Uri.parse(url))
                    view?.context?.startActivity(intent)
                }
                return super.shouldOverrideUrlLoading(view, url)
            }*/

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                view?.visibility = View.INVISIBLE
                progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                view?.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE
            }

        }
        webView.setBackgroundColor(Color.TRANSPARENT)
        webView.settings.loadsImagesAutomatically = true
        webView.settings.javaScriptEnabled = true
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.loadUrl(url)
    }
}
