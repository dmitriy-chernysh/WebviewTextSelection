package com.mobiledevpro.webtexthighlight;

import android.app.Activity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

/**
 * Helper class to interact with WebView via JSinterface to highlight a selected text
 * <p>
 * Created by Dmitriy V. Chernysh on 11/13/18.
 * <p>
 * https://instagr.am/mobiledevpro
 * https://github.com/dmitriy-chernysh
 * #MobileDevPro
 */
public class WebTextHighlightHelper {
    private static final String JS_HIGHLIGHT_FUNC = "(" +
            "function highlightText() {" +
            "var selection = null;" +
            "if (window.getSelection) {" +
            "selection = window.getSelection;" +
            "} else if (window.document.getSelection){" +
            "selection = window.document.getSelection;" +
            "}" +
            "if (!selection()) return '';" +
            "var range = selection().getRangeAt(0);" +
            "if (!range) return '';" +
            "var span = document.createElement('span');" +
            "span.className = 'highlight';" +
            "span.appendChild(range.extractContents());" +
            "range.insertNode(span);" +
            "var resultHtml = window.document.getElementsByTagName('body')[0].getElementsByTagName('p')[0];" +
            "return resultHtml ? resultHtml.innerHTML : '';" +
            "}" +
            ")()";

    public interface IMenuCallback {
        void onHightlightResult(String html);
    }

    public static void onActivityActionMenuStarted(Activity activity, ActionMode mode) {
        //replace a default menu by own menu
        Menu menu = mode.getMenu();
        menu.clear();
        activity.getMenuInflater().inflate(R.menu.webview_menu, menu);
    }

    public static void onContextualMenuItemClicked(WebView webView, MenuItem item, IMenuCallback callback) {
        if (item.getItemId() == R.id.action_highlight) {
            highlightSelection(webView, callback);
        } else if (item.getItemId() == R.id.action_clear_highlights) {
            clearAllHighlights(webView, callback);
        }
    }

    /**
     * Highlight text and return HTML
     *
     * @param webView WebView
     * @return HTML
     */
    private static void highlightSelection(WebView webView, final IMenuCallback callback) {
        webView.evaluateJavascript(JS_HIGHLIGHT_FUNC, value -> {
            if (callback != null) {
                callback.onHightlightResult(value != null ? value : "");
            }
        });
    }

    private static void clearHighlightSelection(WebView webView, final IMenuCallback callback) {
        // TODO: 11/13/18
    }

    private static void clearAllHighlights(WebView webView, final IMenuCallback callback) {
        // TODO: 11/13/18
    }
}
