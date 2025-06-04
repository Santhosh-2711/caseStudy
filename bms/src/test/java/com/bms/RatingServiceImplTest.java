package com.bms;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bms.model.Ratingreview;
import com.bms.repository.RatingRepo;
import com.bms.service.impl.RatingServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RatingServiceImplTest {

    @Mock
    private RatingRepo ratingRepo;

    @InjectMocks
    private RatingServiceImpl ratingService;

    @Test
    public void testAdd() {
        Ratingreview rr = new Ratingreview();
        rr.setRating(5);
        rr.setReview("Great service!");

        when(ratingRepo.save(any(Ratingreview.class))).thenReturn(rr);

        Ratingreview savedRating = ratingService.add(rr);

        assertNotNull(savedRating);
        assertEquals(5, savedRating.getRating());
        assertEquals("Great service!", savedRating.getReview());
    }

    @Test
    public void testGetOne() {
        Ratingreview rr = new Ratingreview();
        rr.setRating(4);
        rr.setReview("Good experience.");

        when(ratingRepo.findById(anyLong())).thenReturn(Optional.of(rr));

        Ratingreview fetchedRating = ratingService.getOne(1L);

        assertNotNull(fetchedRating);
        assertEquals(4, fetchedRating.getRating());
        assertEquals("Good experience.", fetchedRating.getReview());
    }
}