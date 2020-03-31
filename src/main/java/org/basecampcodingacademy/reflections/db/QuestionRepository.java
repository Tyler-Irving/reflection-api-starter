package org.basecampcodingacademy.reflections.db;

import org.basecampcodingacademy.reflections.domain.Question;
import org.basecampcodingacademy.reflections.domain.Question;
import org.basecampcodingacademy.reflections.domain.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
@Repository
public class QuestionRepository {
    @Autowired
    public JdbcTemplate jdbc;

    public List<Question> all() {
        return jdbc.query("SELECT id, prompt, reflectionId FROM questions", this::mapper);
    }

    public Question create(Question question) {
        var sql = "INSERT INTO questions (prompt, reflectionId) VALUES (?, ?) RETURNING *";
        return jdbc.queryForObject(
                sql,
                this::mapper,
                question.prompt,
                question.reflectionId
        );
    }

    public Question find(Integer id) {
        return jdbc.queryForObject("SELECT id, prompt, reflectionId FROM questions WHERE id = ?", this::mapper, id);
    }


    public Question update(Question question) {
        return jdbc.queryForObject(
                "UPDATE questions SET prompt = ? WHERE id = ? AND reflectionId = ? RETURNING id, prompt, reflectionId",
                this::mapper, question.prompt, question.id, question.reflectionId);
    }

    public void delete(Integer id) {
        jdbc.query("DELETE FROM questions WHERE id = ? RETURNING id, prompt, reflectionId", this::mapper, id);
    }

    private Question mapper(ResultSet resultSet, int i) throws SQLException {
        return new Question(
                resultSet.getInt("id"),
                resultSet.getString("prompt"),
                resultSet.getInt("reflectionId")
        );
    }
}