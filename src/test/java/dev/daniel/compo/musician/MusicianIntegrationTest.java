package dev.daniel.compo.musician;

import dev.daniel.compo.Application;
import dev.daniel.compo.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MusicianIntegrationTest {
    @Autowired
    JdbcClientMusicianRepository jcmr;
    @Autowired
    public MusicianIntegrationTest(JdbcClientMusicianRepository jcmr){
        this.jcmr = jcmr;
    }
    @Test
    public void TestMusicianCreation(){
        Musician A = TestDataUtil.createMusicianA();
        jcmr.create(A);
        Optional<Musician> result = jcmr.findById(A.getMusicianId());
        assertThat(result).isPresent();
    }
    @Test
    public void testMultipleMusicianCreatedAndCalled() {
        Musician a = TestDataUtil.createMusicianD();
        jcmr.create(a);
        List<Musician> result = jcmr.findAll();
        assertThat(result.size() == 10);
    }
    @Test
    public void testMusicianUpdate() {
        Musician D = TestDataUtil.createMusicianD();
        jcmr.create(D);
        D.setFirstName("UPDATED");
        D.setMusicianId(7);
        jcmr.update(D,7);
        Optional<Musician> result = jcmr.findById(D.getMusicianId());
        assertThat(result.toString().contains(D.getFirstName()));
    }
    @Test
    public void testMusicianDelete() {
        Musician D = TestDataUtil.createMusicianD();
        jcmr.create(D);
        jcmr.delete(D.getMusicianId());
        Optional<Musician> result = jcmr.findById(D.getMusicianId());
        assertThat(result).isEmpty();
    }
}
