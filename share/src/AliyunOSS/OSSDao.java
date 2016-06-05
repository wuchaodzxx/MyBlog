package AliyunOSS;

import java.io.File;
import java.io.InputStream;

public interface OSSDao {


	public String putFile(InputStream inoutStream, long length, String fileKey, String type, String folder);
	
	public void deleteDocument(String key);
	public boolean isExsist(String key);
}
