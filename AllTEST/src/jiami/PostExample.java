package jiami;

import java.io.IOException;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class PostExample {
	public static final MediaType JSON = MediaType
			.parse("application/json; charset=utf-8");

	OkHttpClient client = new OkHttpClient();

	String post(String url, String json) throws IOException {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	String bowlingJson() {
		return "";
	}
	/*
	 * public static void main(String[] args) throws IOException { PostExample
	 * example = new PostExample(); String json = example.bowlingJson("Jesse",
	 * "Jake"); String response = example.post("http://www.roundsapp.com/post",
	 * json); System.out.println(response); }
	 */
}
