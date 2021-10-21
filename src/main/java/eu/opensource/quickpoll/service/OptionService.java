package eu.opensource.quickpoll.service;

import eu.opensource.quickpoll.domain.Option;

public interface OptionService {
    public Option getOptionById(Long optionId);
}
