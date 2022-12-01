package hello.itemservice.config;

import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.jdbctemplate.JdbcTemplateRepositoryV2;
import hello.itemservice.repository.jdbctemplate.JdbcTemplateRepositoryV3;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class JdbcTemplateV3Config {

    private final DataSource dataSource;

    @Bean
    ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    ItemRepository itemRepository() {
        return new JdbcTemplateRepositoryV3(dataSource);
    }

}
