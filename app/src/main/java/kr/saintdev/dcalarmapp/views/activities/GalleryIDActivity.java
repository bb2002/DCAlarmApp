package kr.saintdev.dcalarmapp.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kr.saintdev.dcalarmapp.R;

/**
 * 현재 파트
 *
 *
 *
 *
 *
 * -=-=-=-=-=-=-=-=-=--=-= 코틀린 코드 =-=-=-=-=-=-=-=-=-=-=-=-=-=-
 * class GalleryIDActivity : AppCompatActivity() {
 *     private var progressDialogInstance: ProgressDialog? = null
 *
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         super.onCreate(savedInstanceState)
 *         setContentView(R.layout.activity_gallert_id)
 *
 *         add_gallery_save.setOnClickListener {
 *             val gallID = add_gallery_ideditor.text.toString()
 *             val isMinerGallery = isMGallery.isChecked
 *             val url = (DC_GALL_URL          // 기본 디씨 갤 URL
 *             + (if(isMinerGallery) { "/mgallery/board/lists/" } else { "/board/lists/" })      // 마이너 갤러리의 경우 처리
 *             + "?id=" + gallID)  // URL 완성
 *
 *             // URL 에 대한 유효성 검사
 *             val parser = DCWebParser.getInstance()
 *             parser.startMetaDataParsing(url, onParseCompleteListener)
 *
 *             this.progressDialogInstance = R.string.execute_isvalid.openProgress(this)
 *         }
 *     }
 *
 *     private val onParseCompleteListener =
 *         object : DCWebParser.OnDCGalleryMetaParsedListener {
 *             override fun onSuccess(meta: GalleryMeta) {
 *                 progressDialogInstance?.dismiss()
 *
 *                 if(meta.isValid()) {
 *                     val dbm = DatabaseManager.getInstance()
 *                     val isTargetingGallery =
 *                         GalleryMetaDatabaseFunc.read(this@GalleryIDActivity, meta).isEmpty()
 *
 *                     if (isTargetingGallery) {
 *                         meta.Insert(dbm, this@GalleryIDActivity)
 *
 *                         // Open activity.
 *                         startActivity(
 *                             Intent(
 *                                 this@GalleryIDActivity,
 *                                 GalleryListActivity::class.java
 *                             )
 *                         )
 *                         finish()
 *                     } else {
 *                         openTargetedGallery()
 *                     }
 *                 } else {
 *                     onFailed()
 *                 }
 *             }
 *
 *             override fun onFailed() {
 *                 progressDialogInstance?.dismiss()
 *                 R.string.execute_is_unvalid.openAlert(this@GalleryIDActivity, "Error")
 *             }
 *
 *             // 이미 타겟팅 중인 갤러리 입니다.
 *             fun openTargetedGallery() {
 *                 R.string.targeted_gallery.openAlert(this@GalleryIDActivity, "WARN")
 *             }
 *         }
 * }
 */

public class GalleryIDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallert_id);
    }
}
