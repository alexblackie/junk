package com.alexblackie.junk.converters;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.alexblackie.junk.models.Pic;
import com.alexblackie.junk.models.PicPresenter;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PicPresenterConverterTests {

	@Test
	void testSetGetName() {
		PicPresenterConverter converter = new PicPresenterConverter();
		Pic pic = new Pic();
		pic.setName("wink.gif");
		pic.setSlug("ad/wink.gif");
		pic.setPrefix("ad");

		PicPresenter presenter = converter.build(pic);

		assertThat(presenter.slug).isEqualTo("ad/wink.gif");
		assertThat(presenter.name).isEqualTo("wink.gif");
		assertThat(presenter.prefix).isEqualTo("ad");
		assertThat(presenter.prefixPath).isEqualTo("/ad");
	}
}
