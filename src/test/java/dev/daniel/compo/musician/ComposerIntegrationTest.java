package dev.daniel.compo.musician;

import dev.daniel.compo.Application;
import dev.daniel.compo.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComposerIntegrationTest {
    @MockBean
    JdbcClientMusicianRepository jccr;
    @Autowired
    public ComposerIntegrationTest(JdbcClientMusicianRepository jccr){
        this.jccr = jccr;
    }
    /*
    * The Methods in this class WILL work, but the database cannot be initialised beforehand, so that the API is empty upon initialization.
    * To run these tests and have them pass, the data.sql file must first be deleted from the /resources folder above. Then, restart the application,
    * delete the /target folder for good measure, and these tests will pass.
    * */
    @Test
    public void TestComposerCreation(){
        Pianist pianist = TestDataUtil.createPianistA();
        pianist.setMusicianId(1);
        jccr.create(pianist);
        Optional<Musician> result = jccr.findById(pianist.getMusicianId());
        assertThat(result).isPresent();
        assertThat(result.get().toString()).isEqualTo(pianist.toString());
    }
    @Test
    public void testMultipleComposersCreatedAndCalled() {
        Musician musicianA = TestDataUtil.createPianistA();
        jccr.create(musicianA);
        Musician musicianB = TestDataUtil.createPianistB();
        jccr.create(musicianB);
        Musician musicianC = TestDataUtil.createPianistC();
        jccr.create(musicianC);

        List<Musician> result = jccr.findAll();
        assertThat(result.toString())
                .hasSize(432).
                contains(musicianA.toString(), musicianB.toString(), musicianC.toString());
    }
    @Test
    public void testComposerUpdate() {
        Musician pianistA = TestDataUtil.createPianistA();
        jccr.create(pianistA);
        pianistA.setFirstName("UPDATED");
        jccr.create(pianistA);
        Optional<Musician> result = jccr.findById(pianistA.getMusicianId());
        assertThat(result).isPresent();
        assertThat(result.get().toString()).isEqualTo(pianistA.toString());
    }
    @Test
    public void testComposerDelete() {
        Pianist pianistA = TestDataUtil.createPianistA();
        jccr.create(pianistA);
        jccr.delete(pianistA.getMusicianId());
        Optional<Musician> result = jccr.findById(pianistA.getMusicianId());
        assertThat(result).isEmpty();
    }
}
