package com.shopme.common;


public class Constants {
	
	private Constants(){
		throw new IllegalStateException("Utility class");
	}
	public static final String S3_BASE_URI;
	
	static {
		String bucketName = System.getenv("AWS_BUCKET_NAME");
		String region = System.getenv("AWS_REGION");
		String pattern = "https://%s.s3.%s.amazonaws.com";
		
		S3_BASE_URI = bucketName == null ? "" : String.format(pattern, bucketName, region);
	}
}
