package io.roadmaps.core.integrations.jpa.idgenerator;

import io.roadmaps.core.domain.common.id.Generator;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcSequenceGenerator implements Generator<Long> {
    private static final String GENERATE_NEXT_QUERY_TEMPLATE
            = "SELECT nextval('%s') AS id";
    private final JdbcTemplate jdbcTemplate;
    private final String query;
    
    public JdbcSequenceGenerator(DataSource dataSource, String sequenceName) {
        super();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.query = String.format(GENERATE_NEXT_QUERY_TEMPLATE, sequenceName);
    }
    
    @Override
    public Long generateNext() {
        return jdbcTemplate.queryForObject(query, Long.class);
    }
}
