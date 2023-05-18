package com.fsmile.utils;

import com.fsmile.app.donation.entities.DonationEntity;
import org.springframework.data.domain.Page;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author raphael
 * @project fsmile-api
 * @package com.fsmile.utils
 * @date 5/18/23 : 2:08 PM
 */

@FunctionalInterface
public interface MapAsyncEntityPageToDtoPage<T, U>{
    Page<T> map(CompletableFuture<Page<U>> u) throws Exception;
}
