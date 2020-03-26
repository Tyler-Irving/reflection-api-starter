package org.basecampcodingacademy.reflections.controllers;

import org.basecampcodingacademy.reflections.db.ResponseRepository;
import org.basecampcodingacademy.reflections.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reflections/{reflectionId}/responses")
public class ResponseController {
    @Autowired
    public ResponseRepository responses;

    @GetMapping
    public List<Response> index() {
        return responses.all();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody Response response, @PathVariable Integer reflectionId) {
        response.reflectionId = reflectionId;
        return responses.create(response);
    }

    @PatchMapping("/{id}")
    public Response update(@PathVariable Integer reflectionId,@PathVariable Integer id, @RequestBody Response response) {
        response.id = id;
        response.reflectionId = reflectionId;
        return responses.update(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        responses.delete(id);
    }
}