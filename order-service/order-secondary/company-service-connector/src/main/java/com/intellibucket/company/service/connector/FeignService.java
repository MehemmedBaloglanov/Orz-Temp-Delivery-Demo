package src.main.java.com.intellibucket.company.service.connector;

import com.intellibucket.company.service.connector.CompanyClient;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.CompanyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeignController {

    private final CompanyClient companyClient;

    @Autowired
    public FeignController(CompanyClient companyClient) {
        this.companyClient = companyClient;
    }

    public CompanyResponse getCompanyDetails(String companyId) {
        return companyClient.getCompanyById(companyId);
    }


}
