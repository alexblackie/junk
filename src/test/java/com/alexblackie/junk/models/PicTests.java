package com.alexblackie.junk.models;

import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PicTests {

	@Test
	void testSetGetName() {
		Pic p = new Pic();
		p.setName("eggs.jpg");
		assertThat(p.getName()).isEqualTo("eggs.jpg");
	}

	@Test
	void testSetGetSlug() {
		Pic p = new Pic();
		p.setSlug("ad/monster.gif");
		assertThat(p.getSlug()).isEqualTo("ad/monster.gif");
	}

	@Test
	void testSetGetPrefix() {
		Pic p = new Pic();
		p.setPrefix("kp");
		assertThat(p.getPrefix()).isEqualTo("kp");
	}

	@Test
	void testSetGetImage() throws IOException {
		Path dog = Paths.get("src", "test", "fixtures", "dog-slide.gif");
		Flux<ByteBuffer> imgData = Flux.just(ByteBuffer.wrap(Files.readAllBytes(dog)));
		Pic p = new Pic();
		p.setImage(imgData);
		assertThat(p.getImage()).isNotNull();
	}
}
