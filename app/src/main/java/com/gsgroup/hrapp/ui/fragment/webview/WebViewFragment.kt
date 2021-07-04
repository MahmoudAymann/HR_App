package com.gsgroup.hrapp.ui.fragment.webview

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withStateAtLeast
import androidx.navigation.fragment.navArgs
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentWebViewBinding
import com.gsgroup.hrapp.util.showErrorDialog
import kotlinx.coroutines.launch
import timber.log.Timber


class WebViewFragment : BaseFragment<FragmentWebViewBinding, WebViewViewModel>() {
    private val args: WebViewFragmentArgs by navArgs()
    override fun pageTitle(): String = args.title ?: ""
    override val mViewModel: WebViewViewModel by viewModels()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webView.apply {
            webViewClient = MyAppWebViewClient()
            settings.setSupportZoom(true)
            settings.javaScriptEnabled = true
            args.url?.let {
                Timber.e("$it")
                loadUrl(it)
            }
        }
    }
    inner class MyAppWebViewClient : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            lifecycleScope.launch {
                withStateAtLeast(Lifecycle.State.INITIALIZED){
                    showProgress()
                }
            }
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            lifecycleScope.launch {
                withStateAtLeast(Lifecycle.State.INITIALIZED){
                    showProgress(false)
                }
            }
        }

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity?.showErrorDialog(error?.description.toString())
            }else{
                activity?.showErrorDialog(error.toString())
            }
        }
    }
}

