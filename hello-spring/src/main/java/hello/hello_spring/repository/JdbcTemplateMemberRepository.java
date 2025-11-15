package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.*;

public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("member")
                .usingGeneratedKeyColumns("id");

        // member 테이블에 실제 있는 컬럼들만 넣어야 한다.
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        parameters.put("login_id", member.getLoginId());
        parameters.put("email", member.getEmail());
        parameters.put("phone", member.getPhone());
        parameters.put("password", member.getPassword());
        parameters.put("birth", member.getBirth());
        parameters.put("student_id", member.getStudentId());
        parameters.put("major", member.getMajor());
        parameters.put("graduation_term", member.getGraduationTerm());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query(
                "select * from member where id = ?",
                memberRowMapper(),
                id
        );
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query(
                "select * from member",
                memberRowMapper()
        );
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query(
                "select * from member where name = ?",
                memberRowMapper(),
                name
        );
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        List<Member> result = jdbcTemplate.query(
                "select * from member where login_id = ?",
                memberRowMapper(),
                loginId
        );
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        List<Member> result = jdbcTemplate.query(
                "select * from member where email = ?",
                memberRowMapper(),
                email
        );
        return result.stream().findAny();
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));

            // 아래 컬럼들도 실제 DB에 있으면 그대로 매핑
            member.setLoginId(rs.getString("login_id"));
            member.setEmail(rs.getString("email"));
            member.setPhone(rs.getString("phone"));
            member.setPassword(rs.getString("password"));
            member.setBirth(rs.getString("birth"));
            member.setStudentId(rs.getString("student_id"));
            member.setMajor(rs.getString("major"));
            member.setGraduationTerm(rs.getString("graduation_term"));

            return member;
        };
    }
}
