package at.dru.wicketblog.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultsService {

    @Value("${application.pageSize}")
    private int pageSize;

    public int getPageSize() {
        return pageSize;
    }

}
