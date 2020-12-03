package edu.roadmaps.core.rest.leaves.lecture;


import edu.roadmaps.core.rest.dto.leaf.LeafDto;
import edu.roadmaps.core.rest.leaves.LeafControllerRestFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
public class LectureController {

    @Autowired
    LeafControllerRestFacade facade;

    @PostMapping(value = "/v0.1/courses/modules/{module_id}/lectures/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody LeafDto dto, @PathVariable("module_id") UUID moduleId){
        LeafDto response = facade.create(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/v0.1/courses/modules/lectures/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@PathVariable("id") UUID id){
        LeafDto response = facade.get(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/v0.1/courses/modules/lectures/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id){
        facade.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping(value = "/v0.1/courses/modules/lectures/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody LeafDto dto, @PathVariable("id") UUID id){
        LeafDto response = facade.update(dto, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
