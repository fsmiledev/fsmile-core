package com.fsmile.utils;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.concurrent.CompletableFuture;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.utils
 * @date 2/19/23 : 7:21 PM
 */
public class MapUtils {
    public static  <D, T> Page<D> mapEntityPageIntoDtoPage(CompletableFuture<Page<T>> entities, Class<D> dtoClass) throws Exception{
        ModelMapper modelMapper = new ModelMapper();
        return entities.get().map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
    }
}
