package com.badaklng.app.constant;

public final class Constants {
	public static final String APPLICATION_NAME = "General Support System";
	public static final String APPL_SHORTNAME = "Gen. Supp. System";
	public static final String APPL_CONTEXT = "GSS";
	public static final String APPL_SMS_KEY = "GSS";
	public static final String APPLICATION_DEV_URL = "http://10.10.214.45:7001/gss";
	public static final String APPLICATION_PROD_URL = "http://10.10.214.82:8035/gss";
	public static final boolean CORE_INSTANCE = true;
	public static final boolean IS_DEV_MODE = true;
	public static final String BASE_FORM_ATTR_KEY = "BaseForm";
	public static final int SCROLLABLE_FETCH_SIZE = 100;
	public static final int TEMPLATE_MAX_SIZE = 200000;
	public static final int TEMPLATE_PAP_MAX_SIZE = 10000000;
	public static final String QUARTZ_SCHEDULER_KEY = "org.quartz.impl.StdSchedulerFactory.KEY";
	public static final String QUARTZ_INSTANCE_NAME = "ApplQuartzScheduler";
	public static final String JOB_MAP_CATALOG_KEY = "ApplCatalog";
	public static final int PAGE_SIZE = 10;
	public static final String KEY = "ThisIsJustAKey O";
	public static final String VECTOR = "VectorTrought123";
	public static final String MAIL_HEADER = "<HTML><HEAD><META HTTP-EQUIV='Content-Type' CONTENT='text/html ; charset=UTF-8'><STYLE>.RepTitle {"
			+ "font-family: Verdana, Trebuchet, Helvetica, Arial; " + "font-size: 14pt; " + "color: #114477; "
			+ "border-bottom: 1px #BBBBBB solid; " + "margin-bottom: 10px; "
			+ "background-color: #FEFFEB } .Box { font-family: Verdana, Trebuchet, Helvetica, Arial; "
			+ "font-weight: normal; " + "font-size: 13pt; " + "border: 1px #BBBBBB dashed; " + "margin-bottom: 10px; "
			+ "padding: 10px; " + "} .BoxContent { font-family: Verdana, Trebuchet, Helvetica, Arial; "
			+ "font-weight: normal; " + "font-size: 10pt; " + "color : grey; " + "box-shadow: 3px 3px 3px #BBBBBB; "
			+ "padding: 5px; " + "} " + ".BoxLight { " + "font-family: 'Segoe UI', Trebuchet, Helvetica, Arial;"
			+ "font-weight: normal;" + "font-size: 10pt;" + "color: #5588BB;" + "border: none;" + "margin-bottom: 10px;"
			+ "padding: 10px;" + "background-color: #FFFFEC;" + "box-shadow: 3px 3px 3px #CCCCEE;}"
			+ ".BoxWarn { font-family: 'Segoe UI', Trebuchet, Helvetica, Arial; " + "font-weight: normal; "
			+ "font-size: 10pt; " + "color: #606060; " + "padding: 10px; " + "box-shadow: 3px 3px 3px #CCCCEE; "
			+ "background-color: #F9FF80; " + "} TABLE { border: 1px #BBBBBB solid; "
			+ "} td { font-family: Verdana, Trebuchet, Helvetica, Arial; " + "font-size: 10pt; "
			+ "font-weight: normal; " + "padding: 3px; "
			+ "} td.err { font-family: Verdana, Trebuchet, Helvetica, Arial; " + "font-weight: normal; "
			+ "font-size: 10pt; " + "color: #cc0000; " + "padding: 3px; "
			+ "} td.fix { font-family: Verdana, Trebuchet, Helvetica, Arial; " + "font-weight: normal; "
			+ "font-size: 10pt; " + "color: orange; " + "padding: 3px; "
			+ "} td.ok { font-family: Verdana, Trebuchet, Helvetica, Arial; " + "font-weight: normal; "
			+ "font-size: 10pt; " + "color: green; " + "padding: 3px; "
			+ "} th { font-family: Verdana, Trebuchet, Helvetica, Arial; " + "font-weight: bold; " + "font-size: 10pt; "
			+ "color: #606060; " + "padding: 3px; " + "background-color:#f2f5ff; "
			+ "} tr:hover { background-color: #f2f2f5; " + "} </STYLE> </HEAD><BODY><div class='Box'>";
	public static final String MAIL_FOOTER = "</div><div class='BoxLight'>Untuk mengunjungi aplikasi <b>"
			+ APPLICATION_NAME + "</b> silakan copy/klik alamat <a href='"
			+ (IS_DEV_MODE ? APPLICATION_DEV_URL : APPLICATION_PROD_URL) + "' target='_blank'>"
			+ (IS_DEV_MODE ? APPLICATION_DEV_URL : APPLICATION_PROD_URL) + "</a> , "
			+ " dan hanya dapat dibuka minimal menggunakan salah satu dari browser <span style='color:red'><b>Firefox, Google Chrome, Internet Explorer 11 atau Safari</b></span>.</div>"
			+ "<div class='BoxWarn'>This is automated " + APPLICATION_NAME
			+ " notification, please do not reply</div></BODY></HTML>";
	public static final String PUBLIC_MAIL_FOOTER = "</div>" + "<div class='BoxWarn'>This is automated "
			+ APPLICATION_NAME + " notification, please do not reply</div></BODY></HTML>";
	public static final Integer BATCH_SIZE_SMALL = 100;

	// public static final String DB_CONN =
	// "jdbc:oracle:thin:@10.10.1.77:1521:ELLPRD";

	/**
	 * First parameter must be an URL without question mark (?) Next 2 parameter
	 * must be an URL parameter with it's value
	 * 
	 * @param params
	 * @return complete URL with parameter
	 */
	public static String getURL(String... params) {
		String prefix = params[0] + "?";
		for (int i = 1; i < params.length; i += 2) {
			prefix += (i != 1 ? "&" : "") + params[i] + "=" + params[i + 1];
		}
		return prefix;
	}

}
