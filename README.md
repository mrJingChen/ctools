
### 项目结构说明
###### 基于spring-cloud-version:Finchley.SR2、spring-boot-version:2.0.6.RELEASE框架

+ 1.ctools：聚合工程

+ 2.ctools-common：工程中各项目公共依赖

+ 3.fastdfs-client-java：原项目-余庆

+ 4.ctools-fastdfs：对fastdfs-client-java进行了简单封装，整合spring-boot/cloud的同时使支持yaml配置文件


### 项目使用说明
> 1.`ctools-fastdfs` 如需使用fastdfs客户端，可直接引入该依赖即可

```
    <dependency>
    	<groupId>cn.charles</groupId>
		<artifactId>ctools-fastdfs</artifactId>
		<version>1.0</version>
	</dependency>
```

	/**
	 * 1.本地文件测试，以文件路径字符串入参
	 */
	 cn.charles.ctools.fastdfs.client.FastDFSClient.upload(String local_filename);
	 
	 /**
	 * 2.web端上传，以文件字节数组入参
	 */
	 cn.charles.ctools.fastdfs.client.FastDFSClient.upload(byte[] file_buff, String file_ext_name);
	 
	 /**
	 * 3.web端上传，以File类型入参，且File需要能获取到绝对路径File.getAbsolutePath()
	 */
	 cn.charles.ctools.fastdfs.client.FastDFSClient.upload(File file);
	 
	 /**
	 * 4.web端上传，(1).MultipartFile需要能获取到字节码，MultipartFile.getBytes()；
	 * (2).如发异常：java.io.FileNotFoundException....00.tmp (系统找不到指定的文件。)时，建议将文件转存到绝对路径下
	 */
	 cn.charles.ctools.fastdfs.client.FastDFSClient.upload(MultipartFile file);
	 
	 /**
	 * 5.web端上传，以文件输入流方式入参
	 */
	 cn.charles.ctools.fastdfs.client.FastDFSClient.upload(InputStream input, String originFileName)；


> 2.

