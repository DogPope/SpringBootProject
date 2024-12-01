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
    @Test
    public void TestMusicianCreation(){
        Musician A = TestDataUtil.createMusicianA();
        A.setMusicianId(1);
        jcmr.create(A);
        Optional<Musician> result = jcmr.findById(A.getMusicianId());
        assertThat(result).isPresent();
        assertThat(result.get().toString()).isEqualTo(A.toString());
    }
    @Test
    public void testMultipleMusicianCreatedAndCalled() {
        Musician A = TestDataUtil.createMusicianA();
        jcmr.create(A);
        Musician B = TestDataUtil.createMusicianB();
        jcmr.create(B);
        Musician C = TestDataUtil.createMusicianC();
        jcmr.create(C);

        List<Musician> result = jcmr.findAll();
        assertThat(result.toString())
                .contains(A.toString(), B.toString(), C.toString());
    }
    @Test
    public void testMusicianUpdate() {
        Musician A = TestDataUtil.createMusicianA();
        jcmr.create(A);
        A.setFirstName("UPDATED");
        jcmr.create(A);
        Optional<Musician> result = jcmr.findById(A.getMusicianId());
        assertThat(result).isPresent();
        assertThat(result.get().toString()).isEqualTo(A.toString());
    }
    @Test
    public void testMusicianDelete() {
        Musician A = TestDataUtil.createMusicianA();
        jcmr.create(A);
        jcmr.delete(A.getMusicianId());
        Optional<Musician> result = jcmr.findById(A.getMusicianId());
        assertThat(result).isEmpty();
    }
}
