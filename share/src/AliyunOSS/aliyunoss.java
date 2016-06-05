package AliyunOSS;
import java.io.File;
import com.wuchao.Entity.User;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.junit.Test;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
public class aliyunoss {
	static String accessKeyId = "X9L0Y8BrV2V4xkWo";
	static String accessKeySecret = "hPC3Y4sbgkLGMIGDFjJ0VAFdikIsCd";
	// 以杭州为例
	//static String endpoint = "http://oss-cn-qingdao-internal.aliyuncs.com"; //内网址
	static String endpoint = "http://oss-cn-qingdao.aliyuncs.com";  //外网址
	static String bucketName = "documents-oss";
	static String folder1 = "Documents";//一级文件夹
	static String folder2 = "user_id";//二级文件夹
	
	// 初始化一个OSSClient
	//OSSClient client = new OSSClient(endpoint,accessKeyId, accessKeySecret);
	@Test
	public void testMethod() throws IOException{
		putObject(bucketName,folder1+"/"+folder2+"/"+"12","F:\\html.txt");
		//listObjects(bucketName);
		//getObject(bucketName,"12");
	}
	
	public static void createBucket(String bucketName) {
		// 初始化OSSClient
		OSSClient client = new OSSClient(endpoint,accessKeyId, accessKeySecret);
		// 新建一个Bucket
		client.createBucket(bucketName);
	}
	public static void putObject(String bucketName, String key, String filePath)throws FileNotFoundException{
		
		// 初始化OSSClient
		OSSClient client = new OSSClient(endpoint,accessKeyId, accessKeySecret);
		// 获取指定文件的输入流
		File file = new File(filePath);
		InputStream content = new FileInputStream(file);
		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();
		// 设置文件类型
		meta.setContentType("image/jpeg");
		// 必须设置ContentLength
		meta.setContentLength(file.length());
		// 上传Object.
		PutObjectResult result = client.putObject(bucketName, key, content, meta);
		
		// 打印ETag
		System.out.println(result.getETag());
		
	}
	public static void putObjectFile(String bucketName, String key, File file,String ContentType)throws FileNotFoundException{
		
		// 初始化OSSClient
		OSSClient client = new OSSClient(endpoint,accessKeyId, accessKeySecret);
		
		InputStream content = new FileInputStream(file);
		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();
		// 设置文件类型
		meta.setContentType(ContentType);
		// 必须设置ContentLength
		meta.setContentLength(file.length());
		// 上传Object.
		PutObjectResult result = client.putObject(bucketName, key, content, meta);
		// 打印ETag
		System.out.println(result.getETag());
		
	}
	
	public static void listObjects(String bucketName) {
		// 初始化OSSClient
		OSSClient client = new OSSClient(endpoint,accessKeyId, accessKeySecret);
		// 获取指定bucket下的所有Object信息
		ObjectListing listing = client.listObjects(bucketName);
		// 遍历所有Object
		for(OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
		System.out.println(objectSummary.getKey());
		}
	}
	
	public static void getObject(String bucketName, String key) throws IOException {
		// 初始化OSSClient
		OSSClient client = new OSSClient(endpoint,accessKeyId, accessKeySecret);
		// 获取Object，返回结果为OSSObject对象
		OSSObject object = client.getObject(bucketName, key);
		//获取object的一些信息
		ObjectMetadata meta = object.getObjectMetadata();
		String contentType = meta.getContentType();//获取object的文件类型
		System.out.println("contentType:"+contentType);
		// 获取Object的输入流
		InputStream objectContent = object.getObjectContent();
		// 处理Object
		File file = new File("file12");
		OutputStream os = new FileOutputStream(file);
		byte[] b = new byte[1024];
		int len = 0;
		while((len = objectContent.read(b))>0){
			for(int i=0;i<len;i++){
				os.write(b[i]);
			}
		}
		// 关闭流
		objectContent.close();
		}
	public static File getObjectFile(String bucketName, String key,User user) throws IOException {
		// 初始化OSSClient
		OSSClient client = new OSSClient(endpoint,accessKeyId, accessKeySecret);
		// 获取Object，返回结果为OSSObject对象
		OSSObject object = client.getObject(bucketName, key);
		//获取object的一些信息
		ObjectMetadata meta = object.getObjectMetadata();
		String contentType = meta.getContentType();//获取object的文件类型
		System.out.println("contentType:"+contentType);
		// 获取Object的输入流
		InputStream objectContent = object.getObjectContent();
		// 处理Object
		
		File file = new File(user.getId()+"");
		OutputStream os = new FileOutputStream(file);
		byte[] b = new byte[1024];
		int len = 0;
		while((len = objectContent.read(b))>0){
			for(int i=0;i<len;i++){
				os.write(b[i]);
			}
		}
		// 关闭流
		objectContent.close();
		return file;
		}
}
