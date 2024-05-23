package dev.seyma.core.config;

import dev.seyma.core.config.modelMapper.IModelMapperService;
import dev.seyma.core.exception.NotFoundException;
import dev.seyma.core.utilies.Msg;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Configuration
public class ConvertEntityToResponse<E, RESPONSE> {
    private final IModelMapperService modelMapperService;
    public List<RESPONSE> convertToResponseList(List<E> entityList, Class<RESPONSE> responseClass) {
        if (entityList.isEmpty()){
            throw new NotFoundException(Msg.NOT_FOUND);
        } else {
            return entityList.stream()
                    .map(entity -> modelMapperService.forResponse().map(entity, responseClass))
                    .collect(Collectors.toList());
        }
    }
}

