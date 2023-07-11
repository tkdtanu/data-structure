package com.tkd.design.pattern.factory;

enum StorageType {
	DB, DISK, S3
}

class File {
	public String name;
	public String fileType; // csv/xml/json
	public StorageType storageType; // DB/S3/DISK
}

interface FileProcessor {
	void processFile(File file);
}

class DbFileProcessor implements FileProcessor {

	@Override
	public void processFile(File file) {
		System.out.println("File:" + file.name + ", Got processed into DB");
	}

}

class DiskFileProcessor implements FileProcessor {

	@Override
	public void processFile(File file) {
		System.out.println("File:" + file.name + ", Got processed into Disk");
	}

}

class S3FileProcessor implements FileProcessor {

	@Override
	public void processFile(File file) {
		System.out.println("File:" + file.name + ", Got processed into S3");
	}

}

class FileProcessorFactory {
	public static FileProcessor getFileProcessor(StorageType storageType) {
		if (storageType.equals(StorageType.DB)) {
			return new DbFileProcessor();
		}
		if (storageType.equals(StorageType.DISK)) {
			return new DiskFileProcessor();
		}
		if (storageType.equals(StorageType.S3)) {
			return new S3FileProcessor();
		}

		throw new RuntimeException("Invalid file storage type");
	}
}

public class FactoryTest {
	public static void main(String[] args) {

		// Other examples are
		// 1. Factory for OrderProcessor For Different Marketplace
		// 2. Factory for OMSOrderPublisher for SQS and Kafka. Either Order will be
		// published to SQS or Kafka
		// 3. 
		File file = new File();
		file.name = "Abc.csv";
		file.fileType = "csv";
		file.storageType = StorageType.DB;

		FileProcessorFactory.getFileProcessor(file.storageType).processFile(file);

		file.storageType = StorageType.DISK;
		FileProcessorFactory.getFileProcessor(file.storageType).processFile(file);

		file.storageType = StorageType.S3;
		FileProcessorFactory.getFileProcessor(file.storageType).processFile(file);
	}
}
