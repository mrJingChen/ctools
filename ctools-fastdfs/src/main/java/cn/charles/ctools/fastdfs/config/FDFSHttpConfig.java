package cn.charles.ctools.fastdfs.config;

import lombok.Data;

/**
 * <p>Description: http属性</p>
 * @author charles·chen
 * @version v0.1
 */
@Data
public class FDFSHttpConfig {
	
	/**
	 * web端访问追踪服务http协议端口，默认：80
	 */
	private String tracker_http_port = "80";
	
}
