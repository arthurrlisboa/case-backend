package challenge.domain.config;

import challenge.domain.mappers.LoanMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public LoanMapper getLoanMapper() {
        return Mappers.getMapper(LoanMapper.class);
    }
}
