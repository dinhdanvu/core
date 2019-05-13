package com.flex.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Vector;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.nio.charset.Charset;

/**
 * 
 * @author jincheng
 *
 */
public class FlexHttpRequester {
	private String _defaultContentEncoding;

	public FlexHttpRequester() {
		this._defaultContentEncoding = Charset.defaultCharset().name();
	}

	public static void main(String[] args) {
		String url = "http://127.0.0.1/test.php?a=jincheng";
		try {
			FlexHttpRequester request = new FlexHttpRequester();
			HttpRespons hr = request.sendGet(url);

			// System.out.println(hr.getUrlString());
			// System.out.println(hr.getProtocol());
			// System.out.println(hr.getHost());
			// System.out.println(hr.getPort());
			// System.out.println(hr.getContentEncoding());
			// System.out.println(hr.getMethod());

			System.out.println(hr.getContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * GET
	 * 
	 * @param urlString
	 *            URL
	 * @return
	 * @throws IOException
	 */
	public HttpRespons sendGet(String urlString) throws IOException {
		return this.send(urlString, "GET", null, null);
	}

	/**
	 * GET
	 * 
	 * @param urlString
	 *            URL
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public HttpRespons sendGet(String urlString, Map<String, String> params) throws IOException {
		return this.send(urlString, "GET", params, null);
	}

	/**
	 * 
	 * @param urlString
	 * @param params
	 * @param propertys
	 * @return
	 * @throws IOException
	 */
	public HttpRespons sendGet(String urlString, Map<String, String> params, Map<String, String> propertys)
			throws IOException {
		return this.send(urlString, "GET", params, propertys);
	}

	/**
	 * 
	 * @param urlString
	 * @return
	 * @throws IOException
	 */
	public HttpRespons sendPost(String urlString) throws IOException {
		return this.send(urlString, "POST", null, null);
	}

	/**
	 * 
	 * @param urlString
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public HttpRespons sendPost(String urlString, Map<String, String> params) throws IOException {
		return this.send(urlString, "POST", params, null);
	}

	/**
	 * 
	 * @param urlString
	 * @param params
	 * @param propertys
	 * @return
	 * @throws IOException
	 */
	public HttpRespons sendPost(String urlString, Map<String, String> params, Map<String, String> propertys)
			throws IOException {
		return this.send(urlString, "POST", params, propertys);
	}

	/**
	 * 
	 * @param urlString
	 * @return
	 * @throws IOException
	 */
	private HttpRespons send(String urlString, String method, Map<String, String> parameters,
			Map<String, String> propertys) throws IOException {
		HttpURLConnection urlConnection = null;

		if (method.equalsIgnoreCase("GET") && parameters != null) {
			StringBuffer param = new StringBuffer();
			int i = 0;
			for (String key : parameters.keySet()) {
				if (i == 0)
					param.append("?");
				else
					param.append("&");
				param.append(key).append("=").append(parameters.get(key));
				i++;
			}
			urlString += param;
		}
		URL url = new URL(urlString);
		urlConnection = (HttpURLConnection) url.openConnection();

		urlConnection.setRequestMethod(method);
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);

		if (propertys != null)
			for (String key : propertys.keySet()) {
				urlConnection.addRequestProperty(key, propertys.get(key));
			}

		if (method.equalsIgnoreCase("POST") && parameters != null) {
			StringBuffer param = new StringBuffer();
			for (String key : parameters.keySet()) {
				param.append("&");
				param.append(key).append("=").append(parameters.get(key));
			}
			urlConnection.getOutputStream().write(param.toString().getBytes());
			urlConnection.getOutputStream().flush();
			urlConnection.getOutputStream().close();
		}

		return this.makeContent(urlString, urlConnection);
	}

	/**
	 * 
	 * @param urlConnection
	 * @return
	 * @throws IOException
	 */
	private HttpRespons makeContent(String urlString, HttpURLConnection urlConnection) throws IOException {
		HttpRespons httpResponser = new HttpRespons();
		try {
			InputStream in = urlConnection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
			httpResponser.contentCollection = new Vector<String>();
			StringBuffer temp = new StringBuffer();
			String line = bufferedReader.readLine();
			while (line != null) {
				httpResponser.contentCollection.add(line);
				temp.append(line).append("\r\n");
				line = bufferedReader.readLine();
			}
			bufferedReader.close();

			String ecod = urlConnection.getContentEncoding();
			if (ecod == null)
				ecod = this._defaultContentEncoding;

			httpResponser.urlString = urlString;

			httpResponser.defaultPort = urlConnection.getURL().getDefaultPort();
			httpResponser.file = urlConnection.getURL().getFile();
			httpResponser.host = urlConnection.getURL().getHost();
			httpResponser.path = urlConnection.getURL().getPath();
			httpResponser.port = urlConnection.getURL().getPort();
			httpResponser.protocol = urlConnection.getURL().getProtocol();
			httpResponser.query = urlConnection.getURL().getQuery();
			httpResponser.ref = urlConnection.getURL().getRef();
			httpResponser.userInfo = urlConnection.getURL().getUserInfo();

			httpResponser.content = new String(temp.toString().getBytes(), ecod);
			httpResponser.contentEncoding = ecod;
			httpResponser.code = urlConnection.getResponseCode();
			httpResponser.message = urlConnection.getResponseMessage();
			httpResponser.contentType = urlConnection.getContentType();
			httpResponser.method = urlConnection.getRequestMethod();
			httpResponser.connectTimeout = urlConnection.getConnectTimeout();
			httpResponser.readTimeout = urlConnection.getReadTimeout();

			return httpResponser;
		} catch (IOException e) {
			throw e;
		} finally {
			if (urlConnection != null)
				urlConnection.disconnect();
		}
	}
/**
 * support https
 * @param url
 * @param param
 * @param contentType
 * @return
 */
	public static String httpClientRequest(String url,String param,String contentType){
    	String result = "";
    	HttpClient client = HttpClientBuilder.create().build();
    	HttpPost post = new HttpPost(url);
        try {
        	post.setHeader("Content-Type", contentType);
        	StringEntity dataEntity = new StringEntity(param);
            post.setEntity(dataEntity);
            HttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
            	result+=line;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
	/**
	 * get encode mac dinh
	 */
	public String getDefaultContentEncoding() {
		return this._defaultContentEncoding;
	}

	/**
	 * set encode mac dinh
	 */
	public void setDefaultContentEncoding(String defaultContentEncoding) {
		this._defaultContentEncoding = defaultContentEncoding;
	}

	public class HttpRespons {
		String urlString;
		int defaultPort;
		String file;
		String host;
		String path;
		int port;
		String protocol;
		String query;
		String ref;
		String userInfo;
		String contentEncoding;
		String content;
		String contentType;
		int code;
		String message;
		String method;
		int connectTimeout;
		int readTimeout;
		Vector<String> contentCollection;

		public String getContent() {
			return content;
		}

		public String getContentType() {
			return contentType;
		}

		public int getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}

		public Vector<String> getContentCollection() {
			return contentCollection;
		}

		public String getContentEncoding() {
			return contentEncoding;
		}

		public String getMethod() {
			return method;
		}

		public int getConnectTimeout() {
			return connectTimeout;
		}

		public int getReadTimeout() {
			return readTimeout;
		}

		public String getUrlString() {
			return urlString;
		}

		public int getDefaultPort() {
			return defaultPort;
		}

		public String getFile() {
			return file;
		}

		public String getHost() {
			return host;
		}

		public String getPath() {
			return path;
		}

		public int getPort() {
			return port;
		}

		public String getProtocol() {
			return protocol;
		}

		public String getQuery() {
			return query;
		}

		public String getRef() {
			return ref;
		}

		public String getUserInfo() {
			return userInfo;
		}
	}
}
