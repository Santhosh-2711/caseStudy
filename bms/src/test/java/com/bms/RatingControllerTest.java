package com.bms;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bms.controller.RatingController;
import com.bms.model.Ratingreview;
import com.bms.service.RatingService;

@ExtendWith(MockitoExtension.class)
public class RatingControllerTest {

    @Mock
    private RatingService ratingService;

    @InjectMocks
    private RatingController ratingController;

    @Test
    public void testPostFeedback() {
        Ratingreview rr = new Ratingreview();
        rr.setRating(5);
        rr.setReview("Excellent service!");

        when(ratingService.add(any(Ratingreview.class))).thenReturn(rr);

        Ratingreview postedFeedback = ratingController.postfeedback(rr);

        assertNotNull(postedFeedback);
        assertEquals(5, postedFeedback.getRating());
        assertEquals("Excellent service!", postedFeedback.getReview());
    }

    @Test
    public void testGetFeedbackDetails() {
        Ratingreview rr = new Ratingreview();
        rr.setRating(3);
        rr.setReview("Average experience.");

        when(ratingService.getOne(anyLong())).thenReturn(rr);

        Ratingreview fetchedFeedback = ratingController.getFeedbackdetails(1L);

        assertNotNull(fetchedFeedback);
        assertEquals(3, fetchedFeedback.getRating());
        assertEquals("Average experience.", fetchedFeedback.getReview());
    }
}