package com.alexblackie.junk.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PicPresenterTests {

	@Test
	void testSetName() {
		PicPresenter pp = new PicPresenter();
		pp.setName("eggs.jpg");
		assertThat(pp.name).isEqualTo("eggs.jpg");
	}

	@Test
	void testSetSlug() {
		PicPresenter pp = new PicPresenter();
		pp.setSlug("ad/monster.gif");
		assertThat(pp.slug).isEqualTo("ad/monster.gif");
	}

	@Test
	void testSetPrefix_withoutPrefix() {
		PicPresenter pp = new PicPresenter();
		pp.setPrefix(null);
		assertThat(pp.prefix).isEqualTo("::");
		assertThat(pp.prefixPath).isEqualTo("/");
	}

	@Test
	void testSetPrefix_withPrefix() {
		PicPresenter pp = new PicPresenter();
		pp.setPrefix("kp");
		assertThat(pp.prefix).isEqualTo("kp");
		assertThat(pp.prefixPath).isEqualTo("/kp");
	}
}
