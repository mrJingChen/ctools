package cn.charles.ctools.fastdfs.client;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import cn.charles.ctools.fastdfs.config.FDFSConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Description: fastdfs客户端</p>
 * @author charles·chen
 * @version v0.1
 */
@Slf4j
@Component
public class FastDFSClient {
	
	@Autowired
	private FDFSConfig fDFSConfig;
	
	private static TrackerServer trackerServer;
	private static StorageServer storageServer;
	
	@PostConstruct
	private void init(){
		try {
			ClientInit.initByFDFSConfig(fDFSConfig);
			log.info("--------------->ClientInit.initByFDFSConfig:{}", fDFSConfig.toString());
			TrackerClient trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			storageServer = trackerClient.getStoreStorage(trackerServer);
		} catch (Exception e) {
			log.error("FastDFS Client init error: "+e.getMessage(), e);
		}
	}
	
	/**
	 * local test
	 * @version v0.1
	 * @param local_filename
	 * @return
	 */
	public static String upload(String local_filename) {
		String fileId = null;
		try {
			StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
	        fileId = storageClient.upload_file1(local_filename, null, null);
		} catch (Exception e) {
			log.error("FastDFSClientUtil/upload error:"+e.getMessage(), e);
		}
        return fileId;
	}
	
	/**
	 * web upload
	 * @version v0.1
	 * @param file_buff		file content/buff
	 * @param file_ext_name
	 * @return
	 */
	public static String upload(byte[] file_buff, String file_ext_name) {
		String fileId = null;
		try {
			StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
			fileId = storageClient.upload_file1(file_buff, file_ext_name, null);
			log.info("---->FastDFSClientUtil/upload - fileId: {}", fileId);
		} catch (Exception e) {
			log.error("FastDFSClientUtil/upload error:"+e.getMessage(), e);
		}
		return fileId;
	}
	
	/**
	 * File需要能获取到绝对路径，File.getAbsolutePath()；
	 * @version v0.1
	 * @param file
	 * @return 文件id，如：group1/M00/00/00/Cly3YFwube2AU7d5AAhdYg5ImOI516.jpg
	 */
	public static String upload(File file) {
		String fileId = null;
		try {
			String originFileName = file.getName();
			String fileExtName = originFileName.substring(originFileName.lastIndexOf(".") + 1);
			String local_filename = file.getAbsolutePath();
			StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
			fileId = storageClient.upload_file1(local_filename, fileExtName, null);
			log.info("---->FastDFSClientUtil/upload - fileId: {}", fileId);
		} catch (Exception e) {
			log.error("FastDFSClientUtil/upload error:"+e.getMessage(), e);
		}
		return fileId;
	}
	
	/**
	 * 1.MultipartFile需要能获取到字节码，MultipartFile.getBytes()；
	 * 2.如发异常：java.io.FileNotFoundException....00.tmp (系统找不到指定的文件。)时，建议将文件转存到绝对路径下
	 * @version v0.1
	 * @param file 待上传文件
	 * @return 文件id，如：group1/M00/00/00/Cly3YFwube2AU7d5AAhdYg5ImOI516.jpg
	 */
	public static String upload(MultipartFile file) {
		String fileId = null;
		if(null != file){
			try {
				String originFile = file.getOriginalFilename();
				String fileExtName = originFile.substring(originFile.lastIndexOf(".") + 1);
				byte[] byt = file.getBytes();
				fileId = upload(byt, fileExtName);
			} catch (Exception e) {
				log.error("FastDFSClientUtil/upload error:"+e.getMessage(), e);
			}
		}
		return fileId;
	}
	
	/**
	 * @version v0.1
	 * @param input 文件输入流
	 * @param originFileName 带后缀文件名
	 * @return
	 */
	public static String upload(InputStream input, String originFileName) {
		String fileId = null;
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		try {
			byte[] buff = new byte[1024 * 4];
			int rc = 0; 
			while ((rc = input.read(buff, 0, 100)) > 0) { 
				swapStream.write(buff, 0, rc); 
			}
			input.close();
			swapStream.close();
			byte[] inputByte = swapStream.toByteArray();
			String fileExtName = originFileName.substring(originFileName.lastIndexOf(".") + 1);
			fileId = upload(inputByte, fileExtName);
		} catch (Exception e) {
			log.error("FastDFSClientUtil/upload error:"+e.getMessage(), e);
		}finally {
            if (input != null) {
                try {
                	input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (swapStream != null) {
            	try {
					swapStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
		return fileId;
	}
	
}
