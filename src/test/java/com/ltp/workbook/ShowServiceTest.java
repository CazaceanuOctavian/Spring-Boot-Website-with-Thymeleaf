package com.ltp.workbook;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ltp.workbook.classes.Show;
import com.ltp.workbook.repository.ShowRepository;
import com.ltp.workbook.service.ShowService;

@RunWith(MockitoJUnitRunner.class)
public class ShowServiceTest {
    @Mock
    ShowRepository showRepository;

    @InjectMocks
    ShowService showService;

    @Test
    public void getShowsFromRepositoryTest() {
       when(showService.getShows()).thenReturn(Arrays.asList(
            new Show("Attack On Titan", "hero", 10, "sahgbflo1235js"),
            new Show("Mr. Robot", "Proxy 407 Not Found", 10, "jdahbfoliayuewg")
       ));

    List <Show> result = showService.getShows();

    assertEquals("Attack On Titan", result.get(0).getTitle());
    assertEquals("message", result.get(1).getRating(), 10, 0);
    }

}
