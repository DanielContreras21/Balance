package com.app.balance.service.abstraction;

import com.app.balance.model.entity.Spent;
import com.app.balance.model.request.SpentRequest;
import com.app.balance.model.response.SpentResponse;

import java.util.List;
import java.util.Optional;

public interface SpentService {
    void deleteById(Long id);
    Optional<SpentResponse> findById(Long id);
    SpentResponse createSpent(SpentRequest request);
    List<SpentResponse> findAllByUser();
    void updateSpent(SpentRequest request);
}
