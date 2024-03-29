package resourceSystem;

import java.util.Iterator;

public interface VirtualFileSystem {
	boolean isExist(String path);

	boolean isDirectory(String path);

	String getAbsolutePath(String file);

	byte[] getBytes(String file);

	String getUFT8Text(String file);

	Iterator<String> getIterator(String startDir);
	
	void printlnFiles();
	
}
