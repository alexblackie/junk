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
class JunkApplicationTests {

	@Test
	void testPicFactory_buildPic_withPrefix_noImage() {
		PicFactory pf = new PicFactory();
		Pic pic = pf.buildPic("kp/anguish.gif");
		assertThat(pic.getName()).isEqualTo("anguish.gif");
		assertThat(pic.getPrefix()).isEqualTo("kp");
		assertThat(pic.getSlug()).isEqualTo("kp/anguish.gif");
	}

	@Test
	void testPicFactory_buildPic_noPrefix_noImage() {
		PicFactory pf = new PicFactory();
		Pic pic = pf.buildPic("boop.gif");
		assertThat(pic.getName()).isEqualTo("boop.gif");
		assertThat(pic.getPrefix()).isNull();
		assertThat(pic.getSlug()).isEqualTo("boop.gif");
	}

	@Test
	void testPicFactory_buildPic_noPrefix_withImage() throws IOException {
		Path dog = Paths.get("src", "test", "fixtures", "dog-slide.gif");
		Flux<ByteBuffer> imgData = Flux.just(ByteBuffer.wrap(Files.readAllBytes(dog)));

		PicFactory pf = new PicFactory();
		Pic pic = pf.buildPic("dog-slide.gif", imgData);

		assertThat(pic.getName()).isEqualTo("dog-slide.gif");
		assertThat(pic.getPrefix()).isNull();
		assertThat(pic.getSlug()).isEqualTo("dog-slide.gif");
		assertThat(pic.getImage()).isNotNull();
	}
}
