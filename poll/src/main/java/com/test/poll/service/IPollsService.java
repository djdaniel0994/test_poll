package com.test.poll.service;

import com.test.poll.dto.PollDTO;

import java.util.ArrayList;

public interface IPollsService {
    ArrayList<PollDTO> findAllPolls();

    PollDTO findPollById(Integer id);

    String savePoll(PollDTO pollDTO);
}
