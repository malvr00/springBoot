package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private EntityManager en;

    @Autowired
    public SpringConfig(EntityManager en) {
        this.en = en;
    }

    //    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    // 직접 Bean에 등록하는 방법
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        // Repository 어노테이션을 활용하지 않고 Bean에 등록해서 사용하면
        // 바꿔치기 용의함.
        return new JpaMemberRepository(en);
//        return new JdbcMemberRepository(dataSource);        // JDBC Template
//        return new JdbcMemberRepository(dataSource);      // 순수 JDBC
//        return new MemoryMemberRepository();              // Memory
    }
}
