package com.kawka.arbitrajlibrary.wv;

import android.annotation.TargetApi;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kawka.arbitrajlibrary.UserData;

public class ArbitrajWebViewClient extends WebViewClient {

    private final String TAG = ArbitrajWebViewClient.class.getName();


    @Override
    public void onPageFinished(WebView view, String url) {
        /*

         Сохраняем последнюю ссылку для дальнейшей загрузки. При закрытии будет откраваться последняя сохраненная ссылка

         */
        new UserData(view.getContext()).setUserData(UserData.PREF_LINK, url.toString());

    }

    @Override
    @TargetApi(android.os.Build.VERSION_CODES.M)
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        Log.e(TAG, errorResponse.toString());
        onReceivedError(view, errorResponse.getStatusCode(), errorResponse.getReasonPhrase(), request.getUrl().toString());
    }

    @Override
    @TargetApi(android.os.Build.VERSION_CODES.M)
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        Log.e(TAG, error.toString());
        handler.proceed();
    }

    @Override
    @TargetApi(android.os.Build.VERSION_CODES.M)
    public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
        Log.d(TAG, rerr.getDescription().toString());
        onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
    }

}
