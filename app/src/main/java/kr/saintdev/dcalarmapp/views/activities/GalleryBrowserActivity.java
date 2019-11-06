package kr.saintdev.dcalarmapp.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kr.saintdev.dcalarmapp.R;

/**
 * 현재 파트
 *
 * =-=-=-=-=-=-=-=-=-=-=-=-= 코틀린 코드 -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
 * class GalleryBrowserActivity : AppCompatActivity() {
 *     private var progressDialogInstance: ProgressDialog? = null
 *
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         super.onCreate(savedInstanceState)
 *         setContentView(R.layout.activity_gallery_browser)
 *
 *         browser.settings.javaScriptEnabled = true
 *         browser.webViewClient = DCWebViewClient()
 *         browser.webChromeClient = WebChromeClient()
 *         browser.loadUrl(DC_GALL_URL)
 *
 *         page_save.setOnClickListener {
 *             val url = browser.url.toString()
 *
 *             // 해당 URL 의 유효성을 확인 한다.
 *             val parser = DCWebParser.getInstance()
 *             parser.startMetaDataParsing(url, MetaParserResultListener())
 *             this.progressDialogInstance = R.string.execute_isvalid.openProgress(this)
 *         }
 *
 *         exit.setOnClickListener {
 *             finish()
 *         }
 *     }
 *
 *     override fun onBackPressed() {
 *         if(browser.canGoBack()) {
 *             browser.goBack()
 *         } else {
 *             super.onBackPressed()
 *         }
 *     }
 *
 *     private inner class MetaParserResultListener : DCWebParser.OnDCGalleryMetaParsedListener {
 *         override fun onSuccess(meta: GalleryMeta) {
 *             progressDialogInstance?.dismiss()
 *
 *             if(meta.isValid()) {
 *                 val database = DatabaseManager.getInstance()
 *
 *                 // 중복 검사
 *                 val bTargetedGallery =
 *                     GalleryMetaDatabaseFunc.read(this@GalleryBrowserActivity, meta).isEmpty()
 *
 *                 if (bTargetedGallery) {
 *                     val pStmt = database.makeInsertQuery(
 *                         SQLQueries.INSERT_DC_TARGETING_GALLERY,
 *                         this@GalleryBrowserActivity
 *                     )
 *                     pStmt.bindString(1, meta.galleryName)
 *                     pStmt.bindString(2, meta.galleryID)
 *                     pStmt.bindString(3, DateUtilFunctions.getNowToString())
 *                     pStmt.execute()
 *
 *                     Toast.makeText(
 *                         this@GalleryBrowserActivity,
 *                         R.string.execute_succ,
 *                         Toast.LENGTH_SHORT
 *                     ).show()
 *
 *                     // Open activity.
 *                     startActivity(
 *                         Intent(
 *                             this@GalleryBrowserActivity,
 *                             GalleryListActivity::class.java
 *                         )
 *                     )
 *                     finish()
 *                 } else {
 *                     targetedGalleryAleryOpen()
 *                 }
 *             } else {
 *                 onFailed()
 *             }
 *         }
 *
 *         override fun onFailed() {
 *             // 오류 발생시 경고창 열기
 *             progressDialogInstance?.dismiss()
 *             R.string.execute_unvalid_err.openAlert(this@GalleryBrowserActivity, resources.getString(R.string.execute_isvalid_err))
 *         }
 *
 *         // 이미 갤러리가 트랙킹 중 입니다.
 *         fun targetedGalleryAleryOpen() {
 *             R.string.targeted_gallery.openAlert(this@GalleryBrowserActivity, "WARN")
 *         }
 *     }
 *
 *     private inner class DCWebViewClient : WebViewClient() {
 *         override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
 *             view?.loadUrl(url)
 *             return true
 *         }
 *
 *         override fun shouldOverrideUrlLoading(
 *             view: WebView?,
 *             request: WebResourceRequest?
 *         ): Boolean {
 *             val url = request?.url.toString()
 *             view?.loadUrl(url)
 *             return true
 *         }
 *     }
 * }
 */

public class GalleryBrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_browser);
    }
}
