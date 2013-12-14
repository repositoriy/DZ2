package resourceSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;



public class VirtualFileSystemImpl implements VirtualFileSystem {
	

	private String root;
	private File file;
	private static VirtualFileSystemImpl virtualFileSystemImpl;
	
	public static VirtualFileSystemImpl instance(){
		if (virtualFileSystemImpl == null)	virtualFileSystemImpl = new VirtualFileSystemImpl();
		return virtualFileSystemImpl;
	}
	
	public static VirtualFileSystemImpl instance(String directory){
		if (virtualFileSystemImpl == null)	virtualFileSystemImpl = new VirtualFileSystemImpl(directory);
		return virtualFileSystemImpl;
	}
	
	
	private VirtualFileSystemImpl(){		
		this.root = "D:\\serverTP2sem\\data";		
	}
	private VirtualFileSystemImpl(String directory){		
		this.root = directory;		
	}
	
	
	

	@Override
	public boolean isExist(String path) {
		this.file = new File(path);
		if (this.file.exists()) return true;
		else return false;
	}

	@Override
	public boolean isDirectory(String path) {		
		this.file = new File(path);
		if (file.isDirectory()) return true;
		else return false;
	}

	@Override
	public String getAbsolutePath(String file) {
		this.file = new File(file);
		return this.file.getAbsolutePath();
	}

	@Override
	public byte[] getBytes(String file) {
		this.file = new File(file);
		return file.getBytes();
	
	}

	@Override
	public String getUFT8Text(String file) {
		this.file = new File(file);
		StringBuilder stringBuilder = new StringBuilder();
		
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((new FileInputStream(file)), "UTF-8"))){
			String buffer;			
			try {
				while ((buffer = bufferedReader.readLine())!=null){
					stringBuilder.append(buffer);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return stringBuilder.toString();
	}
	private class FileIterator implements Iterator<String>{

		private Queue<File> files = new LinkedList<File>();

		public FileIterator(String path){
			files.add(new File(root + path));
		}
		public boolean hasNext() {
			return !files.isEmpty();
		}
		public String next() {
			File file = files.peek();
			if(file.isDirectory()){
				try{
				for(File subFile : file.listFiles()){
					files.add(subFile);
				}
				}catch (NullPointerException e){
					e.printStackTrace();
				}
			}
			return files.poll().getAbsolutePath();
		}
		public void remove() {
		}
	
		
	}
	public Iterator<String> getIterator(String startDir) {
		return  new FileIterator(startDir);
	}
	public void printlnFiles(){
		Iterator<String> iterator = this.getIterator("\\");
		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}

}
