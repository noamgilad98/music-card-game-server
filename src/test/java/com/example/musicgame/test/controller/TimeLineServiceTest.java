package com.example.musicgame.test.controller;

import com.example.musicgame.model.TimeLine;
import com.example.musicgame.repository.TimeLineRepository;
import com.example.musicgame.service.TimeLineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TimeLineServiceTest {

    private TimeLineRepository timeLineRepository;
    private TimeLineService timeLineService;

    @BeforeEach
    void setUp() {
        timeLineRepository = Mockito.mock(TimeLineRepository.class);
        timeLineService = new TimeLineService();

        // Use reflection to inject the mocks
        ReflectionTestUtils.setField(timeLineService, "timeLineRepository", timeLineRepository);
    }

    @Test
    void testCreateTimeLine() {
        TimeLine timeLine = new TimeLine();
        when(timeLineRepository.save(any(TimeLine.class))).thenReturn(timeLine);

        TimeLine createdTimeLine = timeLineService.createTimeLine(timeLine);
        assertNotNull(createdTimeLine);
    }

    @Test
    void testGetTimeLineById() {
        TimeLine timeLine = new TimeLine();
        when(timeLineRepository.findById(anyLong())).thenReturn(Optional.of(timeLine));

        TimeLine foundTimeLine = timeLineService.getTimeLineById(1L);
        assertEquals(timeLine, foundTimeLine);
    }
}
