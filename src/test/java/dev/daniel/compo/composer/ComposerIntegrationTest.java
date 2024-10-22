package dev.daniel.compo.composer;

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
    JdbcClientComposerRepository jccr;
    @Autowired
    public ComposerIntegrationTest(JdbcClientComposerRepository jccr){
        this.jccr = jccr;
    }
    /*
    * The Methods in this class WILL work, but the database cannot be initialised beforehand, so that the API is empty upon initialization.
    * To run these tests and have them pass, the data.sql file must first be deleted from the /resources folder above. Then, restart the application,
    * delete the /target folder for good measure, and these tests will pass.
    * */
    @Test
    public void TestComposerCreation(){
        Composer composer = TestDataUtil.createComposerA();
        composer.setComposerId(1);
        jccr.create(composer);
        Optional<Composer> result = jccr.findById(composer.getComposerId());
        assertThat(result).isPresent();
        assertThat(result.get().toString()).isEqualTo(composer.toString());
    }
    @Test
    public void testMultipleComposersCreatedAndCalled() {
        Composer composerA = TestDataUtil.createComposerA();
        jccr.create(composerA);
        Composer composerB = TestDataUtil.createComposerB();
        jccr.create(composerB);
        Composer composerC = TestDataUtil.createComposerC();
        jccr.create(composerC);

        List<Composer> result = jccr.findAll();
        assertThat(result.toString())
                .hasSize(432).
                contains(composerA.toString(), composerB.toString(), composerC.toString());
    }
    @Test
    public void testComposerUpdate() {
        Composer composerA = TestDataUtil.createComposerA();
        jccr.create(composerA);
        composerA.setFirstName("UPDATED");
        jccr.create(composerA);
        Optional<Composer> result = jccr.findById(composerA.getComposerId());
        assertThat(result).isPresent();
        assertThat(result.get().toString()).isEqualTo(composerA.toString());
    }
    @Test
    public void testComposerDelete() {
        Composer composerA = TestDataUtil.createComposerA();
        jccr.create(composerA);
        jccr.delete(composerA.getComposerId());
        Optional<Composer> result = jccr.findById(composerA.getComposerId());
        assertThat(result).isEmpty();
    }
}
