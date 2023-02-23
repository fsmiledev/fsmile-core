package com.fsmile.core.domain.common;

import org.springframework.data.domain.Page;


/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.core.domain.common
 * @date 2/18/23 : 7:23 PM
 */
public interface GenericRepository<E, K> {

    String create(E e);

    String update(E e);

    String delete(K k);

    E find(K k);

    Page<E> findAllBy(int page, int size) throws Exception;
}
