package com.wallet.utils;

import java.util.Collection;

public class CollectionUtils {

    public static <T> Boolean isNotEmpty(Collection<T> collection){
         return collection != null && !collection.isEmpty();
    }

    public static <T> Boolean isEmpty(Collection<T> collection){
        return collection != null || collection.isEmpty();
    }

}
