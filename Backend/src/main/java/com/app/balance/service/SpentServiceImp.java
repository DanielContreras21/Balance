package com.app.balance.service;

import com.app.balance.model.entity.Spent;
import com.app.balance.model.entity.User;
import com.app.balance.model.mapper.SpentMapper;
import com.app.balance.model.request.SpentRequest;
import com.app.balance.model.response.SpentResponse;
import com.app.balance.repository.SpentRepository;
import com.app.balance.service.abstraction.SpentService;
import com.app.balance.utils.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpentServiceImp implements SpentService {

    @Autowired
    private final CurrentUser currentUser;

    @Autowired
    private SpentRepository repository;

    @Autowired
    private SpentMapper mapper;

    @Override
    public void createSpent(SpentRequest request) {
        User user = currentUser.getCurrentUser();
        Spent spent = mapper.dtoToEntity(request);
        spent.setUser(user);
        repository.save(spent);
    }

    @Override
    public List<Spent> findAllByUser() {
        User user = currentUser.getCurrentUser();
        List<Spent> spents = repository.findByUser(user);
        return spents;
    }

    @Override
    public Optional<SpentResponse> findById(Long id) {
        return repository.findById(id)
                .map(mapper::entitytoDto);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
