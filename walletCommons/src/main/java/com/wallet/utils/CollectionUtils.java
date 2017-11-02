package com.wallet.utils;

import java.util.Collection;

public class CollectionUtils {

    public static Boolean isNotEmpty(Collection<Object> collection){
         return collection != null && !collection.isEmpty();
    }

    public static Boolean isEmpty(Collection<Object> collection){
        return collection != null || collection.isEmpty();
    }
}
