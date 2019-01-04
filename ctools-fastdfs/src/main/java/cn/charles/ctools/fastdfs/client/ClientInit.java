package cn.charles.ctools.fastdfs.client;

import java.util.Properties;

import org.csource.fastdfs.ClientGlobal;

import cn.charles.ctools.fastdfs.config.FDFSConfig;

/**
 * <p>Description: 客户端配置初始化</p>
 * @author charles·chen
 * @version v0.1
 */
public class ClientInit {
	
	/**
	  	public static final String CONF_KEY_CONNECT_TIMEOUT = "connect_timeout";
	  	public static final String CONF_KEY_NETWORK_TIMEOUT = "network_timeout";
	  	public static final String CONF_KEY_CHARSET = "charset";
	  	public static final String CONF_KEY_HTTP_ANTI_STEAL_TOKEN = "http.anti_steal_token";
	  	public static final String CONF_KEY_HTTP_SECRET_KEY = "http.secret_key";
	  	public static final String CONF_KEY_HTTP_TRACKER_HTTP_PORT = "http.tracker_http_port";
	  	public static final String CONF_KEY_TRACKER_SERVER = "tracker_server";
	
	  	public static final String PROP_KEY_CONNECT_TIMEOUT_IN_SECONDS = "fastdfs.connect_timeout_in_seconds";
	  	public static final String PROP_KEY_NETWORK_TIMEOUT_IN_SECONDS = "fastdfs.network_timeout_in_seconds";
	  	public static final String PROP_KEY_CHARSET = "fastdfs.charset";
	  	public static final String PROP_KEY_HTTP_ANTI_STEAL_TOKEN = "fastdfs.http_anti_steal_token";
	  	public static final String PROP_KEY_HTTP_SECRET_KEY = "fastdfs.http_secret_key";
	  	public static final String PROP_KEY_HTTP_TRACKER_HTTP_PORT = "fastdfs.http_tracker_http_port";
	  	public static final String PROP_KEY_TRACKER_SERVERS = "fastdfs.tracker_servers";
	 *
	 */
	/**
	 * @version v0.1
	 * @param fDFSConfig
	 * @throws Exception
	 */
	public static void initByFDFSConfig(FDFSConfig fDFSConfig) throws Exception{
		Properties props = new Properties();
		/** fastdfs */
		props.setProperty(ClientGlobal.PROP_KEY_TRACKER_SERVERS, fDFSConfig.getFastdfs().getTracker_servers());
		
		/** http */
		props.setProperty(ClientGlobal.CONF_KEY_HTTP_TRACKER_HTTP_PORT, fDFSConfig.getHttp().getTracker_http_port());
		
		ClientGlobal.initByProperties(props);
	}
}
