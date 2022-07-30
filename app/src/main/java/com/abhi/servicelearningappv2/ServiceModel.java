package com.abhi.servicelearningappv2;

public class ServiceModel {
    public String serviceImage;
    public String serviceName;
    public String serviceDesc;
    public ServiceModel(){
    }
    public ServiceModel(String serviceImage,String serviceName, String serviceDesc){
        this.serviceImage = serviceImage;
        this.serviceName = serviceName;
        this.serviceDesc = serviceDesc;
    }
    public String getServiceImage() {

        return serviceImage;
    }
    public void setServiceImage(String serviceImage) {

        this.serviceImage = serviceImage;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public String getServiceDesc() {
        return serviceDesc;
    }
    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }
}
