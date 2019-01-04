package cn.charles.ctools.fastdfs.config;

import lombok.Data;

/**
 * <p>Description: fastdfs属性</p>
 * @author charles·chen
 * @version v0.1
 */
@Data
public class FDFSFastdfsConfig {
	
	/**
	 * tracker_servers追踪服务地址列表，默认 10.92.183.96:22122
	 */
	private String tracker_servers = "10.92.183.96:22122";
	
}
