package com.fsmile.core.domain.common;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.core.domain.common
 * @date 2/18/23 : 7:23 PM
 */
public interface GenericApi<E, K>{

    String create(E e);
    String update(E e, K k);
    String delete(K k);
    E find(K k);
    Page<E>findAll(int page, int size) throws Exception;
}
