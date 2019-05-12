package microgram.impl.srv.rest;

import static microgram.api.java.Result.error;
import static microgram.api.java.Result.ErrorCode.CONFLICT;
import static microgram.api.java.Result.ErrorCode.INTERNAL_ERROR;
import static microgram.api.java.Result.ErrorCode.NOT_FOUND;

import java.io.File;
import java.nio.file.Files;
import java.util.Random;

import microgram.api.java.MediaStorage;
import microgram.api.java.Result;
import utils.Hash;

public class JavaMedia implements MediaStorage {

	private static final String MEDIA_EXTENSION = ".jpg";
	private static final String ROOT_DIR = "/tmp/microgram/";

	static final Random random = new Random();
	
	public JavaMedia() {
		new File(ROOT_DIR).mkdirs();
	}

	@Override
	public Result<String> upload(byte[] bytes) {
		try {
			String id = Hash.of(bytes);
			File filename = new File(ROOT_DIR + id + MEDIA_EXTENSION);

			if (filename.exists())
				return Result.error(CONFLICT);

			Files.write(filename.toPath(), bytes);
			return Result.ok(id);
		} catch (Exception x) {
			return error(INTERNAL_ERROR);
		}
	}

	@Override
	public Result<byte[]> download(String id) {
		try {
			File filename = new File(ROOT_DIR + id + MEDIA_EXTENSION);
			if (filename.exists())
				return Result.ok(Files.readAllBytes(filename.toPath()));
			else
				return Result.error(NOT_FOUND);
		} catch (Exception x) {
			return Result.error(INTERNAL_ERROR);
		}
	}
	
	@Override
	public Result<Void> delete(String id) {
		throw new RuntimeException("Not implemented...");	
	}
}
