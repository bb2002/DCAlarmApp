package kr.saintdev.dcalarmapp.modules.parser;

import android.net.Uri;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.helper.DataUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import kr.saintdev.dcalarmapp.modules.utils.DateUtils;
import kr.saintdev.dcalarmapp.modules.utils.GalleryMeta;
import kr.saintdev.dcalarmapp.modules.utils.PostMeta;

public class DCWebParser {
    /**
     * DC INSIDE WEB PARSER CLASS.
     * HOW TO USE
     *
     *
     val parser = DCWebParser.getInstance()
     parser.ParseGallery("http://gall.dcinside.com/mgallery/board/lists/?id=bang_dream", object : DCWebParser.OnDCGalleryParsedListener {
     override fun onSuccess(posts: ArrayList<PostMeta>) {

     }

     override fun onFailed() {
     }
     })

     *
     */

    final String DC_GALL_URL = "https://gall.dcinside.com";

    private static DCWebParser ins = null;

    public static DCWebParser getInstance() {
        if(ins == null) {
            ins = new DCWebParser();
        }

        return ins;
    }



    /**
     * @Date 09.16 2019
     * 갤러리 파싱을 마쳤을 때 호출될 콜백 리스너
     */
    interface OnDCGalleryParsedListener {
        void onSuccess(ArrayList<PostMeta> document);
        void onFailed();
    }

    /**
     * @Date 09.26 2019
     * 갤러리의 메타 정보를 파싱 하여 가져오는 콜백 리스너
     */
    interface OnDCGalleryMetaParsedListener {
        void onSuccess(GalleryMeta meta);
        void onFailed();
    }

    /**
     * @Date 09.16 20190
     * IGNORE SSL CERT.
     */
    private SSLSocketFactory socketFactory() {
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[] {};
                    }
                }
        };

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch(Exception ex) {
            return null;
        }
    }

    private class ParseAsyncTask extends AsyncTask<String, Void, ArrayList<PostMeta>> {
        private OnDCGalleryParsedListener callback;

        public ParseAsyncTask(OnDCGalleryParsedListener callback) {
            this.callback = callback;
        }

        protected ArrayList<PostMeta> doInBackground(String[] urls) {
            ArrayList<PostMeta> postMetaArray = new ArrayList<>();

            try {
                Document document = Jsoup.connect(urls[0]).sslSocketFactory(socketFactory()).get();
                Elements posts = document.select("tr.us-post");

                for(int i = 0; i < posts.size(); ++i) {
                    Element aPost = posts.get(i);

                    // Make Post Struct.
                    PostMeta postMeta = new PostMeta(
                            aPost.getElementsByClass("gall_num").text(),     // uuid
                            DC_GALL_URL + aPost.getElementsByClass("gall_tit").get(0).getElementsByTag("a").get(0).attr("href"),
                            aPost.getElementsByClass("gall_tit").get(0).getElementsByTag("a").get(0).text(),          // title
                            aPost.getElementsByClass("ub-writer").attr("data-nick"),
                            DateUtils.stringToDate(aPost.getElementsByClass("gall_date").attr("title")),
                            Integer.parseInt(aPost.getElementsByClass("gall_count").text())
                    );
                    // Add new post
                    postMetaArray.add(postMeta);
                }
            } catch(Exception ex) {
                ex.printStackTrace();
                postMetaArray.clear();
            }

            return postMetaArray;
        }

        protected void onPostExecute(ArrayList<PostMeta> result) {
            super.onPostExecute(result);

            if(result != null && result.size() != 0) {
                callback.onSuccess(result);
            } else {
                callback.onFailed();
            }
        }
    }

    private class ParseGalleryMetaAsyncTask extends AsyncTask<String, Void, GalleryMeta> {
        private OnDCGalleryMetaParsedListener callback;

        public ParseGalleryMetaAsyncTask(OnDCGalleryMetaParsedListener callback) {
            this.callback = callback;
        }

        protected GalleryMeta doInBackground(String[] url) {
        try {
            Document document = Jsoup.connect(url[0]).sslSocketFactory(socketFactory()).get();
            Uri urlObj = Uri.parse(url[0]);

            // get gallery ID
            String galleryID = urlObj.getQueryParameter("id");
            if(galleryID == null) {
                List<String> seg = urlObj.getPathSegments();
                galleryID = seg.get(seg.size() - 1);
            }

            String galleryTitle = document.select("header .page_head .fl h2 a").text();

            if(galleryTitle == null || galleryID == null) {
                return null;
            }

            return new GalleryMeta(galleryTitle, galleryID, DateUtils.getNowToString());
        } catch(Exception ex) {
            return null;
        }
    }

        protected void onPostExecute(GalleryMeta result) {
            if(result == null) {
                callback.onFailed();
            } else {
                callback.onSuccess(result);
            }
        }
    }

    /**
     * @Date 09.16 2019
     * 파싱 작업을 수행 한다.
     */
    public void startParsing(String targetURL, OnDCGalleryParsedListener callback) {
        ParseAsyncTask task = new ParseAsyncTask(callback);
        task.execute(targetURL);
    }

    /**
     * @Date 09.30 2019
     * 갤러리의 메타 데이터 파싱을 수행 한다.
     */
    public void startMetaDataParsing(String targetURL, OnDCGalleryMetaParsedListener callback) {
        ParseGalleryMetaAsyncTask task = new ParseGalleryMetaAsyncTask(callback);
        task.execute(targetURL);
    }
}
