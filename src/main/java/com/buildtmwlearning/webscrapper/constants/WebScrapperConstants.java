package com.buildtmwlearning.webscrapper.constants;

public class WebScrapperConstants {
    public static final String URL= " https://www.webull.com/quote/exthoursranking";
    public static  final String CSS_CLASS_SELECTOR="wbus-ssr";
    public static  final String TAG_SELECTOR="span";
    public static final String  SUCCESS_MESSAGE="Successfully Created";
    public static final String  FAIL_MESSAGE="ERROR or FAILURE Creating";
    public static final String SCRAP_SWAGGER_VALUE="Fetches the " +
            "data from"+URL+" and populates " +
            "the data to Pojo";
    public static final String RETRIEVE_SWAGGER_VALUE="Retrieves the List of Scrapper Object";
    public static final String CREATECSV_SWAGGER_VALUE="Creates CSV in the directory";


}
