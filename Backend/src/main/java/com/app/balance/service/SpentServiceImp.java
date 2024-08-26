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
import java.util.NoSuchElementException;
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
    public SpentResponse createSpent(SpentRequest request) {
        User user = currentUser.getCurrentUser();
        Spent spent = mapper.dtoToEntity(request);
        spent.setUser(user);
        repository.save(spent);
        SpentResponse spentResponse = mapper.entitytoDto(spent);
        return spentResponse;
    }


    @Override
    public List<SpentResponse> findAllByUser() {
        User user = currentUser.getCurrentUser();
        List<Spent> spents = repository.findByUser(user);

        List<SpentResponse> spentsResponse = spents.stream().map(
                mapper::entitytoDto).toList();
        return spentsResponse;
    }

    @Override
    public void updateSpent(SpentRequest request) {
        boolean isSpentExist = repository.existsById(request.getId());

        if (isSpentExist){
            Spent spent = repository.findSpentById(request.getId());
            if (request.getConcept() == null){
                spent.setConcept(spent.getConcept());
            } else {
                spent.setConcept(request.getConcept());
            }
            if (request.getQuantity() == null){
                spent.setQuantity(spent.getQuantity());
            } else {
                spent.setQuantity(request.getQuantity());
            }
            spent.setId(spent.getId());
            spent.setUser(spent.getUser());
            spent.setCreated(spent.getCreated());

            repository.save(spent);
        }else {
            throw new NoSuchElementException("El ingreso no existe");
        }
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
