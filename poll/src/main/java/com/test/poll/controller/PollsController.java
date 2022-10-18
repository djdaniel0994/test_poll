package com.test.poll.controller;

import com.test.poll.dto.PollDTO;
import com.test.poll.service.IPollsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class PollsController {
    private final IPollsService pollsService;

    public PollsController(IPollsService pollsService) {
        this.pollsService = pollsService;
    }

    @PostMapping(path = "/polls")
    public ResponseEntity<String> savePoll(@RequestBody PollDTO pollDTO) {
        return new ResponseEntity<>(pollsService.savePoll(pollDTO), HttpStatus.OK);
    }

    @GetMapping(path = "/polls/{id}")
    public ResponseEntity<PollDTO> findPollById(@PathVariable Integer id) {
        return new ResponseEntity<>(pollsService.findPollById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/polls/get-all")
    public ResponseEntity<ArrayList<PollDTO>> findAllPolls() {
        return new ResponseEntity<>(pollsService.findAllPolls(), HttpStatus.OK);
    }
}
