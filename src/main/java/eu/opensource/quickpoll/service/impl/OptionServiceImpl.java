package eu.opensource.quickpoll.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.opensource.quickpoll.domain.Option;
import eu.opensource.quickpoll.repository.OptionRepository;
import eu.opensource.quickpoll.service.OptionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service("optionService")
public class OptionServiceImpl implements OptionService{

    private final OptionRepository optionRepository;

    @Override
    public Option getOptionById(Long optionId) {

        return optionRepository.getById(optionId);
    }
    
}
