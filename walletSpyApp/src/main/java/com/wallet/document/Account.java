package com.wallet.document;

import com.google.common.collect.Lists;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by EBR3556 on 21/09/2017.
 */
@Document
public class Account extends NamedDocument {

    private String shortName;
    private List<UploadInformation> uploadProcessLog;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<UploadInformation> getUploadProcessLog() {
        if(uploadProcessLog == null){
            return newArrayList();
        }
        return Lists.newArrayList(uploadProcessLog);
    }

    public void addBalance(UploadInformation balanceToAdd){
        if(uploadProcessLog == null){
            uploadProcessLog = newArrayList();
        }

        if(balanceToAdd != null && balanceToAdd.getBalance() != null && balanceToAdd.getDate() != null){
            uploadProcessLog.add(balanceToAdd);
        }
    }

    private void setUploadProcessLog(List<UploadInformation> uploadProcessLog) {
        this.uploadProcessLog = uploadProcessLog;
    }
}
