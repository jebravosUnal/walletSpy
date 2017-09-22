package com.wallet.mapper;

import com.wallet.dto.TransactionDto;
import com.wallet.entity.Transaction;
import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.Mapper;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

/**
 * Created by EBR3556 on 22/09/2017.
 */
@Mapper( withCustomFields = {
                @Field({"date", "fate"}),
                @Field({"category", "category"}),
                @Field({"amount", "amount"}),
                @Field({"userId", "userId"}),
                @Field({"account.name", "accountName"})
        }, withIgnoreFields = "id" )
public interface TransactionMapper {

    // This will build a fresh new OrderDto
    TransactionDto asTransactionDto(Transaction in);

    // This will update the given order
    Transaction asTransaction(TransactionDto in);

}