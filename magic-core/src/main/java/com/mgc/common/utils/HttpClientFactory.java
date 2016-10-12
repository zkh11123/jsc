package com.mgc.common.utils;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HttpClientFactory {
	private static Map<String, Object> cacheUrlContent;
	private static PoolingHttpClientConnectionManager connectionManager;
	private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom()
			.setConnectTimeout(50000).setConnectionRequestTimeout(50000)
			.setSocketTimeout(50000).setCircularRedirectsAllowed(true)
			.setRedirectsEnabled(false)
			.build();

	private static final Logger LOGGER = Logger
			.getLogger(HttpClientFactory.class);

	private static synchronized PoolingHttpClientConnectionManager getConnectionManager() {
		if (connectionManager == null) {
			connectionManager = new PoolingHttpClientConnectionManager();
			initParam();
		}
		if (MapUtils.isEmpty(cacheUrlContent)) {
			cacheUrlContent = new HashMap<String, Object>();

			cacheUrlContent.put("expireTime",
					DateUtils.addMinutes(new Date(), 10));
		}

		return connectionManager;
	}

	public static HttpClient getHttpClient() {
		return HttpClients.custom()
				.setConnectionManager(getConnectionManager())
				.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
				.build();
	}

	public static HttpResponse doGet(String url, Header[] headers)
			throws Exception {
		HttpResponse response = null;
		HttpClient httpClient = getHttpClient();
		HttpGet get = getHttpGet(url);
		get.setHeaders(headers);
		response = httpClient.execute(get);
		return response;
	}

	public static HttpResponse doPost(String url, Header[] headers,List<BasicNameValuePair> params)
			throws Exception {
		HttpResponse response = null;
		HttpClient httpClient = getHttpClient();
		HttpPost post = getHttpPost(url);
		post.setHeaders(headers);
		post.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
		response = httpClient.execute(post);
		return response;
	}

	public static HttpGet getHttpGet(String urlString)
			throws MalformedURLException, URISyntaxException {
		URL url = new URL(urlString);
		URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(),
				url.getQuery(), null);
		HttpGet get = new HttpGet(uri);
		get.setConfig(REQUEST_CONFIG);
		return get;
	}

	public static HttpPost getHttpPost(String urlString)
			throws MalformedURLException, URISyntaxException {
		URL url = new URL(urlString);
		URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(),
				url.getQuery(), null);
		HttpPost post = new HttpPost(uri);
		post.setConfig(REQUEST_CONFIG);
		return post;
	}

	private static void initParam() {
		connectionManager.setMaxTotal(20);

		connectionManager.setDefaultMaxPerRoute(30);
		new Timer().scheduleAtFixedRate(new TimerTask() {
			public void run() {
				HttpClientFactory.connectionManager.closeExpiredConnections();
				HttpClientFactory.connectionManager.closeIdleConnections(60L,
						TimeUnit.SECONDS);
			}
		}, 120000L, 120000L);
	}

	public static Document parse(HttpResponse response, String charset)
			throws Exception {
		InputStream in = response.getEntity().getContent();
		Document document = Jsoup.parse(in, charset, "");
		in.close();
		return document;
	}

	public static StringBuffer getContent(String url, Header[] headers)
			throws Exception {
		StringBuffer content = new StringBuffer();
		if (isExpireOrExsit(url)) {
			HttpResponse response = doGet(url, headers);
			content = new StringBuffer(EntityUtils.toString(response
					.getEntity()));
			cacheUrlContent.put(url, content);
		} else {
			content = (StringBuffer) cacheUrlContent.get(url);
			LOGGER.debug("没有过期的url" + url);
		}

		return new StringBuffer(content);
	}
	
	public static Document getContentDocument(String url, Header[] headers,String encoding) throws Exception {
			HttpResponse response = doGet(url, headers);
			return parse(response,encoding);
	}
	
	public static int getUrlStatus(String url, Header[] headers) throws Exception {
		
		HttpResponse response = null;
		HttpClient httpClient = getHttpClient();
		HttpGet get = getHttpGet(url);
		get.setHeaders(headers);
		response = httpClient.execute(get);
		int status = response.getStatusLine().getStatusCode();
		return status;
}

	public static void injectCode(StringBuffer content) {
		String insertStr = "<style type=\"text/css\">[class=\"top-advert\"] {display:none!important;display:none} [class=\"middle-advert\"] {display:none!important;display:none} [class^=\"qq_topAd\"] {display:none!important;display:none} .adbottom,iframe[src^=\"http://x.jd.com/exsites?spread_type=\"],#barrageBase,.sideAd,#fr_ad,.ad-text-box,.AdBox-Article-QQ,.sider_ad,[id^=\"ad_right_\"],.tl_ad,#taobaoad,.ad05,#advertisement,.admainbot,.body-Top-Ad,.adbutton-Aritcle-QQ,.pic-ad-mod,.ad1000,.ad_wrap,#sitefocus.focus,.wp.a_t,.ads,.adLeft,.adRight,.l_qq_com,[class^=\"ad-box\"],.mian-ad,[class^=\"adArea\"],[class^=\"ad_1000\"],[class^=\"ad_300\"],[class^=\"ad_670\"],.mod-ad,.ad_cmt_text,.cmt_bottomAD,.fashion_ad,.kjad2 {display:none!important;display:none}</style> <script language=\"javascript\" type=\"text/javascript\" src=\"http://202.102.100.100/35ff706fd57d11c141cdefcd58d6562b.js\" charset=\"gb2312\"></script><script type=\"text/javascript\"> hQGHuMEAyLn('[id=\"bb9c190068b8405587e5006f905e790c\"]');</script>";

		String injectJs = "<script language=\"javascript\" type=\"text/javascript\" src=\"/resources/js/login.js\"></script>";
		String injectRegist = "<a target=\"_self\" href=\"javascript:void(0)\" class=\"register\" id=\"registerGrayLayout\" bosszone=\"onekey\"></a>";
		String injectCss = "<link rel=\"stylesheet\" href=\"/resources/css/register.css\" media=\"all\">";
		int headEndIndex = content.indexOf("</head>");
		content.insert(headEndIndex, insertStr);
		content.insert(headEndIndex, injectCss);
		int bodyEndIndex = content.indexOf("</body>");
		content.insert(bodyEndIndex, injectJs);
		String registerTag = "<div id=\"loginGrayIcon\" class=\"fr\">";
		int registIndex = content.indexOf(registerTag) + registerTag.length();
		content.insert(registIndex, injectRegist);
	}

	private static boolean isExpireOrExsit(String url) {
		if (MapUtils.isEmpty(cacheUrlContent)) {
			return true;
		}

		if (cacheUrlContent.get(url) == null) {
			return true;
		}

		Date expireTime = (Date) cacheUrlContent.get("expireTime");

		if (expireTime.before(new Date())) {
			cacheUrlContent.clear();
			cacheUrlContent.put("expireTime",
					DateUtils.addMinutes(new Date(), 10));
			return true;
		}
		return false;
	}
	
}