package com.reliance.jiocloud.monitoring;
import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract class AbstractMetricsFactory implements MetricsFactory {
    String programName;
    String operationName;
    String accountId;
    String marketplaceId;
    String requestId;

    public AbstractMetricsFactory() {
        this.programName = null;
        this.operationName = null;
        this.accountId = null;
        this.marketplaceId = null;
        this.requestId = null;
    }

    public AbstractMetricsFactory withProgramName(String programName) {
        this.programName = programName;
        return this;
    }

    public AbstractMetricsFactory withOperationsName(String operationName) {
        this.operationName = operationName;
        return this;
    }

    public AbstractMetricsFactory withAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public AbstractMetricsFactory withMarketplaceId(String marketplaceId) {
        this.marketplaceId = marketplaceId;
        return this;
    }

    public AbstractMetricsFactory withRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public void addMetricAttributes(Metrics metrics) throws UnknownHostException {
        metrics.addProperty("HostName", InetAddress.getLocalHost().getHostName());
        // metrics.addProperty("PId", os.getId());

        if (this.programName != null) {
            metrics.addProperty("ProgramName", this.programName);
        }

        if (this.operationName != null) {
            metrics.addProperty("OperationName", this.operationName);
        }

        if (this.accountId != null){
            metrics.addProperty("AccountId", this.accountId);
        }
        if (this.marketplaceId != null){
            metrics.addProperty("MarketplaceId", this.marketplaceId);
        }
        
        this.requestId = null;
        
        if (this.requestId != null){
            metrics.addProperty("RequestId", this.requestId);
        }
    }
}
