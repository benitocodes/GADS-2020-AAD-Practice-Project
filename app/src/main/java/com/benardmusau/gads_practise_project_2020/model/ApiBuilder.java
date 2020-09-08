package com.benardmusau.gads_practise_project_2020.model;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.benardmusau.gads_practise_project_2020.Constants.BASE_API_URL;
import static com.benardmusau.gads_practise_project_2020.Constants.SUBMIT_API_URL;

/***
 * Application was developed by Software Developer Benard Musau for GADS 2020 practice project
 * email: musaubm3@gmail.com
 */

public class ApiBuilder {

	/**
	 * create the parameters needed for retrofit and add a logger
	 */

	private static HttpLoggingInterceptor logger = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

	private static OkHttpClient.Builder oBuilder = new OkHttpClient.Builder().addInterceptor(logger);
	private static OkHttpClient.Builder oBuilder2 = new OkHttpClient.Builder().addInterceptor(logger);

	private static Retrofit.Builder sBuilder = new Retrofit.Builder().baseUrl(BASE_API_URL).addConverterFactory(GsonConverterFactory.create()).client(oBuilder.build());
	private static Retrofit.Builder sBuilderSubmit = new Retrofit.Builder().baseUrl(SUBMIT_API_URL).addConverterFactory(GsonConverterFactory.create()).client(oBuilder2.build());


	private static Retrofit sRetrofit = sBuilder.build();
	private static Retrofit sRetrofitSubmit = sBuilderSubmit.build();

	public static <S> S buildService(Class<S> serviceType) {
		return sRetrofit.create(serviceType);
	}

	public static <S> S buildSubmitService(Class<S> serviceType) {
		return sRetrofitSubmit.create(serviceType);
	}
}
