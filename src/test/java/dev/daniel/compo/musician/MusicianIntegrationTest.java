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
public class MusicianIntegrationTest {
    @MockBean
    JdbcClientMusicianRepository jcmr;
    @Autowired
    public MusicianIntegrationTest(JdbcClientMusicianRepository jcmr){
        this.jcmr = jcmr;
    }
    /*
    * The Methods in this class WILL work, but the database cannot be initialised beforehand, so that the API is empty upon initialization.
    * To run these tests and have them pass, the data.sql file must first be deleted from the /resources folder above. Then, restart the application,
    * delete the /target folder for good measure, and these tests will pass.
    * */
    @Test
    public void TestMusicianCreation(){
        Musician pianist = TestDataUtil.createPianistA();
        pianist.setMusicianId(1);
        jcmr.create(pianist);
        Optional<MusicianFactory> result = jcmr.findById(pianist.getMusicianId());
        assertThat(result).isPresent();
        assertThat(result.get().toString()).isEqualTo(pianist.toString());
    }
    @Test
    public void testMultipleMusicianCreatedAndCalled() {
        MusicianFactory musicianA = TestDataUtil.createPianistA();
        jcmr.create(musicianA);
        MusicianFactory musicianB = TestDataUtil.createPianistB();
        jcmr.create(musicianB);
        MusicianFactory musicianC = TestDataUtil.createPianistC();
        jcmr.create(musicianC);

        List<MusicianFactory> result = jcmr.findAll();
        assertThat(result.toString())
                .hasSize(432).
                contains(musicianA.toString(), musicianB.toString(), musicianC.toString());
    }
    @Test
    public void testMusicianUpdate() {
        MusicianFactory pianistA = TestDataUtil.createPianistA();
        jcmr.create(pianistA);
        pianistA.setFirstName("UPDATED");
        jcmr.create(pianistA);
        Optional<MusicianFactory> result = jcmr.findById(pianistA.getMusicianId());
        assertThat(result).isPresent();
        assertThat(result.get().toString()).isEqualTo(pianistA.toString());
    }
    @Test
    public void testMusicianDelete() {
        Musician pianistA = TestDataUtil.createPianistA();
        jcmr.create(pianistA);
        jcmr.delete(pianistA.getMusicianId());
        Optional<MusicianFactory> result = jcmr.findById(pianistA.getMusicianId());
        assertThat(result).isEmpty();
    }
}
