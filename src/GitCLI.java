import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;

public class GitCLI {

	public static boolean init(String dir) throws IOException {
		File folder = new File(dir);
		File gitFolder = new File(dir + Constants.GITC_DIR);
		File csvFile = new File(dir + Constants.GITC_DIR + "\\" + Constants.REPO_CSV);
		File changeFile = new File(dir + Constants.GITC_DIR + "\\" + Constants.CHANGE_CSV);
		if (gitFolder.exists()) {
			System.out.println("Existing git repository");
		} else {
			gitFolder.mkdirs();
			csvFile.createNewFile();
			changeFile.createNewFile();
			System.out.println("Initialized Git repository in " + gitFolder.getAbsolutePath());
		}
		return true;
	}

	public static void add(String dir) throws IOException {
		String csv = dir + Constants.GITC_DIR + "\\" + Constants.REPO_CSV;
		String changeCSV = dir + Constants.GITC_DIR + "\\" + Constants.CHANGE_CSV;
		PrintWriter pw = new PrintWriter(changeCSV);
		StringBuilder sb = new StringBuilder();
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();
		Reader in = new FileReader(csv);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
		HashMap<String, Long> remoteFilesMap = new HashMap<String, Long>();
		HashSet<String> localFilesSet = new HashSet<String>();

		for (CSVRecord record : records) {
			remoteFilesMap.put(record.get(0), Long.parseLong(record.get(1)));
		}
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String fileName = listOfFiles[i].getName();
				localFilesSet.add(fileName);
				long timestamp = listOfFiles[i].lastModified();
				if (remoteFilesMap.containsKey(fileName)) {
					if (timestamp != remoteFilesMap.get(fileName)) {
						sb.append(Constants.UPDATE + "," + fileName + "\n");
					}
				} else {
					sb.append(Constants.ADD + "," + fileName + "\n");
				}
			}
		}

		for (String fileName : remoteFilesMap.keySet()) {
			if (!localFilesSet.contains(fileName)) {
				sb.append(Constants.DELETE + "," + fileName + "\n");
			}
		}

		pw.write(sb.toString());
		pw.close();
	}

	public static void push(String dir) throws Exception {
		String csv = dir + Constants.GITC_DIR + "\\" + Constants.REPO_CSV;
		PrintWriter pw = new PrintWriter(csv);
		String changeCSV = dir + Constants.GITC_DIR + "\\" + Constants.CHANGE_CSV;
		StringBuilder sb = new StringBuilder();
		Reader in = new FileReader(changeCSV);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
		HashMap<String, Integer> files = GitChain.getLatestFiles();
		for (CSVRecord record : records) {
			String operation = record.get(0);
			if (operation.equals(Constants.ADD)) {
				String hash = IPFSUtil.uploadToIPFS(dir + record.get(1));
				GitChain.uploadFile(record.get(1) + "@1", hash);
			} else if (operation.equals(Constants.UPDATE)) {
				String hash = IPFSUtil.uploadToIPFS(dir + record.get(1));
				GitChain.uploadFile(record.get(1) + "@" + (files.get(record.get(1)) + 1), hash);
			}
		}

		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String fileName = listOfFiles[i].getName();
				long timestamp = listOfFiles[i].lastModified();
				sb.append(fileName + "," + timestamp + "\n");
			}
		}
		pw.write(sb.toString());
		pw.close();
		PrintWriter writer = new PrintWriter(changeCSV);
		writer.print("");
		writer.close();
	}

	public static void pull(String dir) throws Exception {
		FileUtils.deleteDirectory(new File(dir));
		clone(dir);
	}

	public static void clone(String dir) throws Exception {
		HashMap<String, Integer> fileMap = GitChain.getLatestFiles();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		String csv = dir + Constants.GITC_DIR + "\\" + Constants.REPO_CSV;
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Integer> entry : fileMap.entrySet()) {
			String hash = GitChain.retrieveHash(entry.getKey() + "@" + entry.getValue());
			hashMap.put(entry.getKey(), hash);
		}
		for (Map.Entry<String, String> entry : hashMap.entrySet()) {
			byte[] fileContent = IPFSUtil.readFileFromIPFS(entry.getValue());
			System.out.println("File " + entry.getKey() + " retrieved from IPFS.");
			FileUtils.writeByteArrayToFile(new File(dir + entry.getKey()), fileContent);
		}
		init(dir);
		PrintWriter pw = new PrintWriter(csv);
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String fileName = listOfFiles[i].getName();
				long timestamp = listOfFiles[i].lastModified();
				sb.append(fileName + "," + timestamp + "\n");
			}
		}
		pw.write(sb.toString());
		pw.close();
	}

}
