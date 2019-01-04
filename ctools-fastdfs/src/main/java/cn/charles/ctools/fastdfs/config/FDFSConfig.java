package cn.charles.ctools.fastdfs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * <p>Description: fastdfs属性</p>
 * @author charles·chen
 * @version v0.1
 */
@Data
@Configuration
@ConfigurationProperties("ctools.fdfs")
public class FDFSConfig {
	
	/**
	 * tracker_server追踪服务相关配置
	 */
	private FDFSFastdfsConfig fastdfs = new FDFSFastdfsConfig();
	
	/**
	 * web端访问追踪服务相关配置
	 */
	private FDFSHttpConfig http = new FDFSHttpConfig();
}
