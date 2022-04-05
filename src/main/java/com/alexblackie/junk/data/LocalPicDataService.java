package com.alexblackie.junk.data;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import reactor.core.publisher.Flux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.alexblackie.junk.models.Pic;
import com.alexblackie.junk.models.PicFactory;

@Component
@ConditionalOnProperty(prefix = "junk", value = "picDataService", havingValue = "local")
public class LocalPicDataService implements PicDataService {
	@Autowired
	private PicFactory picFactory;

	@Value("${junk.picDir}")
	private String dir;

	public Flux<Pic> listAll() {
		return listPath(Paths.get(this.dir))
			.filter(Files::isRegularFile)
			.map(p -> picFactory.buildPic(pathToRelativeString(p)));
	}

	public Flux<Pic> listAll(String prefix) {
		Path path = Paths.get(this.dir, prefix);
		return listPath(path)
			.filter(Files::isRegularFile)
			.map(p -> picFactory.buildPic(pathToRelativeString(p)));
	}

	public Flux<String> listAllPrefixes() {
		Path path = Paths.get(this.dir);
		return listPath(path)
			.filter(Files::isDirectory)
			.map(p -> pathToRelativeString(p))
			.filter(p -> !p.equals(this.dir));
	}

	public Pic getBySlug(String slug) {
		Path path = Paths.get(this.dir, slug);
		Flux<ByteBuffer> content = Flux.just(getFileBytes(path));
		return this.picFactory.buildPic(slug, content);
	}

	private Flux<Path> listPath(Path path) {
		try {
			return Flux.fromStream(Files.walk(path));
		} catch (IOException e) {
			return Flux.empty();
		}
	}

	private String pathToRelativeString(Path p) {
		return p.toString().replace(this.dir + "/", "");
	}

	private ByteBuffer getFileBytes(Path path) {
		try {
			return ByteBuffer.wrap(Files.readAllBytes(path));
		} catch (IOException e) {
			return ByteBuffer.wrap(new byte[0]);
		}
	}
}
