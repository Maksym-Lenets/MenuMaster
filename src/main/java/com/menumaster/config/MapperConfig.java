package com.menumaster.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MapperConfig {
    /**
     * Provides a configured ModelMapper instance with strict matching strategy.
     * This method sets up a ModelMapper with specific configuration: - Matching
     * strategy is set to STRICT, ensuring strict property matching. - Field
     * matching is enabled, allowing fields with the same name to be mapped. - Null
     * values are skipped during mapping. - Field access level is set to private.
     *
     * @param converters A list of converters discovered by Spring to be used by the
     *                   ModelMapper.
     * @return A properly configured instance of ModelMapper.
     */
    @Bean
    public ModelMapper getModelMapper(List<Converter<?, ?>> converters) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper
            .getConfiguration()
            .setMatchingStrategy(MatchingStrategies.STRICT)
            .setFieldMatchingEnabled(true)
            .setSkipNullEnabled(true)
            .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        converters.forEach(modelMapper::addConverter);

        return modelMapper;
    }
}
